package hello.servlet.web.servlet;

import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        StringBuilder resultBuilder = new StringBuilder()
                .append("<html>")
                .append("<head>")
                .append(" <meta charset=\"UTF-8\">")
                .append(" <title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append("<a href=\"/index.html\">메인</a>")
                .append("<table>")
                .append(" <thead>")
                .append(" <th>id</th>")
                .append(" <th>username</th>")
                .append(" <th>age</th>")
                .append(" </thead>")
                .append(" <tbody>");
        for (Member member : members) {
            resultBuilder
                    .append(" <tr>")
                    .append(" <td>" + member.getId() + "</td>")
                    .append(" <td>" + member.getUsername() + "</td>")
                    .append(" <td>" + member.getAge() + "</td>")
                    .append(" </tr>");
        }
        resultBuilder
                .append(" </tbody>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        w.write(resultBuilder.toString());
    }
}
