package hello.servlet.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append(" <meta charset=\"UTF-8\">\n")
                .append(" <title>Title</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<form action=\"/servlet/members/save\" method=\"post\">\n")
                .append(" username: <input type=\"text\" name=\"username\" />\n")
                .append(" age: <input type=\"text\" name=\"age\" />\n")
                .append(" <button type=\"submit\">전송</button>\n")
                .append("</form>\n")
                .append("</body>\n")
                .append("</html>\n")
                .toString());
    }
}
