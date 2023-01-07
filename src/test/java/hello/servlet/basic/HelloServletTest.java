package hello.servlet.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HelloServletTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void serviceTest() throws Exception {
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when
        when(request.getParameter("username")).thenReturn("kim");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new HelloServlet().service(request, response);

        //then
        assertThat(output.toString()).isEqualTo("HelloServlet.service" +
                "\nrequest = " + request +
                "\nresponse = " + response +
                "\nusername = kim\n");

        verify(request, atLeast(1)).getParameter("username");
        assertThat(stringWriter.toString()).isEqualTo("hello kim");

    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
        output.reset();
    }
}
