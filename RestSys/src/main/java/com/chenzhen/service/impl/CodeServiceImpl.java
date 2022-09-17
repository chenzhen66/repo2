package com.chenzhen.service.impl;

import com.chenzhen.entity.Code;
import com.chenzhen.mapper.CodeMapper;
import com.chenzhen.service.CodeService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CodeServiceImpl implements CodeService {
    @Override
    public int addCode(Code code) {
        SqlSession sqlSession = sqlUtils.conn();
        CodeMapper mapper = sqlSession.getMapper(CodeMapper.class);
        int result = mapper.addCode(code);
        sqlSession.close();
        return result;
    }

    @Override
    public List<Code> findcodeByuseridandcode(Code code) {
        SqlSession sqlSession =sqlUtils.conn();
        CodeMapper mapper = sqlSession.getMapper(CodeMapper.class);
        List<Code> a = mapper.findcodeByuseridandcode(code);
        sqlSession.close();
        return a;
    }
}
