package hello.servlet.web.servletmvc;

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
public class MvcMemberSaveServletTest {

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
        String expected = "/WEB-INF/views/save-result.jsp";
        request.setParameter("username", "hello");
        request.setParameter("age", "20");
        //when
        new MvcMemberSaveServlet().service(request, response);
        String actual = response.getForwardedUrl();
        Object actualMember = request.getAttribute("member");
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualMember).isInstanceOf(Member.class);
        assertThat(actualMember).extracting("username").isEqualTo("hello");
        assertThat(actualMember).extracting("age").isEqualTo(20);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clear();
    }
}
