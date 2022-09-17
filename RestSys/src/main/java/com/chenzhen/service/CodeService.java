package com.chenzhen.service;

import com.chenzhen.entity.Code;

import java.util.List;

public interface CodeService {
    int addCode(Code code);

    List<Code> findcodeByuseridandcode(Code code);
}
