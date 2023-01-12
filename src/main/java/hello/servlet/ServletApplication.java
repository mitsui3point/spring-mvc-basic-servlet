package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
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

}
