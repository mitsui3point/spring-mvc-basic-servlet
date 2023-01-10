package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member paramMember = Member.builder()
                .username(request.getParameter("username"))
                .age(Integer.parseInt(request.getParameter("age")))
                .build();

        Member savedMember = memberRepository.save(paramMember);

        request.setAttribute("member", savedMember);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
