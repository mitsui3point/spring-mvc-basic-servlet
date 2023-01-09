package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 1.파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20&username=hello2
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder result = new StringBuilder();

        result.append("[전체 파라미터 조회] - start\n");
        request.getParameterNames().asIterator().forEachRemaining(s -> {
            result.append(s).append(": ").append(request.getParameter(s)).append("\n");
        });
        result.append("[전체 파라미터 조회] - end\n\n");

        result.append("[단일 파라미터 조회] - start\n");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        result.append("username: " + username + "\n");
        result.append("age: " + age + "\n");
        result.append("[단일 파라미터 조회] - end\n\n");

        result.append("[이름이 같은 복수 파라미터 조회] - start\n");
        Arrays.stream(request.getParameterValues("username")).forEach(s -> {
            result.append("username: ").append(s).append("\n");
        });
        result.append("[이름이 같은 복수 파라미터 조회] - end\n\n");

        System.out.println(result.toString());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().write(result.toString());
    }
}
