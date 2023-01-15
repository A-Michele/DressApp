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

class AddToCartServletTest {


    @InjectMocks
    private AddToCartServlet servlet;

    HttpServletRequest request=null;
    HttpServletResponse response=null;

    @BeforeEach
    void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class); 
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void AddToCartTestOk() throws ServletException, IOException {
        when(request.getParameter("p_id")).thenReturn("1");
        servlet.doGet(request,response);
        assertEquals("AddCartOk",request.getParameter("AddCartOk"));
    }

    @Test
    void AddToCartTestError() throws ServletException, IOException {
        when(request.getParameter("p_id")).thenReturn("99");
        servlet.doGet(request,response);
        assertEquals("errorAdd",request.getParameter("errorAdd"));
    }

}
