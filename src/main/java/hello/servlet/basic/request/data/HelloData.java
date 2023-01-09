package hello.servlet.basic.request.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * javaBean 접근방법
 * <p>
 * : getter/setter 메소드가 있어야 jackson library 가 읽어들일 수 있음.
 */
@Getter
@Setter
@ToString(of = {"username", "age"})
public class HelloData {
    private String username;
    private Integer age;
}
