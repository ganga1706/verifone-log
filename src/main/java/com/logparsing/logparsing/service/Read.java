package com.logparsing.logparsing.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logparsing.logparsing.TodoWebProperties;

@Service
public class Read {
	@Autowired
	VlogsRepo vlogsRepo;

	public void dimeboxValidationTimeTakenFileWrite() throws IOException {
		File files = new File(TodoWebProperties.getProperty("source"));
		// Fetching all the files
		File[] filearray = files.listFiles();
		System.out.println("Number of Log files:" + filearray.length);
		for (File file : filearray) {
			if (file.isFile()) {
				try (BufferedReader fileContent = new BufferedReader(new FileReader(file))) {
					String line;
					while ((line = fileContent.readLine()) != null) {
						if (line.contains("Detokenization TIME TAKEN:")) {
							getDetokenizationTimeTaken(line);
						}
						if (line.contains("Validation TIME TAKEN:")) {
							getValidationTimeTaken(line);
						}
						if (line.contains("Dimebox Call TIME TAKEN:")) {
							getDimeboxCallTIMETAKEN(line);
						}
					}

				}

			}

		}

		System.out.println("#################### end DB insertion ########################");

	}

	public String getDetokenizationTimeTaken(String dtt) {
		String strDttTime = null;
		if (dtt.contains("Detokenization TIME TAKEN:")) {
			String traceId = getTraceId(dtt);
			Vlogs findByTraceId = findByTraceId(traceId);
			String substring = dtt.substring(dtt.lastIndexOf("Detokenization TIME TAKEN:"));
			String[] split = substring.split(":");
			strDttTime = split[1];
//			System.out.println("Detokenization_Time_Taken : " + strDttTime);
			if (findByTraceId == null) {
				saveToDB(traceId, strDttTime, null, null);
			} else if (findByTraceId.getDetokenizationTimeTaken() == null
					|| findByTraceId.getDetokenizationTimeTaken().isEmpty()) {
				vlogsRepo.updateDetokenizationTimeTakenByGatewayTraceId(strDttTime, traceId);
			}

			return strDttTime;

		}
		return strDttTime;
	}

	public String getValidationTimeTaken(String vtt) {
		String strVttTime = null;
		if (vtt.contains("Validation TIME TAKEN:")) {
			String traceId = getTraceId(vtt);
			Vlogs findByTraceId = findByTraceId(traceId);
			String substring = vtt.substring(vtt.lastIndexOf("Validation TIME TAKEN:"));
			String[] split = substring.split(":");
//			String traceId = split[3]; //trace id
//			System.out.println("trace id:" + traceId);
			String substring2 = split[1].substring(0, 5);
			strVttTime = substring2.replaceAll(",", ""); // val time
//			System.out.println("Validation TIME TAKEN:" + strVttTime);
			if (findByTraceId == null) {
				saveToDB(traceId, null, vtt, null);
			} else if (findByTraceId.getValidationTimeTaken() == null
					|| findByTraceId.getValidationTimeTaken().isEmpty()) {
				vlogsRepo.updateValidationTimeTakenByGatewayTraceId(strVttTime, traceId);
			}

			return strVttTime;
		}
		return strVttTime;
	}

	public String getDimeboxCallTIMETAKEN(String dctt) {
		String strDcttTime = null;
		if (dctt.contains("Dimebox Call TIME TAKEN:")) {
			String traceId = getTraceId(dctt);
			Vlogs findByTraceId = findByTraceId(traceId);
			String substring = dctt.substring(dctt.lastIndexOf("Dimebox Call TIME TAKEN:"));
			String[] split = substring.split(":");
//			String traceId = split[3]; // trace id
//			System.out.println("trace id:" + traceId);
			String substring2 = split[1].substring(0, 5);
			strDcttTime = substring2.replaceAll(",", ""); // val time
//			System.out.println("Dimebox Call TIME TAKEN:" + strDcttTime);

			if (findByTraceId == null) {
				saveToDB(traceId, null, null, dctt);
			} else if (findByTraceId.getDimeboxCallTIMETAKEN() == null
					|| findByTraceId.getDimeboxCallTIMETAKEN().isEmpty()) {
				vlogsRepo.updatedimeboxCallTIMETAKENByGatewayTraceId(strDcttTime, traceId);
			}
			return strDcttTime;
		}
		return strDcttTime;
	}

	public String getTraceId(String str) {
		String[] split = str.split(" ");
		System.out.println(split[1]);
		StringBuffer sb = new StringBuffer(split[7]);
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public void saveToDB(String traceId, String dtt, String vtt, String dctt) {
		Vlogs findByGatewayTraceId = vlogsRepo.findByGatewayTraceId(traceId);
		if (findByGatewayTraceId == null) {
			Vlogs vlogs = new Vlogs();
			vlogs.setGatewayTraceId(traceId);
			vlogs.setDetokenizationTimeTaken(dtt);
			vlogs.setValidationTimeTaken(vtt);
			vlogs.setDimeboxCallTIMETAKEN(dctt);
			try {
				vlogsRepo.save(vlogs);
			} catch (Exception e) {
				System.err.println("duplicate traceId");
			}
		}
	}

	public Vlogs findByTraceId(String traceId) {
		Vlogs findByGatewayTraceId = null;
		try {
			findByGatewayTraceId = vlogsRepo.findByGatewayTraceId(traceId);
		} catch (Exception e) {
			return findByGatewayTraceId;
		}
		return findByGatewayTraceId;
	}

}