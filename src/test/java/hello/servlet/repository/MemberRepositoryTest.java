package hello.servlet.repository;

import hello.servlet.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Test
    void saveTest() {
        //given
        Member member = Member.builder()
                .username("hello")
                .age(20)
                .build();
        //when
        Member actual = memberRepository.save(member);
        Member expected = memberRepository.findById(actual.getId());
        //then
        System.out.println("actual = " + actual);
        System.out.println("expected = " + expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findAllTest() {
        //given
        List<Member> expected = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Member member = Member.builder()
                    .username("hello" + i)
                    .age(20 + i)
                    .build();
            Member savedMember = memberRepository.save(member);
            expected.add(savedMember);
        }
        //when
        List<Member> actual = memberRepository.findAll();
        //then
        System.out.println("actual = " + actual);
        System.out.println("expected = " + expected);
        assertThat(actual).containsExactly(expected.toArray(Member[]::new));
    }

    @AfterEach
    void tearDown() {
        memberRepository.clear();
    }
}
