package com.logparsing.logparsing.service;

import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logparsing.logparsing.TodoWebProperties;
import com.opencsv.CSVWriter;

@Service
public class Write {

	@Autowired
	VlogsRepo vlogsRepo;

	public void csvwriter() {

		try (CSVWriter dimeboxcsvWrite = new CSVWriter(new FileWriter(TodoWebProperties.getProperty("destn")));) {

			String dimeboxheader[] = { "Gateway_Trace_Id", "Detokenization TIME TAKEN", "Validation TIME TAKEN:",
					"Dimebox Call TIME TAKEN" };
			dimeboxcsvWrite.writeNext(dimeboxheader);
			List<Vlogs> findAllVlogs = vlogsRepo.findAll();
			String[] csvData = new String[4];
			for (Vlogs vlogs : findAllVlogs) {
				csvData[0] = vlogs.getGatewayTraceId();
				csvData[1] = vlogs.getDetokenizationTimeTaken();
				csvData[2] = vlogs.getValidationTimeTaken();
				csvData[3] = vlogs.getDimeboxCallTIMETAKEN();
				dimeboxcsvWrite.writeNext(csvData);
			}

			System.out.println("#################### close CSV write ################");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
