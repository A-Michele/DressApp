package entity.model.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

 


 class TestRemoveServlet extends Mockito {
	
	
	 
	  @InjectMocks
	  private RemoveServlet servlet; 
	  
	 @Before
	 public void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }
	 
	@Test
	void TestRimozioneCappelloOK()  throws Exception{ 
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		when(request.getParameter("p_id")).thenReturn("1");  
		servlet.doGet(request,response);
		assertEquals("tuttoOk",request.getAttribute("msg"));	
	}
	
	@Test
	void TestRimozioneCappelloError()  throws Exception{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("p_id")).thenReturn("1o");
		servlet.doGet(request,response);
		assertEquals("error",request.getAttribute("msg"));	
	}


}
