/**
 * 
 */
package org.esir.AliMax.FuzingRestApi;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.esir.AliMax.FuzingRestApi.model.MyHttp;
import org.esir.AliMax.FuzingRestApi.model.MyHttpResponse;
import org.esir.AliMax.FuzingRestApi.model.VerifyCode;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import io.swagger.parser.SwaggerParser;
import junit.framework.TestCase;

/**
 * @author AliMohamed
 *
 */
public class VerifyCodeUnitTest extends TestCase {

	private VerifyCode verifCode;
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
		//verifCode = new VerifyCode(paths);
		//MockitoAnnotations.initMocks(this);
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

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
