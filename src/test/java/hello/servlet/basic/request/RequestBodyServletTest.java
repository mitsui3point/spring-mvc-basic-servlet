package hello.servlet.basic.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RequestBodyServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void serviceTest() throws IOException, ServletException {
        //given
        //String jsonString = "{\"username\":\"hello\"}";
        String stringBody = "hello";
        request.setContent(stringBody.getBytes(StandardCharsets.UTF_8));

        //when
        new RequestBodyServlet().service(request, response);

        //then
        assertThat(response.getContentAsString()).isEqualTo("messageBody = " + stringBody);
    }

}
