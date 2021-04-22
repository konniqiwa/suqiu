package com.suqiu.goods.service;

import com.suqiu.goods.model.req.AuditSpuModel;
import com.suqiu.goods.model.req.LogicDeleteSpuModel;
import com.suqiu.goods.model.req.PullSpuModel;
import com.suqiu.goods.pojo.Goods;
import com.suqiu.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.SpuListModel;
import com.suqiu.model.req.UpdateStatusModel;
import com.suqiu.model.res.SpuListDTO;
import com.suqiu.model.res.SpuListTotalDTO;
import com.suqiu.model.res.SpuSpecDTO;
import com.suqiu.model.res.SpuSpecParamDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/****
 * @Author:admin
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {

    /**
     * 获取商品规格
     *
     * @param id
     * @return
     */
    List<SpuSpecDTO> getSpuSpec(Long id);

    /**
     * 是否推荐
     *
     * @param model
     */
    void updateStatus(UpdateStatusModel model);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    SpuListDTO getInfo(Long id);


    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     *
     * @param id
     * @return
     */
    Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

    /**
     * 添加商品(SPU+ SKUlIST)
     *
     * @param goods update  add
     */
    void save(Goods goods);

    Goods findGoodsById(Long id);

    void auditSpu(List<Long> ids, int publishStatus);

    /**
     * 下架
     *
     * @param model
     */
    void pullSpu(PullSpuModel model);

    void logicDeleteSpu(List<Long> ids,int deleteStatus);


    void restoreSpu(Long id);

    SpuListTotalDTO findBySearch(SpuListModel reqModel) throws Exception;

    /**
     * 获取规格参数
     *
     * @param id
     * @return
     */
    List<SpuSpecParamDTO> getSpuSpecParam(Long id);
}
