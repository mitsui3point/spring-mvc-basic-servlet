package hello.servlet.repository;

import hello.servlet.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Long sequence = 0L;
    private static Map<Long, Member> store = new HashMap<>();
    private static final MemberRepository memberRepository = new MemberRepository();
    private MemberRepository() {

    }
    public static MemberRepository getInstance() {
        return memberRepository;
    }

    public void clear() {
        sequence = 0L;
        store.clear();
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }


    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
