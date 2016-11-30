package org.esir.AliMax.FuzingRestApi.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

import io.swagger.models.Path;

public class TestBadUrl extends ModelTest{
	private int nbBadUrl;

	public TestBadUrl( int nbBadUrl,Map<String,Path> paths) {
		super("Bad URL",paths);
		this.nbBadUrl = nbBadUrl;
	}

	@Override
	public String generateReport(String url) {
		String res = "{$";
		List<String> urls = new ArrayList<String>();
		urls.addAll(this.paths.keySet());
		for(int i = 0; i < this.nbBadUrl; i++ ){
			int index = (int) (Math.random()*urls.size()); 
			String badUrl = urls.get(index)+index;
			while(urls.contains(badUrl)){
				badUrl+=Math.random()*urls.size();
			}
			badUrl= url+badUrl;
			//badUrl = tmp;
			List<HttpMethod> methods = new ArrayList<HttpMethod>();
			try {
				methods.add(new GetMethod(badUrl));
				methods.add(new PostMethod(badUrl));
				methods.add(new PutMethod(badUrl));
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			for(HttpMethod m : methods){
				int statuCode = 0;
				try {
					HttpClient client = new HttpClient();
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
				else{
					resTest = "{KO!}--->"+statuCode;
				}
				res+=" Méthod "+m.getName()+" BAD URL : "+badUrl+" Résultat TEST : "+resTest+"}\n{";
			}
		}
		return res;
	}

}
