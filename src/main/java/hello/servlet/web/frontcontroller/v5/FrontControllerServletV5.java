package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyHandlerAdapter;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.handleradapter.MyHandlerAdapterV3;
import hello.servlet.web.frontcontroller.v5.handleradapter.MyHandlerAdapterV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private Map<String, Object> handlerMappingMap = new HashMap();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();//핸들러 매핑 초기화
        initHandlerAdapters();//어댑터 초기화
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * controller -> handler
         * <p/>
         * 이전에는 컨트롤러를 직접 매핑해서 사용했다.<br/>
         * 그런데 이제는 어댑터를 사용하기 때문에,<br/>
         * 컨트롤러 뿐만 아니라 어댑터가 지원하기만 하면,<br/>
         * 어떤 것이라도 URL에 매핑해서 사용할 수 있다.<br/>
         * 그래서 이름을 컨트롤러에서 더 넒은 범위의 핸들러로 변경했다.<br/>
         */
        Object handler = getHandler(request);

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        ModelView mv = handlerAdapter.handle(request, response, handler);

        MyView view = viewResolver(mv.getViewPath());
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView(new StringBuilder()
                .append("/WEB-INF/views/")
                .append(viewName)
                .append(".jsp")
                .toString());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new MyHandlerAdapterV3());
        handlerAdapters.add(new MyHandlerAdapterV4());
    }

    private Object getHandler(HttpServletRequest request) {
        return handlerMappingMap.get(request.getRequestURI());
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        return handlerAdapters.stream()
                .filter(adapter -> adapter.supports(handler))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler));
    }
}
