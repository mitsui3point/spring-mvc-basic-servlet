package hello.servlet.web.frontcontroller.v1;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class FrontControllerServletV1Test {

    private static final String prefixUri = "/front-controller/v1";
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void formProcessTest() throws ServletException, IOException {
        //given
        String expected = "/WEB-INF/views/new-form.jsp";
        String formUri = "/members/new-form";

        //when
        request.setRequestURI(prefixUri + formUri);
        new FrontControllerServletV1().service(request, response);
        String actual = response.getForwardedUrl();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void saveProcessTest() throws ServletException, IOException {
        //given
        String expected = "/WEB-INF/views/save-result.jsp";
        request.setParameter("username", "hello");
        request.setParameter("age", "20");
        String saveUri = "/members/save";

        //when
        request.setRequestURI(prefixUri + saveUri);
        new FrontControllerServletV1().service(request, response);
        String actual = response.getForwardedUrl();
        Object actualMember = request.getAttribute("member");

        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualMember).isInstanceOf(Member.class);
        assertThat(actualMember).extracting("username").isEqualTo("hello");
        assertThat(actualMember).extracting("age").isEqualTo(20);
    }

    @Test
    void membersProcessTest() throws ServletException, IOException {
        //given
        String expected = "/WEB-INF/views/members.jsp";
        Member savedMember1 = memberRepository.save(Member.builder().username("hello1").age(20).build());
        Member savedMember2 = memberRepository.save(Member.builder().username("hello2").age(21).build());
        Member[] expectedMembers = {savedMember1, savedMember2};//배열 선언시 초기화

        //when
        request.setRequestURI(prefixUri + "/members");
        new FrontControllerServletV1().service(request, response);
        String actual = response.getForwardedUrl();
        List<Member> actualMembers = (ArrayList<Member>) request.getAttribute("members");

        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualMembers).containsExactly(expectedMembers);
    }

    @Test
    void notExistUriTest() throws ServletException, IOException {
        //given

        //when
        request.setRequestURI(prefixUri + "/not-exists-uri");
        new FrontControllerServletV1().service(request, response);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clear();
    }
}
