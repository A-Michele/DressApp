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

class PaymentServletTest {

    @InjectMocks
    private PaymentServlet servlet;

    HttpServletRequest request=null;
    HttpServletResponse response=null;

    @BeforeEach
    void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class); 
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void PaymentServletTestOk() throws ServletException, IOException {
        when(request.getParameter("payment-proprietario")).thenReturn("Mario Rossi");
    when(request.getParameter("payment-num")).thenReturn("1234567891019213");
    when(request.getParameter("payment-cvv")).thenReturn("132");
    when(request.getParameter("payment-scadenza")).thenReturn("05/2024");
        servlet.doPost(request,response);
        assertEquals("paymentOk",request.getParameter("paymentOk"));
    }

    @Test
    void PaymentServletDateTestError() throws ServletException, IOException {
          when(request.getParameter("payment-proprietario")).thenReturn("Mario Rossi");
    when(request.getParameter("payment-num")).thenReturn("1234567891019213");
    when(request.getParameter("payment-cvv")).thenReturn("132");
    when(request.getParameter("payment-scadenza")).thenReturn("01/05/2024");
    servlet.doPost(request,response);
        assertEquals("errorDataFormat",request.getParameter("errorDataFormat"));
    }

} 
