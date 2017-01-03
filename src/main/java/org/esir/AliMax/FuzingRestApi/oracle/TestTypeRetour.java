package org.esir.AliMax.FuzingRestApi.oracle;

import java.util.Map;
import org.json.JSONObject;

import http.MyHttp;
import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Response;
import model.MyHttpResponse;

public class TestTypeRetour extends ModelTest {
	private static final String ERROR = "error";
	private static final String REF = "ref";
	private final String GET ="GET";
	private final String POST ="POST";
	private final String PUT ="PUT";
	private MyHttp myHttp; 

	private Map<String, Model> defs; 
	public TestTypeRetour(Map<String, Path> paths,Map<String, Model> defs ) {
		super("Test de type de retour", paths);
		this.defs = defs;
		this.myHttp = new MyHttp();
	}

	public boolean verifierType (String url, Path p, String method) {
		boolean resTestType = false;
		MyHttpResponse reponse = new MyHttpResponse();
		io.swagger.models.Operation operation = null;
		String typeRetour = "";
		if( method.equals(GET)){
			operation = p.getGet();
			reponse = myHttp.get(url, p.getGet().getParameters());
			Response tmp = p.getGet().getResponses().get(String.valueOf(reponse.getCode()));
			if(tmp !=null){
				typeRetour = tmp.getSchema().getType();
			}
			else {
				typeRetour = ERROR;
			}
		}
		else if(method.equals(POST)){
			operation = p.getPost();
			reponse = myHttp.post(url, p.getPost().getParameters());
			Response tmp = p.getPost().getResponses().get(reponse.getCode());
			if(tmp !=null){
				typeRetour = tmp.getSchema().getType();
			}
			else {
				typeRetour = ERROR;
			}
		}
		else if(method.equals(PUT)){
			operation = p.getPut();
			reponse = myHttp.put(url, p.getPut().getParameters());
			Response tmp = p.getPut().getResponses().get(reponse.getCode());
			if(tmp !=null){
				typeRetour = tmp.getSchema().getType();
			}
			else {
				typeRetour = ERROR;
			}
		}
		if( operation != null ){
			if(typeRetour.equals(ERROR)){
				return false;
			}
			else if(typeRetour.equalsIgnoreCase(REF)){
				try {
					JSONObject json = new JSONObject(reponse.getContenu());
					for(Model m : this.defs.values()){
						resTestType = ComparateurType.verifierTypeRef(m, json);
						if(resTestType) break;
					}
					if(!resTestType) this.motif = "type ref spécifié != type donnée renvoyée";
				} catch (Exception e) {
					resTestType = false;
					this.motif = "erreur de conversion json";
				}
			}
			else{
				resTestType = ComparateurType.verifierTypePrim(typeRetour, reponse.getContenu());
				if(!resTestType) this.motif = "type("+typeRetour+")spécifié != type donnée renvoyée";
			}
		}

		return resTestType;
	}

	@Override
	public String generateReport(String url) {
		String res = "";
		String baseUrl = url;
		int i = 0;
		for(Map.Entry<String, Path> entr : this.paths.entrySet()){
			if(i>9) break;
			Path p = entr.getValue();
			url = baseUrl + entr.getKey();
			if(p.getGet()!=null){
				res+=this.interpreteResult(verifierType(url,p,GET), GET, url);
			}
			if(p.getPost() !=null){
				res+=this.interpreteResult(verifierType(url,p,POST), POST, url);
			}
			if(p.getPut() !=null){
				res+=this.interpreteResult(verifierType(url,p,PUT), PUT, url);
			}
			i++;
		}
		return res;
	}

}
