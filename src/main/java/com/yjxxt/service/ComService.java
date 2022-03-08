package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Com;
import com.yjxxt.mapper.ComMapper;
import com.yjxxt.query.ComQuery;
import com.yjxxt.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComService extends BaseService<Com,Integer> {

    @Resource
    private ComMapper comMapper;

    /**
     * 商品展示
     * @param comQuery
     * @return
     */
    public Map<String,Object> queryAllComs(ComQuery comQuery){

        Map<String,Object> map = new HashMap<>();
        // 开启分页
        PageHelper.startPage(comQuery.getPage(),comQuery.getLimit());

        List<Com> list = comMapper.selectAllComs(comQuery);

        PageInfo<Com> plist = new PageInfo<>(list);

        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());

        return map;
    }

    public Integer queryComState(Integer id){
       return comMapper.selectComState(id);

    }

    public void addCom(Com com) {
        // 商品名为空
        AssertUtil.isTrue(StringUtils.isBlank(com.getComName()),"商品名为空");

        // 商品已经存在
        AssertUtil.isTrue(comMapper.selectComByName(com.getComName()) != null,"商品已经存在");

        // 商品价格为空
        AssertUtil.isTrue(com.getComPrice() == null,"商品价格不能为空");

        AssertUtil.isTrue(comMapper.insertSelective(com) < 1,"添加失败");

    }

    public void updateCom(Com com) {

        // 商品名为空
        AssertUtil.isTrue(StringUtils.isBlank(com.getComName()),"商品名为空");

        // 商品价格为空
        AssertUtil.isTrue(com.getComPrice() == null,"商品价格不能为空");

        AssertUtil.isTrue(comMapper.updateByPrimaryKeySelective(com) < 1,"修改失败");
    }

    public void deleteCom(Integer[] ids) {

        //    判断ID是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0,"请选择要删除的数据!");

        AssertUtil.isTrue(comMapper.deleteBatch(ids) < ids.length,"删除成功");
    }


    //何林杰BuyService
    public Map<String, Object> selectAllCom() {

        //实例化分页
        PageHelper.startPage(1,10);

        PageInfo<Com> pageInfo = new PageInfo<>(comMapper.selectAllCom());

        Map<String,Object> map = new HashMap<>();

        map.put("code",0);
        map.put("msg","查询成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

}
