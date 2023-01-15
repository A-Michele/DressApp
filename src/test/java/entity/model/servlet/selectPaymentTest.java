package entity.model.servlet;

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

class selectPaymentTest extends TestCase {

	@InjectMocks
    private selectPayment servlet;

    HttpServletRequest request=null;
    HttpServletResponse response=null;
	
	
	@BeforeEach
	protected void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class); 
        MockitoAnnotations.initMocks(this);
	}
	@Test
	void selectPaymentOk() throws ServletException, IOException {
		when(request.getParameter("o_id")).thenReturn("1");
        servlet.doGet(request,response);
        assertEquals("Ordine Effettuato",request.getParameter("msg"));
	}
	
	@Test
	void selectPaymentError() throws ServletException, IOException {
		when(request.getParameter("o_id")).thenReturn("100");
        servlet.doGet(request,response);
        assertEquals("Id Ordine Inesistente",request.getParameter("msg"));
	}

}
