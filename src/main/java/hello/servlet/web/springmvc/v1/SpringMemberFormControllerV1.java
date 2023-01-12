package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * {@link Controller} :
 * {@link Component} + {@link RequestMapping}<br>
 * 스프링이 자동으로 스프링 빈으로 등록한다. (내부에 {@link Component} 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)<br>
 * 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.
 * <p>
 * {@link RequestMapping}:
 * 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.<br>
 * 애노테이션을 기반으로 동작하기 때문에, 메서드의 이름은 임의로 지으면 된다.
 */
@Controller
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
