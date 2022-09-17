package com.chenzhen.service.impl;

import com.chenzhen.entity.Admin;
import com.chenzhen.entity.Desk;
import com.chenzhen.mapper.AdminMapper;
import com.chenzhen.mapper.DeskMapper;
import com.chenzhen.service.AdminService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public List<Admin> findbyuser(Admin admin) {
        SqlSession sqlSession = sqlUtils.conn();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        List<Admin> all = new ArrayList<>();
        Logger logger = Logger.getLogger(this.getClass());
        try {
            all = mapper.findbyuser(admin);
        }catch (Exception e){
            logger.info("账号或密码错误");
        }
        sqlSession.close();
        return all;
    }

    @Override
    public int updatepsd(Admin admin) {
        SqlSession sqlSession = sqlUtils.conn();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        int res = adminMapper.updatepsd(admin);
        sqlSession.close();
        return res;
    }
}
