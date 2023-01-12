package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 0: 애노테이션 기반의 컨트롤러인 {@link RequestMapping}에서 사용. {@link RequestMappingHandlerMapping}<br>
 * 1: 스프링 빈의 이름{@link Component}으로 핸들러를 찾는다. {@link BeanNameUrlHandlerMapping} => 현재 {@link HandlerMapping} 구현체<br>
 * <p>
 * 0: 애노테이션 기반의 컨트롤러인 {@link RequestMapping}에서 사용. {@link RequestMappingHandlerAdapter}<br>
 * 1: {@link HttpRequestHandler} 처리 {@link HttpRequestHandlerAdapter}<br>
 * 2: 스프링 빈의 이름{@link Component}으로 핸들러를 찾는다. {@link SimpleControllerHandlerAdapter} => 현재 {@link HandlerAdapter} 구현체<br>
 * <p>
 * 현재 {@link HandlerMapping} 구현체: {@link BeanNameUrlHandlerMapping}<br>
 * 현재 {@link HandlerAdapter} 구현체: {@link HttpRequestHandlerAdapter}<br>
 * <p>
 * {@link #handleRequest(HttpServletRequest, HttpServletResponse)} 호출은 <br>
 * {@link SimpleControllerHandlerAdapter#handle(HttpServletRequest, HttpServletResponse, Object)} 에서 진행
 */
@Component(value = "/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
