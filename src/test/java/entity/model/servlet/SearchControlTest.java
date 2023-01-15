package entity.model.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import junit.framework.TestCase;

class SearchControlTest extends TestCase {

	@InjectMocks
    private AddToCartServlet servlet;

    HttpServletRequest request=null;
    HttpServletResponse response=null;
	
	
	@BeforeEach
	protected void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class); 
        MockitoAnnotations.initMocks(this);
	}

	@Test
	void SearchControlTestOk() throws ServletException, IOException {
		when(request.getParameter("search")).thenReturn("Nike");
        servlet.doGet(request,response);
        assertEquals("Trovato",request.getParameter("msg"));
	}

	@Test
	void SearchControlTestError() throws ServletException, IOException {
		when(request.getParameter("search")).thenReturn("123");
        servlet.doGet(request,response);
        assertEquals("Errore Formato",request.getParameter("msg"));
	}
	
}
