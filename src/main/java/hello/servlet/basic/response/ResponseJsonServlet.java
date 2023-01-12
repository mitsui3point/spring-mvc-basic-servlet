package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import hello.servlet.basic.request.data.HelloData;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json;charset=utf-8
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ServletInputStream inputStream = request.getInputStream();
        HelloData helloData = null;
        try {
            helloData = mapper.readValue(inputStream, HelloData.class);
        } catch (MismatchedInputException e) {
            helloData = HelloData.builder().username("kim").age(20).build();
        } finally {
            //{"username":"kim","age":20}
            response.getWriter().write(mapper.writeValueAsString(helloData));
        }
    }
}
