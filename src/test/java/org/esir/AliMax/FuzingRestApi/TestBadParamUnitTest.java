package org.esir.AliMax.FuzingRestApi;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;
import model.MyHttpResponse;
import org.esir.AliMax.FuzingRestApi.oracle.TestBadParam;
import org.mockito.Mockito;

import http.MyHttp;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import junit.framework.TestCase;

public class TestBadParamUnitTest extends TestCase {
	private Swagger swagger;
	
	public TestBadParamUnitTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		swagger = new SwaggerParser().read("swagger.json");
	}

	
	public void testBAdOkGET(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://personalization/thresholds/aar";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(404);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.get(url, p.getGet().getParameters())).thenReturn(value);
		Map<String,Path> paths = new HashMap<String, Path>();
		paths.put("/personalization/thresholds/aar", p);
		TestBadParam clt = new TestBadParam(paths);
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.testGetBadParam(1, "http:/"));
		assertEquals(clt.getRapportGet(), clt.interpreteResult(true, "GET", url));
		
	}
	
	public void testBadKOGET(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://personalization/thresholds/aar";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.get(url, p.getGet().getParameters())).thenReturn(value);
		Map<String,Path> paths = new HashMap<String, Path>();
		paths.put("/personalization/thresholds/aar", p);
		TestBadParam clt = new TestBadParam(paths);
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.testGetBadParam(1, "http:/"));
		assertEquals(clt.getRapportGet(), clt.interpreteResult(false, "GET", url));
	}
	
	public void testBAdOkPUT(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://personalization/thresholds/aar";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(404);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.put(url, p.getPut().getParameters())).thenReturn(value);
		Map<String,Path> paths = new HashMap<String, Path>();
		paths.put("/personalization/thresholds/aar", p);
		TestBadParam clt = new TestBadParam(paths);
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.testPutBadParam(1, "http:/"));
		assertEquals(clt.getRapportPut(), clt.interpreteResult(true, "PUT", url));
		
	}
	
	public void testBadKOPUT(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://personalization/thresholds/aar";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.put(url, p.getPut().getParameters())).thenReturn(value);
		Map<String,Path> paths = new HashMap<String, Path>();
		paths.put("/personalization/thresholds/aar", p);
		TestBadParam clt = new TestBadParam(paths);
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.testPutBadParam(1, "http:/"));
		assertEquals(clt.getRapportPut(), clt.interpreteResult(false, "PUT", url));
	}
	
	public void testBAdOkPOST(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://sccr/periods/{period}";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(404);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/sccr/periods/{period}");
		Mockito.when(mockMyHttp.post(url, p.getPost().getParameters())).thenReturn(value);
		Map<String,Path> paths = new HashMap<String, Path>();
		paths.put("/sccr/periods/{period}", p);
		TestBadParam clt = new TestBadParam(paths);
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.testPostBadParam(1, "http:/"));
		assertEquals(clt.getRapportPost(), clt.interpreteResult(true, "POST", url));
	}
	
	public void testBadKOPOST(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://sccr/periods/{period}";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/sccr/periods/{period}");
		Mockito.when(mockMyHttp.post(url, p.getPost().getParameters())).thenReturn(value);
		Map<String,Path> paths = new HashMap<String, Path>();
		paths.put("/sccr/periods/{period}", p);
		TestBadParam clt = new TestBadParam(paths);
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.testPostBadParam(1, "http:/"));
		assertEquals(clt.getRapportPost(), clt.interpreteResult(false, "POST", url));
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
