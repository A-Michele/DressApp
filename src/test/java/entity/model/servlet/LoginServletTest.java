package entity.model.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class LoginServletTest {

	
	@InjectMocks
	private LoginServlet servlet;
	
	HttpServletRequest request=null;
	HttpServletResponse response=null;
	
	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class); 
        MockitoAnnotations.initMocks(this);
	}

	@Test
	void LoginTestOk() throws ServletException, IOException {
		when(request.getParameter("login-email")).thenReturn("josegiammarino@gmail.com");  
		when(request.getParameter("login-password")).thenReturn("jose12");  
		
		servlet.doPost(request,response);
		assertEquals("LoginOk",request.getParameter("LoginOk"));
	}
	
	@Test
	void LoginTestError() throws ServletException, IOException {
		when(request.getParameter("login-email")).thenReturn("josegiammarino@gmail.com");  
		when(request.getParameter("login-password")).thenReturn("cddsv");  
		servlet.doPost(request,response);
		assertEquals("errorLogin",request.getParameter("errorLogin"));
	}

}
