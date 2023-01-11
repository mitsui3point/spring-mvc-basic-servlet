package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        Member paramMember = Member.builder()
                .username(paramMap.get("username"))
                .age(Integer.parseInt(paramMap.get("age")))
                .build();

        model.put("member", memberRepository.save(paramMember));
        return "save-result";
    }
}
