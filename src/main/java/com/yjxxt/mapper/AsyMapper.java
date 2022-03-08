package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Asy;
import com.yjxxt.dto.Dto;

import java.util.List;

public interface AsyMapper extends BaseMapper<Asy,Integer> {


    List<Dto> all();
}