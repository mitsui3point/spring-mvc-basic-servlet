package hello.servlet.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {
    private Long id;
    private String username;
    private int age;

    @Builder
    private Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
