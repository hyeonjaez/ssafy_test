package com.ssafy.test.member.model.service;

import java.util.Map;

import com.ssafy.test.member.model.MemberDto;
import com.ssafy.test.member.model.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        super();
        this.memberMapper = memberMapper;
    }

    @Override
    public int idCheck(String userId) throws Exception {
        return memberMapper.idCheck(userId);
    }

    @Override
    public void joinMember(MemberDto memberDto) throws Exception {
        memberMapper.joinMember(memberDto);
    }

    @Override
    public MemberDto loginMember(Map<String, String> map) throws Exception {
        return memberMapper.loginMember(map);
    }

}
