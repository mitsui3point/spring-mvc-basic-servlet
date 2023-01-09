package hello.servlet.basic.request.data;

import lombok.*;

/**
 * javaBean 접근방법
 * <p>
 * : getter/setter 메소드가 있어야 jackson library 가 읽어들일 수 있음.
 */
@Getter
@Setter
@ToString(of = {"username", "age"})
@NoArgsConstructor
public class HelloData {
    private String username;
    private Integer age;

    @Builder
    private HelloData(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
