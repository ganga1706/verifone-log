package com.logparsing.logparsing.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VERIFONE_LOGS", uniqueConstraints = { @UniqueConstraint(columnNames = { "GATEWAY_TRACE_ID" }) })
public class Vlogs implements Serializable {

	private static final long serialVersionUID = -944006380567234140L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	@JsonIgnore
	private int Id;

	@Column(name = "GATEWAY_TRACE_ID")
	private String gatewayTraceId;

	@Column(name = "DETOKENIZATION_TIME_TAKEN")
	private String detokenizationTimeTaken;

	@Column(name = "VALIDATION_TIME_TAKEN")
	private String validationTimeTaken;

	@Column(name = "DIMEBOX_CALL_TIME_TAKEN")
	private String dimeboxCallTIMETAKEN;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getGatewayTraceId() {
		return gatewayTraceId;
	}

	public void setGatewayTraceId(String gatewayTraceId) {
		this.gatewayTraceId = gatewayTraceId;
	}

	public String getDetokenizationTimeTaken() {
		return detokenizationTimeTaken;
	}

	public void setDetokenizationTimeTaken(String detokenizationTimeTaken) {
		this.detokenizationTimeTaken = detokenizationTimeTaken;
	}

	public String getValidationTimeTaken() {
		return validationTimeTaken;
	}

	public void setValidationTimeTaken(String validationTimeTaken) {
		this.validationTimeTaken = validationTimeTaken;
	}

	public String getDimeboxCallTIMETAKEN() {
		return dimeboxCallTIMETAKEN;
	}

	public void setDimeboxCallTIMETAKEN(String dimeboxCallTIMETAKEN) {
		this.dimeboxCallTIMETAKEN = dimeboxCallTIMETAKEN;
	}

}
