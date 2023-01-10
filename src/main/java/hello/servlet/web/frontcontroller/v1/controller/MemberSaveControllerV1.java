package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member paramMember = Member.builder()
                .username(request.getParameter("username"))
                .age(Integer.parseInt(request.getParameter("age")))
                .build();

        Member savedMember = memberRepository.save(paramMember);

        request.setAttribute("member", savedMember);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
