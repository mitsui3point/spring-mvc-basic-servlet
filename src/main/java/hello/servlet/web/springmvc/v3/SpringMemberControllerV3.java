package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * {@link RequestParam} 사용
 * {@link RequestMapping} -> {@link GetMapping}, @{@link PostMapping}
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")//@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String newForm() {
        return "new-form";//문자 반환시 viewName으로 인식
    }

    @PostMapping("/save")//@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        model.addAttribute("member", memberRepository.save(
                Member.builder()
                        .username(username)
                        .age(age)
                        .build()));
        return "save-result";
    }

    @GetMapping//@RequestMapping(method = RequestMethod.GET)
    public String members(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }
}
