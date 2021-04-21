package com.suqiu.goods.service.impl;

import com.suqiu.goods.dao.BrandMapper;
import com.suqiu.goods.pojo.Brand;
import com.suqiu.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.BrandListModel;
import com.suqiu.model.req.CreateOrUpdateBrandModel;
import com.suqiu.model.req.IsFactoryStatusModel;
import com.suqiu.model.req.IsShowStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:suqiu
 * @Description:Brand业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * Brand条件+分页查询
     *
     * @param brand 查询条件
     * @param page  页码
     * @param size  页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(brand);
        //执行搜索
        return new PageInfo<Brand>(brandMapper.selectByExample(example));
    }

    /**
     * Brand分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Brand>(brandMapper.selectAll());
    }

    /**
     * Brand条件查询
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //构建查询条件
        Example example = createExample(brand);
        //根据构建的条件查询数据
        return brandMapper.selectByExample(example);
    }


    /**
     * Brand构建查询对象
     *
     * @param brand
     * @return
     */
    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            // 品牌id
            if (!StringUtils.isEmpty(brand.getId())) {
                criteria.andEqualTo("id", brand.getId());
            }
            // 品牌名称
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            // 品牌图片地址
            if (!StringUtils.isEmpty(brand.getImage())) {
                criteria.andEqualTo("image", brand.getImage());
            }
            // 品牌的首字母
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
            // 排序
            if (!StringUtils.isEmpty(brand.getSeq())) {
                criteria.andEqualTo("seq", brand.getSeq());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Brand
     *
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 增加Brand
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    /**
     * 根据ID查询Brand
     *
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Brand全部数据
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public List<Brand> findByCategory(Integer id) {
        //两种方案:
        //1. 自己写sql语句直接执行  推荐
        //2. 调用通用的mapper的方法 一个个表查询
        return brandMapper.findByCategory(id);
    }

    @Override
    public List<Brand> findAllBrand(BrandListModel reqModel) {
        if (reqModel.getPageNum() != null && reqModel.getPageSize() != null) {
            PageHelper.startPage(reqModel.getPageNum(), reqModel.getPageSize());
        }
        Example example = new Example(Brand.class);
        if (reqModel.getKeyword() != null) {
            example.createCriteria().andLike("name", String.format("%%%s%%", reqModel.getKeyword()));
        }
        return brandMapper.selectByExample(example);
    }

    @Override
    public void deleteBrand(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void createOrUpdate(CreateOrUpdateBrandModel model) {
        Brand brand = new Brand();
        brand.setStory(model.getBrandStory());
        brand.setFactoryStatus(model.getFactoryStatus());
        brand.setShowStatus(model.getShowStatus());
        brand.setName(model.getName());
        brand.setImage(model.getLogo());
        brand.setLetter(model.getFirstLetter());
        brand.setSeq(model.getSort());
        if (model.getId() == null) {
            // 新增品牌
            brandMapper.insertSelective(brand);
        }else {
            brand.setId(Math.toIntExact(model.getId()));
            brandMapper.updateByPrimaryKeySelective(brand);
        }
    }

    @Override
    public void isFactoryStatus(Long id, IsFactoryStatusModel model) {
        Brand brand = new Brand();
        brand.setId(Math.toIntExact(id));
        brand.setFactoryStatus(model.getIsFactoryStatus());
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void isShowStatus(IsShowStatusModel model) {
        model.getIds().forEach(id -> {
            Brand brand = new Brand();
            brand.setId(Math.toIntExact(id));
            brand.setShowStatus(model.getShowStatus());
            brandMapper.updateByPrimaryKeySelective(brand);
        });
    }

    @Override
    public Brand brandInfo(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
