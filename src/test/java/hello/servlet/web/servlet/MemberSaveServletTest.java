package hello.servlet.web.servlet;

import hello.servlet.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberSaveServletTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void serviceTest() throws ServletException, IOException {
        //given
        request.setParameter("username", "hello");
        request.setParameter("age", "20");
        //when
        new MemberSaveServlet().service(request, response);
        String result = response.getContentAsString();
        String expected = new StringBuilder()
                .append("<html>\n")
                .append("<head>\n")
                .append(" <meta charset=\"UTF-8\">\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("성공\n")
                .append("<ul>\n")
                .append(" <li>id=1</li>\n")
                .append(" <li>username=hello</li>\n")
                .append(" <li>age=20</li>\n")
                .append("</ul>\n")
                .append("<a href=\"/index.html\">메인</a>\n")
                .append("</body>\n")
                .append("</html>").toString();
        //then
        assertThat(result).isEqualTo(expected);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clear();
    }
}
