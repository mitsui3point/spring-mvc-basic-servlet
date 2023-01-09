package hello.servlet.basic.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ResponseJsonServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void serviceTest() throws ServletException, IOException {
        //given
        String jsonParam = "{\"username\":\"kim\",\"age\":20}";
        request.setContent(jsonParam.getBytes(StandardCharsets.UTF_8));
        //when
        new ResponseJsonServlet().service(request, response);
        //then
        assertThat(response.getContentType()).isEqualTo("application/json;charset=utf-8");
        assertThat(response.getContentAsString()).isEqualTo(jsonParam);
    }
}
