package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String > paramMap) {
        Member paramMember = Member.builder()
                .username(paramMap.get("username"))
                .age(Integer.parseInt(paramMap.get("age")))
                .build();

        Member savedMember = memberRepository.save(paramMember);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", savedMember);

        return mv;
    }
}
