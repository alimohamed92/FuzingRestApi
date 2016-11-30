package org.esir.AliMax.FuzingRestApi.model;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.apideclaration.Operation;

public class VerifyCode extends ModelTest{
	private final String CODEOK = "{OK}";
	private final String CODEKO = "{KO}";
	private final String GET ="GET";
	private final String POST ="POST";
	private final String PUT ="PUT";
	
	public VerifyCode(Map<String,Path> paths) {
		super("Verif code de retour", paths);
	}

	private String httpMethod (String url, Path p, String method) {
		String result =" $ Méthode "+method+" : URL = "+url+", REPONSE = ";
		String exception ="";
		int statuCode = 0;
		Map<String, Response> responseCodes = null;
		HttpMethod m = null;
		io.swagger.models.Operation operation = null;
		
		 if( method.equals(GET) && p.getGet()!=null){
			 operation = p.getGet();
			 responseCodes = p.getGet().getResponses();
			 m = new GetMethod(url);
		 }
		if(method.equals(POST) && p.getPost()!=null){
			operation = p.getPost();
			m = new PostMethod(url);
			responseCodes = p.getPost().getResponses();
		}
		else if(method.equals(PUT)&&  p.getPut()!=null){
			operation = p.getPut();
			m = new PutMethod(url);
			responseCodes = p.getPut().getResponses();
		}
		if( operation != null ){
			try {
				HttpClient client = new HttpClient();
				statuCode = client.executeMethod(m);
			} catch (HttpException e) {
				statuCode = 11000;
				exception+=":"+ e.toString();
			} catch (IOException e) {
				statuCode = 111111;
				exception+=":"+ e.toString();
			}
			String str = String.valueOf(statuCode);
			if(responseCodes !=null && responseCodes.containsKey(str)){
				result += CODEOK ;
			}
			else result += CODEKO;
		}
		return result+exception;
	}
	@Override
	public String generateReport(String url) {
		String res = "";
		System.out.println("************* SIZE **************"+this.paths.keySet().size());
		for(Map.Entry<String, Path> entr : this.paths.entrySet()){
			Path p = entr.getValue();
			//if(p.getGet() != null && p.getGet().getParameters() != null && p.getGet().getParameters().size()!=0) System.out.println("**"+p.getGet().getParameters().get(0).getAccess());
			//url = url + entr.getKey();
			res+= "{"+httpMethod(url,p,GET)+" "+httpMethod(url,p,POST)+" "+httpMethod(url,p,PUT)+"}\n";
		}
		return res;
	}

}
