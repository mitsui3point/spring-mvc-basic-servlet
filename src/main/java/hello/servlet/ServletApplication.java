package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//서블릿 자동 등록; 현재 @ServletComponentScan를 추가한 클래스 패키지를 포함한 모든 하위패키지를 색인하여 servlet 을 등록해서 실행할 수 있도록 도와준다.
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
