package com.chenzhen.service;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.DeskType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeskService {

    public List<Desk> findAll();

    public List<Desk> searchDesk(String name);

    public List<DeskType> finddesktype();

    public int updatestatus(Desk desk);

    public int insertDesk(Desk desk);

    public int deleteDeskById(int id);

    public List<Desk> findById(int id);

    public int updateDeskById(Desk desk);

    public List<Desk> findByUserid(int user_id);
}
