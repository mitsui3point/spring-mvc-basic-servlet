package hello.servlet.web.frontcontroller.v5.handleradapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyHandlerAdapter;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyHandlerAdapterV4 implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String pathName = controller.process(paramMap, model);

        ModelView mv = new ModelView(pathName);
        mv.setModel(model);
        return mv;
    }

    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterMap()
                .forEach((key, values) -> {
                    paramMap.put(key, values[0]);
                });
        return paramMap;
    }
}
