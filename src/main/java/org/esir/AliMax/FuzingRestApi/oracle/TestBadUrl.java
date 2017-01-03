package org.esir.AliMax.FuzingRestApi.oracle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import http.MyHttp;
import io.swagger.models.Path;
import model.MyHttpResponse;

public class TestBadUrl extends ModelTest{
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private int nbBadUrl;
	private MyHttp myHttp; 
	public TestBadUrl( int nbBadUrl,Map<String,Path> paths) {
		super("Bad URL",paths);
		this.nbBadUrl = nbBadUrl;
		this.myHttp = new MyHttp();
	}

	@Override
	public String generateReport(String baseUrl) {
		String res="";
		List<String> urls = new ArrayList<String>();
		urls.addAll(this.paths.keySet());
		List<String> badUrls = generateBadUrls(urls);
		for(String badUrl : badUrls){
			badUrl= baseUrl+badUrl;
			List<String> methods = new ArrayList<String>();
			try {
				methods.add(GET);
				methods.add(POST);
				methods.add(PUT);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			for(String m : methods){
				MyHttpResponse reponse = myHttp.httpReq(badUrl, m);
				this.motif = String.valueOf(reponse.getCode());
				if(reponse.getCode() == 404){
					res+=this.interpreteResult(true, m, badUrl);
				}
				else {
					res+=this.interpreteResult(false, m, badUrl);
				}
			}
		}
		return res;
	}

	public List<String> generateBadUrls(List<String> urls) {
		List<String> res = new ArrayList<String>();
		for(int i = 0; i < this.nbBadUrl; i++ ){
			int index = (int) (Math.random()*urls.size()); 
			String badUrl = urls.get(index)+index;
			while(urls.contains(badUrl)){
				badUrl+=Math.random()*urls.size();
			}
			res.add(badUrl);
		}
		return res;
	}

}
