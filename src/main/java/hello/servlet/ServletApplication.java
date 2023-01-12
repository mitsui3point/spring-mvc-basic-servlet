package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan//서블릿 자동 등록; 현재 @ServletComponentScan를 추가한 클래스 패키지를 포함한 모든 하위패키지를 색인하여 servlet 을 등록해서 실행할 수 있도록 도와준다.
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	/**
	 * 뷰 리졸버 - {@link InternalResourceViewResolver}<br>
	 * 스프링 부트는 {@link InternalResourceViewResolver} 라는 뷰 리졸버를 자동으로 등록하는데,<br>
	 * 이때 application.properties 에 등록한<br>
	 * spring.mvc.view.prefix,<br>
	 * spring.mvc.view.suffix 설정 정보를 사용해서 등록한다.
	 * <p>
	 * ; InternalResource?: 내부에서 자원을 호출하는 것(ex. servlet -> jsp 이동)
	 */
//	@Bean
//	ViewResolver internalResourceViewResolver() {
//		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
//	}

	/**
	 * spring bean 직접 등록<br>
	 * spring bean 등록 후, 등록된 controller class에 {@link RequestMapping} 만 작성해도,<br>
	 * spring bean 이 되었으므로 {@link RequestMappingHandlerMapping} 에 의해 매핑된다
	 * @return {@link SpringMemberFormControllerV1}
	 */
//	@Bean
//	 SpringMemberFormControllerV1 springMemberFormControllerV1() {
//		return new SpringMemberFormControllerV1();
//	}
}
