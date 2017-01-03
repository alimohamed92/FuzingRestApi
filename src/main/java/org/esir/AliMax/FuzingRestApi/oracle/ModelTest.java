package org.esir.AliMax.FuzingRestApi.oracle;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import io.swagger.models.Path;

public abstract class ModelTest {
	private String testName;
	private final String CODEOK = "OK";
	private final String CODEKO = "KO";
	protected Map<String,Path> paths;
	protected String motif;
	public ModelTest(String testName, Map<String,Path> paths) {
		super();
		this.testName = testName;
		this.paths = paths;
		this.motif = "";
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
	public String interpreteResult(boolean resultTest, String methodName, String url){
		String res = "";
		String resTest="";
		String classColor = "";
			if(resultTest){
				resTest += CODEOK ;
				classColor = "class=\"hidden-xs bg-success\"";
			}
			else {
				resTest += CODEKO;
				classColor = "class=\"hidden-xs bg-danger\"";
			}
		res += "<tr "+classColor+">";
		res+="<td>"+this.getTestName()+" </td><td> "+methodName+"</td><td>"+url+" </td><td>  "+resTest+"</td><td>"+this.motif+"</td></tr>\n";
		return res;
	}
	

}
