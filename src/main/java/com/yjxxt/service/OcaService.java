package com.yjxxt.service;


import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Oca;
import com.yjxxt.mapper.OcaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OcaService extends BaseService<Oca,Integer> {


    @Resource
    private OcaMapper ocaMapper;
}
