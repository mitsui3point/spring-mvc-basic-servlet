package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    private String viewPath;
    private Map<String, Object> model;

    public ModelView(String viewPath) {
        this.viewPath = viewPath;
        this.model = new HashMap<>();
    }
}
