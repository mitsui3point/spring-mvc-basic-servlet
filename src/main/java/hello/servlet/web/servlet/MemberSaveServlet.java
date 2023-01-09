package hello.servlet.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.domain.Member;
import hello.servlet.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member paramMember = Member.builder()
                .username(request.getParameter("username"))
                .age(Integer.parseInt(request.getParameter("age")))
                .build();

        Member member = memberRepository.save(paramMember);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(new StringBuilder()
                .append("<html>\n")
                .append("<head>\n")
                .append(" <meta charset=\"UTF-8\">\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("성공\n")
                .append("<ul>\n")
                .append(" <li>id=")
                .append(member.getId())
                .append("</li>\n")
                .append(" <li>username=")
                .append(member.getUsername())
                .append("</li>\n")
                .append(" <li>age=")
                .append(member.getAge())
                .append("</li>\n")
                .append("</ul>\n")
                .append("<a href=\"/index.html\">메인</a>\n")
                .append("</body>\n")
                .append("</html>")
                .toString());
    }
}
