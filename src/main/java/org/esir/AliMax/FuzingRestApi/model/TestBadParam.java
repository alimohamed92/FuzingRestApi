package org.esir.AliMax.FuzingRestApi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.models.Path;
import io.swagger.models.parameters.Parameter;

public class TestBadParam extends ModelTest {

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private MyHttp myHttp; 
	private String rapportGet;
	private String rapportPost;
	private String rapportPut;
	public TestBadParam(Map<String, Path> paths) {
		super("BAD PARAM ", paths);
		this.rapportGet = "";
		this.rapportPost = "";
		this.rapportPut = "";
		this.myHttp = new MyHttp();
	}

	private List<String> selectUrl(int nbUrl){
		List<String> res = new ArrayList<String>();
		res.addAll(this.paths.keySet());
		int fromIndex = (int) (Math.random()*(res.size()/2));
		if(nbUrl > res.size()/2){
			nbUrl = res.size()/2;
		}
		int toIndex = fromIndex+nbUrl;
		res =res.subList(fromIndex, toIndex);
		return res;
	}

	public boolean testGetBadParam(int nbUrl,String baseUrl){
		boolean res = true;
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getGet() != null && p.getGet().getParameters() != null && p.getGet().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getGet().getParameters().size()-1));
				Parameter param = p.getGet().getParameters().get(index );
				String badParam = param.getName()+"BAD"; //param.getPattern()
				param.setName(badParam);
				List<Parameter> params = new ArrayList<Parameter>();
				params .add(param);
				url = baseUrl + url;
				int statuCode = myHttp.get(url, params).getCode();
				//String str = new String(m.getResponseBody());
				//					 JSONObject jsonObj = new JSONObject(str);
				//					 Object o = jsonObj.get("code");
				//					 Swagger swagger = new SwaggerParser().read("swagger1.json");
				//					 Model model = swagger.getDefinitions().get("PriceEstimate");
				//					System.out.println("$$$$$ "+jsonObj+" comparType : "+ComparateurType.verifierTypeRef(model, jsonObj)+jsonObj.getClass().getSimpleName());
				this.motif = String.valueOf(statuCode)+" Param = "+badParam;
				if(statuCode == 404){
					this.rapportGet+=this.interpreteResult(true, GET, url);
				}
				else {
					res = false;
					this.rapportGet+=this.interpreteResult(false, GET, url);
				}
			}
		}
		return res;

	}

	public boolean testPostBadParam(int nbUrl,String baseUrl){
		boolean res = true;
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getPost() != null && p.getPost().getParameters() != null && p.getPost().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getPost().getParameters().size()-1));
				Parameter param = p.getPost().getParameters().get(index );
				String badParam = param.getName()+"BAD"; //param.getPattern()
				param.setName(badParam);
				List<Parameter> params = new ArrayList<Parameter>();
				params .add(param);
				url = baseUrl + url;
				int statuCode = myHttp.post(url, params).getCode();
				//si on a un 404 correspondant au résultat attendu, on laisse le res à sa valeur précedente 
				//(true si on a tjrs eu 404)
				this.motif = String.valueOf(statuCode)+" Param = "+badParam;
				if(statuCode == 404){
					this.rapportPost+= this.interpreteResult(true, POST, url);
				}
				else {
					res = false;
					this.rapportPost += this.interpreteResult(false, POST, url);
				}
			}
		}
		return res;

	}

	public boolean testPutBadParam(int nbUrl,String baseUrl){
		boolean res = true;
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getPut() != null && p.getPut() .getParameters() != null && p.getPut().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getPut().getParameters().size()-1));
				Parameter param = p.getPut().getParameters().get(index );
				String badParam = param.getName()+"BAD"; //param.getPattern()
				param.setName(badParam);
				List<Parameter> params = new ArrayList<Parameter>();
				params .add(param);
				url = baseUrl + url;
				int statuCode = myHttp.put(url, params).getCode();
				this.motif = String.valueOf(statuCode)+" Param = "+badParam;
				if(statuCode == 404){
					this.rapportPut += this.interpreteResult(true, PUT, url);
				}
				else {
					res = false;
					this.rapportPut += this.interpreteResult(false, PUT, url);
				}
			}
		}
		return res;

	}
	@Override
	public String generateReport(String url) {
		this.testGetBadParam(5,url);
		this.testPostBadParam(5, url);
		this.testPutBadParam(5, url);
		return this.rapportGet+this.rapportPost+this.rapportPut;
	}

}
