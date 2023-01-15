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

class incTest {


    @InjectMocks
    private incServlet servlet;

    HttpServletRequest request=null;
    HttpServletResponse response=null;

    @BeforeEach
    void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class); 
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void incTestOk() throws ServletException, IOException {
        when(request.getParameter("p_id")).thenReturn("2");
        servlet.doPost(request,response);
        assertEquals("incrementOk",request.getParameter("incrementOk"));
    }

    @Test
    void incTestError() throws ServletException, IOException {
        when(request.getParameter("p_id")).thenReturn("30");
        servlet.doPost(request,response);
        assertEquals("errorIncrement",request.getParameter("errorIncrement"));
    }

}
