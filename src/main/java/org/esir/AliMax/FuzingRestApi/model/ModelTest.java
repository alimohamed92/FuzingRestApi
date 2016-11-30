package org.esir.AliMax.FuzingRestApi.model;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import io.swagger.models.Path;

public abstract class ModelTest {
	private String testName;
	protected Map<String,Path> paths;
	public ModelTest(String testName, Map<String,Path> paths) {
		super();
		this.testName = testName;
		this.paths = paths;
	}

	public Map<String, Path> getPaths() {
		return paths;
	}

	public void setPaths(Map<String, Path> paths) {
		this.paths = paths;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public abstract String generateReport(String url) ;
	@Override
	public String toString() {
		return getTestName()+ " résultat du rapport [" ;
	}
	

}
