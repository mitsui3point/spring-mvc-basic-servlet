package hello.servlet.basic.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class RequestBodyJsonServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void serviceTest() throws ServletException, IOException {
        //given
        String json = "{\"username\":\"hello\",\"age\":\"20\"}";
        //when
        request.setContent(json.getBytes(StandardCharsets.UTF_8));
        new RequestBodyJsonServlet().service(request, response);
        //then
        Assertions.assertThat(response.getContentAsString()).isEqualTo("username: hello, age: 20");
    }
}
