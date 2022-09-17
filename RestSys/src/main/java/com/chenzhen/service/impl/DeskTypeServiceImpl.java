package com.chenzhen.service.impl;

import com.chenzhen.entity.DeskType;
import com.chenzhen.mapper.DeskTypeMapper;
import com.chenzhen.service.DeskTypeService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DeskTypeServiceImpl implements DeskTypeService {


    @Override
    public int insert(DeskType deskType) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        int res = mapper.insert(deskType);
        sqlSession.close();
        return res;
    }

    @Override
    public List<DeskType> findAll() {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        List<DeskType> all = mapper.findAll();
        sqlSession.close();
        return all;
    }

    @Override
    public List<DeskType> findByname(String name) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        List<DeskType> all = mapper.findByname(name);
        sqlSession.close();
        return all;
    }


    @Override
    public int updatePer(DeskType deskType) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        int res = mapper.updatePer(deskType);
        sqlSession.close();
        return res;
    }


    @Override
    public int deleteDeskTypeById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        int res = mapper.deleteDeskTypeById(id);
        sqlSession.close();
        return res;
    }

    @Override
    public List<DeskType> findById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        List<DeskType> all = mapper.findByid(id);
        sqlSession.close();
        return all;
    }

    @Override
    public int updateDeskType(DeskType deskType) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskTypeMapper mapper = sqlSession.getMapper(DeskTypeMapper.class);
        int res = mapper.updateDeskType(deskType);
        sqlSession.close();
        return res;
    }
}
