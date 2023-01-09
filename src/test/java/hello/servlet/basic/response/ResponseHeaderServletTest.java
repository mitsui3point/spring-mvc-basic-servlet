package hello.servlet.basic.response;

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
public class ResponseHeaderServletTest {
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
        //when
        new ResponseHeaderServlet().service(request, response);
        //then
        assertThat(response.getHeader("Content-Type")).isEqualTo("text/plain;charset=utf-8");
        assertThat(response.getHeader("Cache-Control")).isEqualTo("no-cache, no-store, must-revalidate");
        assertThat(response.getHeader("Pragma")).isEqualTo("no-cache");
        assertThat(response.getHeader("my-header")).isEqualTo("hello");

        assertThat(response.getContentLength()).isEqualTo(2);
        assertThat(response.getCookie("myCookie").getValue()).isEqualTo("good");
        assertThat(response.getCookie("myCookie").getMaxAge()).isEqualTo(60);

        assertThat(response.getHeader("Location")).isEqualTo("/basic/hello-form.html");
    }
}
