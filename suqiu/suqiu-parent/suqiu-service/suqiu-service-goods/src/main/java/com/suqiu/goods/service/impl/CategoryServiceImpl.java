package com.suqiu.goods.service.impl;

import com.suqiu.goods.dao.CategoryMapper;
import com.suqiu.goods.pojo.Category;
import com.suqiu.goods.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.BasePageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/****
 * @Author:suqiu
 * @Description:Category业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * Category条件+分页查询
     *
     * @param category 查询条件
     * @param page     页码
     * @param size     页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Category> findPage(Category category, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(category);
        //执行搜索
        return new PageInfo<Category>(categoryMapper.selectByExample(example));
    }

    /**
     * Category分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Category> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Category>(categoryMapper.selectAll());
    }

    /**
     * Category条件查询
     *
     * @param category
     * @return
     */
    @Override
    public List<Category> findList(Category category) {
        //构建查询条件
        Example example = createExample(category);
        //根据构建的条件查询数据
        return categoryMapper.selectByExample(example);
    }


    /**
     * Category构建查询对象
     *
     * @param category
     * @return
     */
    public Example createExample(Category category) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (category != null) {
            // 分类ID
            if (!StringUtils.isEmpty(category.getId())) {
                criteria.andEqualTo("id", category.getId());
            }
            // 分类名称
            if (!StringUtils.isEmpty(category.getName())) {
                criteria.andLike("name", "%" + category.getName() + "%");
            }
            // 商品数量
            if (!StringUtils.isEmpty(category.getGoodsNum())) {
                criteria.andEqualTo("goodsNum", category.getGoodsNum());
            }
            // 是否显示
            if (!StringUtils.isEmpty(category.getIsShow())) {
                criteria.andEqualTo("isShow", category.getIsShow());
            }
            // 是否导航
            if (!StringUtils.isEmpty(category.getIsMenu())) {
                criteria.andEqualTo("isMenu", category.getIsMenu());
            }
            // 排序
            if (!StringUtils.isEmpty(category.getSeq())) {
                criteria.andEqualTo("seq", category.getSeq());
            }
            // 上级ID
            if (!StringUtils.isEmpty(category.getParentId())) {
                criteria.andEqualTo("parentId", category.getParentId());
            }
            // 模板ID
            if (!StringUtils.isEmpty(category.getTemplateId())) {
                criteria.andEqualTo("templateId", category.getTemplateId());
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
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Category
     *
     * @param category
     */
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 增加Category
     *
     * @param category
     */
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 根据ID查询Category
     *
     * @param id
     * @return
     */
    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Category全部数据
     *
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> findByParentId(Integer pid, BasePageModel reqModel) {
        //SELECT * from tb_category where parent_id=0
        if (reqModel != null && reqModel.getPageNum() != null && reqModel.getPageSize() != null) {
            PageHelper.startPage(reqModel.getPageNum(), reqModel.getPageSize());
        }
        Category record = new Category();
        record.setParentId(pid);//查询的条件  相当于 where parent_id=0
        //record.setId(1);//where parent_id=0 and id = 1
        List<Category> categoryList = categoryMapper.select(record);

        return categoryList;
    }

    /**
     * 查询所有分类，树形结构
     *
     * @return
     */
    @Override
    public List<Category> findAllTree() {
        /*List<Category> all = categoryMapper.selectAll();
        List<Category> collect = categoryMapper.selectAll().stream().filter((category) -> {
            return category.getParentId() == 0;
        }).map((menu) -> {
            menu.setChildren(findChildren(menu, all));
            return menu;
        }).collect(Collectors.toList());
        return collect;*/


        /*Example example3 = new Example(Category.class);
        example3.createCriteria().andEqualTo("level", 2);
        List<Category> categories3 = categoryMapper.selectByExample(example3);

        Example example2 = new Example(Category.class);
        example2.createCriteria().andEqualTo("level", 1);
        List<Category> categories2 = categoryMapper.selectByExample(example2);
        for (Category category2 : categories2) {
            category2.setChildren(categories3);
        }


        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("level", 0);
        List<Category> categories = categoryMapper.selectByExample(example);
        for (Category category : categories) {
            category.setChildren(categories2);
        }
        System.out.println(JSON.toJSONString(categories));
        return categories;*/


        List<Category> byParentId1 = this.findByParentId(0, null);
        byParentId1.forEach(item -> {
            List<Category> byParentId2 = this.findByParentId(item.getId(), null);
            item.setChildren(byParentId2);
            byParentId2.forEach(item2 -> {
                List<Category> byParentId3 = this.findByParentId(item2.getId(), null);
                item2.setChildren(byParentId3);
            });
        });

        return byParentId1;


    }

    /**
     * 查询改分类下的子分类
     *
     * @param root
     * @param all
     * @return
     */
    public List<Category> findChildren(Category root, List<Category> all) {
        List<Category> collect = all.stream().filter(category -> category.getParentId().equals(root.getId()))
                .map((menu) -> {
                    menu.setChildren(findChildren(menu, all));
                    return menu;
                }).collect(Collectors.toList());
        return collect;
    }


    @Override
    public void setLevel() {
        Category record = new Category();
        record.setParentId(0);//查询的条件  相当于 where parent_id=0
        //record.setId(1);//where parent_id=0 and id = 1
        List<Category> categoryList = categoryMapper.select(record);
        for (Category category : categoryList) {
            List<Category> collect2Level = categoryMapper.selectAll().stream().filter(category1 -> category1.getParentId().equals(category.getId())).collect(Collectors.toList());
            for (Category category1 : collect2Level) {
                category1.setLevel(1);
                categoryMapper.updateByPrimaryKey(category1);
                List<Category> collect3Level = categoryMapper.selectAll().stream().filter(category2 -> category2.getParentId().equals(category1.getId())).collect(Collectors.toList());
                for (Category category2 : collect3Level) {
                    category2.setLevel(2);
                    categoryMapper.updateByPrimaryKey(category2);
                }
            }
        }


    }
}