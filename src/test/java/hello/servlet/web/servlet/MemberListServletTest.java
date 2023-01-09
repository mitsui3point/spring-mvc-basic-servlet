package hello.servlet.web.servlet;

import hello.servlet.domain.Member;
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
public class MemberListServletTest {

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
        for (int i = 1; i < 6; i++) {
            memberRepository.save(Member.builder().username("member" + i).age(i).build());
        }
        //when
        new MemberListServlet().service(request, response);
        String result = response.getContentAsString();
        StringBuilder expectedBuilder = new StringBuilder()
                .append("<html>")
                .append("<head>")
                .append(" <meta charset=\"UTF-8\">")
                .append(" <title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append("<a href=\"/index.html\">메인</a>")
                .append("<table>")
                .append(" <thead>")
                .append(" <th>id</th>")
                .append(" <th>username</th>")
                .append(" <th>age</th>")
                .append(" </thead>")
                .append(" <tbody>");
        for (Member member : memberRepository.findAll()) {
            expectedBuilder
                    .append(" <tr>")
                    .append(" <td>" + member.getId() + "</td>")
                    .append(" <td>" + member.getUsername() + "</td>")
                    .append(" <td>" + member.getAge() + "</td>")
                    .append(" </tr>");
        }
        expectedBuilder
                .append(" </tbody>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        //then
        System.out.println("expected = " + expectedBuilder.toString());
        System.out.println("result = " + result);
        assertThat(result).isEqualTo(expectedBuilder.toString());
    }

    @AfterEach
    void tearDown() {
        memberRepository.clear();
    }
}
