package hello.servlet.basic.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ResponseHtmlServletTest {
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
        new ResponseHtmlServlet().service(request, response);
        //then
        assertThat(response.getContentType()).isEqualTo("text/html;charset=utf-8");
        assertThat(response.getContentAsString()).isEqualTo(new StringBuilder()
                .append("<html>").append("\n")
                .append("<body>").append("\n")
                .append("  <div>안녕?</div>").append("\n")
                .append("</body>").append("\n")
                .append("</html>").append("\n").toString());
    }
}
