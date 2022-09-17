package com.chenzhen.service;

import com.chenzhen.entity.Admin;

import java.util.List;

public interface AdminService {

    public List<Admin> findbyuser(Admin admin);

    public int updatepsd(Admin admin);
}
