package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SpringMemberControllerV2Test {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void formTest() throws Exception {
        //given
        //when
        ResultActions perform = mvc.perform(get("/springmvc/v2/members/new-form"));
        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/new-form.jsp"));
    }

    @Test
    void saveTest() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "hello");
        params.add("age", "20");
        //when
        ResultActions perform = mvc.perform(
                post("/springmvc/v2/members/save")
                        .characterEncoding("utf-8")
                        .contentType("x-www-form-urlencoded")
                        .params(params));

        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/save-result.jsp"));
    }

    @Test
    void listTest() throws Exception {
        //given
        List<Member> members = Arrays.asList(
                memberRepository.save(Member.builder().username("hello1").age(20).build()),
                memberRepository.save(Member.builder().username("hello2").age(21).build())
        );
        //when
        ResultActions perform = mvc.perform(get("/springmvc/v2/members"));

        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("members", members))
                .andExpect(forwardedUrl("/WEB-INF/views/members.jsp"));

    }

    @AfterEach
    void tearDown() {
        memberRepository.clear();
    }
}
