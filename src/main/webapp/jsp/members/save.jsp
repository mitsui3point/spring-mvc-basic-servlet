<%@ page import="hello.servlet.domain.Member" %>
<%@ page import="hello.servlet.repository.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //MemberSaveServlet
    //request, response 사용 가능; jsp 도 servlet 으로 변환되므로 request, response 객체를 사용할 수 있다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    Member paramMember = Member.builder()
            .username(request.getParameter("username"))
            .age(Integer.parseInt(request.getParameter("age")))
            .build();

    Member member = memberRepository.save(paramMember);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
