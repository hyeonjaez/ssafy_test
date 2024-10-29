package com.ssafy.test.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.test.member.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int idCheck(String id) throws SQLException;

    void joinMember(MemberDto memberDto) throws SQLException;

    MemberDto loginMember(Map<String, String> map) throws SQLException;

}
