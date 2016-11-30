package org.esir.AliMax.FuzingRestApi.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import io.swagger.models.Path;
import io.swagger.models.parameters.Parameter;

public class TestBadParam extends ModelTest {
	
	public TestBadParam(Map<String, Path> paths) {
		super("BAD PARAM ", paths);
		// TODO Auto-generated constructor stub
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

	private String get(int nbUrl,String baseUrl){
		String res = "******BAD PARAM GET METHODE***** \n";
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getGet() != null && p.getGet().getParameters() != null && p.getGet().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getGet().getParameters().size()-1));
				Parameter param = p.getGet().getParameters().get(index );
				String badParam = param.getName()+"bad"; //param.getPattern()
				int statuCode = 0;
				try {
					url = baseUrl + url;
					HttpClient client = new HttpClient();
					//System.out.println("URL!!!!!!!!!!!! "+url);
					HttpMethod m = new GetMethod(url);
					HttpMethodParams params = new HttpMethodParams();
					params.setParameter(badParam, "");
					m.setParams(params);
					statuCode = client.executeMethod(m);
				} catch (HttpException e) {
					statuCode = 11000;
					System.out.println(e.toString());
				} catch (IOException e) {
					statuCode = 111111;
					System.out.println(e.toString());
				}catch (Exception e) {
					statuCode = 111000;
					System.out.println(e.toString());
				}
				String resTest = ""; 
				if(statuCode == 404){
					resTest = "{OK}";
				}
				else {
					resTest = "{KO}--->"+statuCode;
				}
				res+="{$ GET "+url+" BAD_PARAM : "+badParam+" Résultat TEST : "+resTest+"}\n";
			}
		}
		return res;

	}
	
	private String post(int nbUrl,String baseUrl){
		String res = "******BAD PARAM POST METHODE***** \n";
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getPost() != null && p.getPost() .getParameters() != null && p.getPost().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getPost().getParameters().size()-1));
				Parameter param = p.getPost().getParameters().get(index );
				String badParam = param.getName()+"bad"; //param.getPattern()
				int statuCode = 0;
				try {
					url = baseUrl + url;
					HttpClient client = new HttpClient();
					HttpMethod m = new PostMethod(url);
					HttpMethodParams params = new HttpMethodParams();
					params.setParameter(badParam, "");
					m.setParams(params);
					statuCode = client.executeMethod(m);
				} catch (HttpException e) {
					statuCode = 11000;
					System.out.println(e.toString());
				} catch (IOException e) {
					statuCode = 111111;
					System.out.println(e.toString());
				}
				String resTest = ""; 
				if(statuCode == 404){
					resTest = "{OK}";
				}
				else {
					resTest = "{KO}--->"+statuCode;
				}
				res+="{$ GET "+url+" BAD_PARAM : "+badParam+" Résultat TEST : "+resTest+"}\n";
			}
		}
		return res;

	}

	private String put(int nbUrl,String baseUrl){
		String res = "******BAD PARAM PUT METHODE***** \n";
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getPut() != null && p.getPut() .getParameters() != null && p.getPut().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getPut().getParameters().size()-1));
				Parameter param = p.getPut().getParameters().get(index );
				String badParam = param.getName()+"bad"; //param.getPattern()
				int statuCode = 0;
				try {
					url = baseUrl + url;
					HttpClient client = new HttpClient();
					HttpMethod m = new PutMethod(url);
					HttpMethodParams params = new HttpMethodParams();
					params.setParameter(badParam, "");
					m.setParams(params);
					statuCode = client.executeMethod(m);
				} catch (HttpException e) {
					statuCode = 11000;
					System.out.println(e.toString());
				} catch (IOException e) {
					statuCode = 111111;
					System.out.println(e.toString());
				}
				String resTest = ""; 
				if(statuCode == 404){
					resTest = "{OK}";
				}
				else {
					resTest = "{KO}--->"+statuCode;
				}
				res+="{$ GET "+url+" BAD_PARAM : "+badParam+" Résultat TEST : "+resTest+"}\n";
			}
		}
		return res;

	}
	@Override
	public String generateReport(String url) {
		return this.get(5,url) + this.post(5, url)+this.put(5, url);
	}

}
