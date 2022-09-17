package com.chenzhen.service.impl;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.DeskType;
import com.chenzhen.mapper.DeskMapper;
import com.chenzhen.service.DeskService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DeskServiceImpl implements DeskService {

    @Override
    public List<Desk> findAll() {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        List<Desk> all = mapper.findAll();
        sqlSession.close();
        return all;
    }

    @Override
    public List<Desk> searchDesk(String name) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        List<Desk> all = mapper.searchDesk(name);
        sqlSession.close();
        return all;
    }

    @Override
    public List<DeskType> finddesktype() {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        List<DeskType> all = mapper.finddesktype();
        sqlSession.close();
        return all;
    }

    @Override
    public int updatestatus(Desk desk) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        int res = mapper.updatestatus(desk);
        sqlSession.close();
        return res;
    }

    @Override
    public int insertDesk(Desk desk) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        int res = mapper.insertDesk(desk);
        sqlSession.close();
        return res;
    }

    @Override
    public int deleteDeskById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        int res = mapper.deleteDeskById(id);
        sqlSession.close();
        return res;
    }

    @Override
    public List<Desk> findById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        List<Desk> all = mapper.findById(id);
        sqlSession.close();
        return all;
    }

    @Override
    public int updateDeskById(Desk desk) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        int res = mapper.updateDeskById(desk);
        sqlSession.close();
        return res;
    }

    @Override
    public List<Desk> findByUserid(int user_id) {
        SqlSession sqlSession = sqlUtils.conn();
        DeskMapper mapper = sqlSession.getMapper(DeskMapper.class);
        List<Desk> list = mapper.findByUserid(user_id);
        sqlSession.close();
        return list;
    }
}
