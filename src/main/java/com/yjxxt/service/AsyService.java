package com.yjxxt.service;


import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Asy;
import com.yjxxt.dto.Dto;
import com.yjxxt.mapper.AsyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AsyService extends BaseService<Asy,Integer> {


    @Resource
    private AsyMapper asyMapper;



    public List<Dto> all(){
        return asyMapper.all();
    }
}
