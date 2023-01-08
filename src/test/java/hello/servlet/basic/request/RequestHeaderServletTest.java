package hello.servlet.basic.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RequestHeaderServletTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void serviceTest() throws ServletException, IOException {
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<String> requestHeaders = Arrays.asList("host", "connection", "cache-control", "sec-ch-ua", "sec-ch-ua-mobile",
                "sec-ch-ua-platform", "upgrade-insecure-requests", "user-agent", "accept", "sec-fetch-site",
                "sec-fetch-mode", "sec-fetch-user", "sec-fetch-dest", "accept-encoding", "accept-language",
                "cookie");
        List<Locale> requestLocales = Arrays.asList(
                new Locale("ko", "KR"),
                new Locale("en", "US")
        );
        List<Cookie> requestCookies = Arrays.asList(
                new Cookie("Idea-5b47ab55", "0fa8934d-3a89-41e2-9e40-5dccf438a3a5")
        );
        List<String> expected = getExpected(request, requestHeaders, requestLocales, requestCookies);

        //when
        executeService(request, response, requestHeaders, requestLocales, requestCookies);

        List<String> actual = Arrays.stream(output.toString().split("\n"))
                .map(s -> s.replaceAll("\r", ""))
                .collect(Collectors.toList());

        //then
        actual.stream().forEach(System.out::println);
        assertThat(actual).containsExactly(expected.toArray(String[]::new));
    }

    private void executeService(HttpServletRequest request, HttpServletResponse response, List<String> requestHeaders, List<Locale> requestLocales, List<Cookie> requestCookies) throws ServletException, IOException {
        //startLine
        when(request.getMethod()).thenReturn("GET");
        when(request.getProtocol()).thenReturn("HTTP/1.1");
        when(request.getScheme()).thenReturn("http");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/request-header"));
        when(request.getRequestURI()).thenReturn("/request-header");
        when(request.getQueryString()).thenReturn("username=hi");
        when(request.isSecure()).thenReturn(false);
        when(request.getParameter("username")).thenReturn("hi");
        //headers
        when(request.getHeaderNames()).thenReturn(Collections.enumeration(
                requestHeaders
                        .stream()
                        .collect(Collectors.toList())));
        //headerUtil
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(8080);
        when(request.getLocales()).thenReturn(Collections.enumeration(requestLocales));
        when(request.getCookies()).thenReturn(requestCookies.toArray(Cookie[]::new));
        when(request.getContentType()).thenReturn(null);
        when(request.getContentLength()).thenReturn(-1);
        when(request.getCharacterEncoding()).thenReturn("UTF-8");
        //etc
        when(request.getRemoteHost()).thenReturn("0:0:0:0:0:0:0:1");
        when(request.getRemoteAddr()).thenReturn("0:0:0:0:0:0:0:1");
        when(request.getRemotePort()).thenReturn(10562);
        when(request.getLocalName()).thenReturn("0:0:0:0:0:0:0:1");
        when(request.getLocalAddr()).thenReturn("0:0:0:0:0:0:0:1");
        when(request.getLocalPort()).thenReturn(8080);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new RequestHeaderServlet().service(request, response);
    }

    private List<String> getExpected(HttpServletRequest request, List<String> requestHeaders, List<Locale> requestLocales, List<Cookie> requestCookies) {
        List<String> expected = new ArrayList<>();
        //startLine
        expected.add("--- REQUEST-LINE - start ---");
        expected.add("request.getMethod() = GET");
        expected.add("request.getProtocol() = HTTP/1.1");
        expected.add("request.getScheme() = http");
        expected.add("request.getRequestURL() = http://localhost:8080/request-header");
        expected.add("request.getRequestURI() = /request-header");
        expected.add("request.getQueryString() = username=hi");
        expected.add("request.isSecure() = false");
        expected.add("--- REQUEST-LINE - end ---");
        expected.add("");
        //headers
        expected.add("--- Headers - start ---");
        requestHeaders.forEach(e -> expected.add(e.toString()));
        expected.add("--- Headers - end ---");
        expected.add("");
        //headerUtil
        expected.add("--- Header 편의 조회 start ---");
        expected.add("[Host 편의 조회]");
        expected.add("request.getServerName() = localhost"); //Host 헤더
        expected.add("request.getServerPort() = 8080"); //Host 헤더
        expected.add("");
        expected.add("[Accept-Language 편의 조회]");
        requestLocales.forEach(e -> expected.add("locale = " + e.toString()));
        expected.add("request.getLocale() = " + request.getLocale());//ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7 : q가 가장 높은 우선순위 언어
        expected.add("");
        expected.add("[cookie 편의 조회]");
        requestCookies.forEach(e -> expected.add(e.getName() + ": " + e.getValue()));
        expected.add("");
        expected.add("[Content 편의 조회]");
        expected.add("request.getContentType() = null");//message body 에 담긴
        expected.add("request.getContentLength() = -1");
        expected.add("request.getCharacterEncoding() = UTF-8");
        expected.add("--- Header 편의 조회 end ---");
        expected.add("");
        //etc
        expected.add("--- 기타 조회 start ---");
        expected.add("[Remote 정보]");//요청이 온 곳에 대한 정보
        expected.add("request.getRemoteHost() = 0:0:0:0:0:0:0:1"); //
        expected.add("request.getRemoteAddr() = 0:0:0:0:0:0:0:1"); //
        expected.add("request.getRemotePort() = 10562"); //
        expected.add("");
        expected.add("[Local 정보]");//나의 서버에 대한 정보
        expected.add("request.getLocalName() = 0:0:0:0:0:0:0:1"); //
        expected.add("request.getLocalAddr() = 0:0:0:0:0:0:0:1"); //
        expected.add("request.getLocalPort() = 8080"); //
        expected.add("--- 기타 조회 end ---");
        expected.add("");
        return expected;
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
        output.reset();
    }
}
