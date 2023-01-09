package hello.servlet.basic.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RequestParamServletTest {

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void serviceTest() throws ServletException, IOException {
        //given

        //when
        //http://localhost:8080/request-param?username=hello&age=20
        request.setParameter("username", "hello", "hello2");
        request.setParameter("age", "20");

        new RequestParamServlet().service(request, response);

        /**
         *         result.append("[단일 파라미터 조회] - start\n");
         *         String username = request.getParameter("username");
         *         String age = request.getParameter("age");
         *         System.out.println("username = " + username);
         *         System.out.println("age = " + age);
         *         result.append("[단일 파라미터 조회] - end\n");
         *
         *         result.append("[이름이 같은 복수 파라미터 조회] - start\n");
         *         Arrays.stream(request.getParameterValues("username")).forEach(s -> {
         *             result.append("username: ").append(s).append("\n")
         *         });
         *         result.append("[이름이 같은 복수 파라미터 조회] - end\n");
         */
        //then
        assertThat(response.getContentAsString()).isEqualTo(
                "[전체 파라미터 조회] - start\n" +
                        "username: hello\n" +
                        "age: 20\n" +
                        "[전체 파라미터 조회] - end\n\n" +
                        "[단일 파라미터 조회] - start\n" +
                        "username: hello\n" +
                        "age: 20\n" +
                        "[단일 파라미터 조회] - end\n\n" +
                        "[이름이 같은 복수 파라미터 조회] - start\n" +
                        "username: hello\n" +
                        "username: hello2\n" +
                        "[이름이 같은 복수 파라미터 조회] - end\n\n"
        );
    }
}
