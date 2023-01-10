package hello.servlet.web.servletmvc;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MvcMemberListServletTest {
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
        String expected = "/WEB-INF/views/members.jsp";
        Member savedMember1 = memberRepository.save(Member.builder().username("hello1").age(20).build());
        Member savedMember2 = memberRepository.save(Member.builder().username("hello2").age(21).build());
        Member[] expectedMembers = {savedMember1, savedMember2};//배열 선언시 초기화
        //when
        new MvcMemberListServlet().service(request, response);
        String actual = response.getForwardedUrl();
        List<Member> actualMembers = (ArrayList<Member>) request.getAttribute("members");
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualMembers).containsExactly(expectedMembers);
    }
}
