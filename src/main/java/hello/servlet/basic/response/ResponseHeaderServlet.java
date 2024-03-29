package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        //[response-header]
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//캐시 설정; 무효화
        response.setHeader("Pragma", "no-cache");//캐시 설정; 무효화
        response.setHeader("my-header", "hello");

        //[response-header 편의 메서드]
        headerMethods(response);

        //[response-header cookie 메서드]
        cookie(response);

        //[response-header redirect]
        redirect(response);

        response.getWriter().write("ok");
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location","/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

    private void headerMethods(HttpServletResponse response) {
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //response.setHeader("Content-Length", "2");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setContentLength(2);
    }

    private void cookie(HttpServletResponse response) {
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=6");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(60);//60초
        response.addCookie(cookie);//request.getCookie("myCookie");로 호출가능
    }
}
