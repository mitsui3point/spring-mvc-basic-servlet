package hello.servlet.web.servlet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberFormServletTest {

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
        String expectedContent = new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append(" <meta charset=\"UTF-8\">\n")
                .append(" <title>Title</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<form action=\"/servlet/members/save\" method=\"post\">\n")
                .append(" username: <input type=\"text\" name=\"username\" />\n")
                .append(" age: <input type=\"text\" name=\"age\" />\n")
                .append(" <button type=\"submit\">전송</button>\n")
                .append("</form>\n")
                .append("</body>\n")
                .append("</html>\n")
                .toString();
        //when
        new MemberFormServlet().service(request, response);
        //then
        assertThat(response.getContentType()).isEqualTo("text/html;charset=utf-8");
        assertThat(response.getContentAsString()).isEqualTo(expectedContent);
    }
}
