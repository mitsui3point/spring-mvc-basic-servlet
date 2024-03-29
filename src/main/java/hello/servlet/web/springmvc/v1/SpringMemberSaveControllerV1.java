package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpringMemberSaveControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", memberRepository.save(
                Member.builder()
                        .username(request.getParameter("username"))
                        .age(Integer.parseInt(request.getParameter("age")))
                        .build()));
        return mv;
    }
}
