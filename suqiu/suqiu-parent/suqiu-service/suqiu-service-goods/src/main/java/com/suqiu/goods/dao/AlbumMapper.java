package com.suqiu.goods.dao;
import com.suqiu.goods.pojo.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:suqiu
 * @Description:Album的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface AlbumMapper extends Mapper<Album> {
}
