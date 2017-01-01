/**
 * 
 */
package org.esir.AliMax.FuzingRestApi;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.esir.AliMax.FuzingRestApi.model.MyHttp;
import org.esir.AliMax.FuzingRestApi.model.MyHttpResponse;
import org.esir.AliMax.FuzingRestApi.model.VerifyCode;
import org.mockito.Mockito;

import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import junit.framework.TestCase;

/**
 * @author AliMohamed
 *
 */
public class VerifyCodeUnitTest extends TestCase {

	private Swagger swagger;
	/**
	 * @param name
	 */
	public VerifyCodeUnitTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		swagger = new SwaggerParser().read("swagger.json");
	}
	
	public void testcodeOkGET(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://test";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.get(url, p.getGet().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.verifierCodeRetour(url, p, "GET"));
		
	}
	
	public void testcodeKOGET(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://test";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(5000);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.get(url, p.getGet().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.verifierCodeRetour(url, p, "GET"));
		
	}
	
	public void testcodeOkPost(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://testPost";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200); 
		value.setContenu("test");
		Path p = swagger.getPaths().get("/sccr/periods/{period}");
		p.getPost().addResponse("200", null);
		Mockito.when(mockMyHttp.post(url, p.getPost().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.verifierCodeRetour(url, p, "POST"));
		
	}
	
	public void testcodeKOPost(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://testpost";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(5000);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/sccr/periods/{period}");
		Mockito.when(mockMyHttp.post(url, p.getPost().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.verifierCodeRetour(url, p, "POST"));
		
	}
	
	public void testcodeOkPUT(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://test";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.put(url, p.getPut().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(clt.verifierCodeRetour(url, p, "PUT"));
		
	}
	
	public void testcodeKOPUT(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="http://test";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(5000);
		value.setContenu("test");
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.put(url, p.getPut().getParameters())).thenReturn(value);
		VerifyCode clt = new VerifyCode(swagger.getPaths());
		clt.setMyHttp(mockMyHttp);
		assertTrue(!clt.verifierCodeRetour(url, p, "PUT")); 
		
	}
	
	public void testrapport(){
		MyHttp mockMyHttp = mock(MyHttp.class);
		String url="base/http://testrapport";
		MyHttpResponse value = new MyHttpResponse();
		value.setCode(200);
		value.setContenu("test");
		Map<String, Path> paths = new HashMap<String, Path>();
		
		Path p = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.put(url, p.getPut().getParameters())).thenReturn(value);
		
		Path p2 = swagger.getPaths().get("/personalization/thresholds/aar");
		Mockito.when(mockMyHttp.get(url, p2.getGet().getParameters())).thenReturn(value);
		
		Path p3 = swagger.getPaths().get("/sccr/periods/{period}");
		Mockito.when(mockMyHttp.post(url+"POST", p3.getPost().getParameters())).thenReturn(value);
		p3.getPost().addResponse("200", null);
		paths.put("http://testrapport", p2);
		paths.put("http://testrapportPOST", p3);
		paths.put("http://testrapport", p);
		VerifyCode clt = new VerifyCode(paths);
		clt.setMyHttp(mockMyHttp); 
		String expected = clt.interpreteResult(true, "GET", url)+clt.interpreteResult(true, "PUT", url)+clt.interpreteResult(true, "POST", url+"POST");
		assertEquals(expected, clt.generateReport("base/"));
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
