package org.esir.AliMax.FuzingRestApi.oracle;

import java.util.Map;

import http.MyHttp;
import io.swagger.models.Path;
import io.swagger.models.Response;
import model.MyHttpResponse;

public class VerifyCode extends ModelTest{
	private final String GET ="GET";
	private final String POST ="POST";
	private final String PUT ="PUT";
	private MyHttp myHttp; 
	public VerifyCode(Map<String,Path> paths) {
		super("Verif code de retour", paths);
		this.myHttp = new MyHttp();
	}

	public  boolean verifierCodeRetour (String url, Path p, String method) {
		int statuCode = 0;
		boolean res = false;
		Map<String, Response> responseCodes = null;
		io.swagger.models.Operation operation = null;
		MyHttpResponse reponse = new MyHttpResponse();
		 if( method.equals(GET)){
			 operation = p.getGet();
			 responseCodes = p.getGet().getResponses();
			 reponse = myHttp.get(url, p.getGet().getParameters());
		 }
		if(method.equals(POST)){
			operation = p.getPost();
			reponse = myHttp.post(url, p.getPost().getParameters());
			responseCodes = p.getPost().getResponses();
		}
		else if(method.equals(PUT)){
			operation = p.getPut();
			reponse = myHttp.put(url, p.getPut().getParameters());
			responseCodes = p.getPut().getResponses();
		}
		if( operation != null ){
			statuCode = reponse.getCode();
			String str = String.valueOf(statuCode);
			if(responseCodes !=null && responseCodes.containsKey(str)){
				res = true;
			}
			else {
				this.motif = "code retour ("+statuCode+") != code attendu";
				res = false;
			}
		}
		return res;
	}
	@Override
	public String generateReport(String url) {
		String res = "";
		String baseUrl = url;
		for(Map.Entry<String, Path> entr : this.paths.entrySet()){
			Path p = entr.getValue();
			url = baseUrl + entr.getKey();
			if(p.getGet()!=null){
				System.out.println(url);
				res+=this.interpreteResult(verifierCodeRetour(url,p,GET), GET, url);
			}
			if(p.getPost() !=null){
				res+=this.interpreteResult(verifierCodeRetour(url,p,POST), POST, url);
			}
			if(p.getPut() !=null){
				res+=this.interpreteResult(verifierCodeRetour(url,p,PUT), PUT, url);
			}
		}
		return res;
	}

	public MyHttp getMyHttp() {
		return myHttp;
	}

	public void setMyHttp(MyHttp myHttp) {
		this.myHttp = myHttp;
	}

}
