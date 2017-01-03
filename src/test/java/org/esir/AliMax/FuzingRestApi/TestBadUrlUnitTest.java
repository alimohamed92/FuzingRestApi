package org.esir.AliMax.FuzingRestApi;

import java.util.ArrayList;
import java.util.List;

import org.esir.AliMax.FuzingRestApi.oracle.TestBadUrl;

import http.MyHttp;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import junit.framework.TestCase;

public class TestBadUrlUnitTest extends TestCase {
   private MyHttp request;
   private Swagger swagger;
   private TestBadUrl badUrl;
	public TestBadUrlUnitTest(String name) {
		super(name);
	}
	
	public void setUp(){
		swagger = new SwaggerParser().read("swagger1.json");
		badUrl = new TestBadUrl(5, swagger.getPaths());
	}
	
	public void testBadUrlgeneration(){
		List<String> urls = new ArrayList<String>();
		urls.addAll(swagger.getPaths().keySet());
		List<String> badUrls = badUrl.generateBadUrls(urls);
		assertTrue(badUrls.size() <= 5);
		assertTrue(! urls.containsAll(badUrls));
		for(String url : badUrls){
			assertTrue(!urls.contains(url));
		}
	}
	

}
