package org.esir.AliMax.FuzingRestApi;

import static org.mockito.Mockito.mock;


import org.esir.AliMax.FuzingRestApi.oracle.VerifyCode;
import org.mockito.Mockito;

import http.MyHttp;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import junit.framework.TestCase;
import model.MyHttpResponse;

public class TypeRetourUnitTest extends TestCase {
	private Swagger swagger;
	
	public TypeRetourUnitTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		swagger = new SwaggerParser().read("swagger1.json");
	}

	public void testcodeOkGET(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://test";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/calculation/mode/aar");
		Mockito.when(mockMyHttp.get(url, p.getGet().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.verifierCodeRetour(url, p, "GET"));
		
	}
	
	public void testcodeKOGET(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://test";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(404);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/calculation/mode/aar");
		Mockito.when(mockMyHttp.get(url, p.getGet().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.verifierCodeRetour(url, p, "GET"));
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
