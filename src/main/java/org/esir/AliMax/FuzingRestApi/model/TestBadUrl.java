package org.esir.AliMax.FuzingRestApi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.models.Path;

public class TestBadUrl extends ModelTest{
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private int nbBadUrl;

	public TestBadUrl( int nbBadUrl,Map<String,Path> paths) {
		super("Bad URL",paths);
		this.nbBadUrl = nbBadUrl;
	}

	@Override
	public String generateReport(String url) {
		String res="";
		List<String> urls = new ArrayList<String>();
		urls.addAll(this.paths.keySet());
		for(int i = 0; i < this.nbBadUrl; i++ ){
			int index = (int) (Math.random()*urls.size()); 
			String badUrl = urls.get(index)+index;
			while(urls.contains(badUrl)){
				badUrl+=Math.random()*urls.size();
			}
			badUrl= url+badUrl;
			List<String> methods = new ArrayList<String>();
			try {
				methods.add(GET);
				methods.add(POST);
				methods.add(PUT);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			for(String m : methods){
				MyHttpResponse reponse = MyHttp.httpReq(badUrl, m);
				String resTest = "";
				String classColor = "";
				if(reponse.getCode() == 404){
					resTest = "OK";
					classColor = "class=\"hidden-xs bg-success\"";
				}
				else {
					resTest = "KO";
					classColor = "class=\"hidden-xs bg-danger\"";
				}
				res += "<tr "+classColor+">";
				res+="<td>"+this.getTestName()+" </td><td> "+m+"</td><td>"+badUrl+" </td><td>  "+resTest+"</td><td>"+reponse.getCode()+"</td></tr>\n";
			}
		}
		return res;
	}

}
