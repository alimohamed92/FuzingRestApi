package http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import io.swagger.models.parameters.Parameter;
import model.MyHttpResponse;

public class MyHttp {


	public  MyHttpResponse get(String url,List<Parameter> params){
		MyHttpResponse res = new MyHttpResponse();
		try {
			HttpClient client = new HttpClient();
			GetMethod method = new GetMethod(url);
			for(Parameter param : params){
				HttpMethodParams paramsHttp = new HttpMethodParams();
				paramsHttp.setParameter(param.getName(),null);
				method.setParams(paramsHttp);
			}
			res.setCode(client.executeMethod(method));
			res.setContenu(method.getResponseBodyAsString());
		} catch (HttpException e) {
			res.setCode(11000);
			res.setContenu("HttpException");
		} catch (Exception e) {
			res.setCode(11111);
			res.setContenu("OException");
		}
		return res;
	}

	public  MyHttpResponse post(String url,List<Parameter> params){
		MyHttpResponse res = new MyHttpResponse();
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);
			for(Parameter param : params){
				HttpMethodParams paramsHttp = new HttpMethodParams();
				paramsHttp.setParameter(param.getName(),null);
				method.setParams(paramsHttp);
			}
			res .setCode(client.executeMethod(method));
			res.setContenu(method.getResponseBodyAsString());
		} catch (HttpException e) {
			res.setCode(11000);
			res.setContenu("HttpException");
		} catch (Exception e) {
			res.setCode(11111);
			res.setContenu("OException");
		}
		return res;

	}

	public  MyHttpResponse put(String url,List<Parameter> params){
		MyHttpResponse res = new MyHttpResponse();
		try {
			HttpClient client = new HttpClient();
			PutMethod method = new PutMethod(url);
			for(Parameter param : params){
				HttpMethodParams paramsHttp = new HttpMethodParams();
				paramsHttp.setParameter(param.getName(),null);
				method.setParams(paramsHttp);
			}
			res .setCode(client.executeMethod(method));
			res.setContenu(method.getResponseBodyAsString());
		} catch (HttpException e) {
			res.setCode(11000);
			res.setContenu("HttpException");
		} catch (Exception e) {
			res.setCode(11111);
			res.setContenu("OException");
		}
		return res;

	}
	
	public  MyHttpResponse httpReq(String url, String method){
		List<Parameter> params = new ArrayList<Parameter>();
		if(method.equalsIgnoreCase("GET")){
			return get(url, params);
		}
		if(method.equalsIgnoreCase("POST")){
			return post(url, params);
		}
		if(method.equalsIgnoreCase("PUT")){
			return put(url, params);
		}
		return null;
	}
	public boolean test(String url,List<Parameter> params){
		return true;
	}

}
