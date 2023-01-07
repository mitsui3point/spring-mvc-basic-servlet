package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@link WebServlet} 서블릿 애노테이션<br>
 * name: 서블릿 이름<br>
 * urlPatterns: URL 매핑<br>
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("HelloServlet.service\n");
        System.out.print("request = " + request + "\n");// request = org.apache.catalina.connector.RequestFacade@7d802f8b; interface HttpServletRequest 구현체
        System.out.print("response = " + response + "\n");// response = org.apache.catalina.connector.ResponseFacade@7347ce4b; interface HttpServletResponse 구현체

        String username = request.getParameter("username");
        System.out.print("username = " + username + "\n");

        //response header
        response.setContentType("text/plain");//단순 문자 return type
        response.setCharacterEncoding("utf-8");//문자 세트 encoding 방식
        //response body
        response.getWriter().write("hello " + username);//http message response body 에 data 가 들어감
    }
}