package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", memberRepository.save(
                Member.builder()
                        .username(request.getParameter("username"))
                        .age(Integer.parseInt(request.getParameter("age")))
                        .build()));
        return mv;
    }

    @RequestMapping
    public ModelAndView members() {
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", memberRepository.findAll());
        return mv;
    }
}
