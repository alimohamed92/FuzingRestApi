package org.esir.AliMax.FuzingRestApi.model;

import java.util.Map;
import org.json.JSONObject;

import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Response;

public class TestTypeRetour extends ModelTest {
	private static final String ERROR = "error";
	private static final String REF = "ref";
	private final String CODEOK = "OK";
	private final String CODEKO = "KO";
	private final String GET ="GET";
	private final String POST ="POST";
	private final String PUT ="PUT";

	private Map<String, Model> defs; 
	public TestTypeRetour(Map<String, Path> paths,Map<String, Model> defs ) {
		super("Test de type de retour", paths);
		this.defs = defs;
	}

	public String verifierType (String url, Path p, String method) {
		String res ="";
		MyHttpResponse reponse = new MyHttpResponse();
		String methodName ="";
		io.swagger.models.Operation operation = null;
		String typeRetour = "";
		String motif ="";

		if( method.equals(GET)){
			operation = p.getGet();
			reponse = MyHttp.get(url, p.getGet().getParameters());
			Response tmp = p.getGet().getResponses().get(String.valueOf(reponse.getCode()));
			if(tmp !=null){
				typeRetour = tmp.getSchema().getType();
			}
			else {
				typeRetour = ERROR;
			}
			methodName = GET;
		}
		else if(method.equals(POST)){
			operation = p.getPost();
			reponse = MyHttp.post(url, p.getPost().getParameters());
			Response tmp = p.getPost().getResponses().get(reponse.getCode());
			if(tmp !=null){
				typeRetour = tmp.getSchema().getType();
			}
			else {
				typeRetour = ERROR;
			}
			methodName = POST;
		}
		else if(method.equals(PUT)){
			operation = p.getPut();
			reponse = MyHttp.put(url, p.getPut().getParameters());
			Response tmp = p.getPut().getResponses().get(reponse.getCode());
			if(tmp !=null){
				typeRetour = tmp.getSchema().getType();
			}
			else {
				typeRetour = ERROR;
			}
			methodName = PUT;
		}
		String resTest="";
		String classColor = "";
		if( operation != null ){
			boolean resTestType = false;
			if(typeRetour.equals(ERROR)){
				motif = "mauvais code de retour";
			}
			else if(typeRetour.equalsIgnoreCase(REF)){
				try {
					JSONObject json = new JSONObject(reponse.getContenu());
					for(Model m : this.defs.values()){
						resTestType = ComparateurType.verifierTypeRef(m, json);
						if(resTestType) break;
					}
					if(!resTestType) motif = "type ref spécifié != type donnée renvoyée";
				} catch (Exception e) {
					resTestType = false;
					motif = "erreur de conversion json";
				}
			}
			else{
				resTestType = ComparateurType.verifierTypePrim(typeRetour, reponse.getContenu());
				if(!resTestType) motif = "type("+typeRetour+")spécifié != type donnée renvoyée";
			}
			if(resTestType){
				resTest += CODEOK ;
				classColor = "class=\"hidden-xs bg-success\"";
			}
			else {
				resTest += CODEKO;
				classColor = "class=\"hidden-xs bg-danger\"";
			}
		}
		res += "<tr "+classColor+">";
		res+="<td>"+this.getTestName()+" </td><td> "+methodName+"</td><td>"+url+" </td><td>  "+resTest+"</td><td>"+motif+"</td></tr>\n";

		return res;
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
				res+=verifierType(url,p,GET);
			}
			if(p.getPost() !=null){
				res+=verifierType(url,p,POST);
			}
			if(p.getPut() !=null){
				res+=verifierType(url,p,PUT);
			}
			i++;
		}
		return res;
	}

}
