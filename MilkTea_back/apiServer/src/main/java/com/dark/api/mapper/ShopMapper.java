package com.dark.api.mapper;

import com.dark.api.pojo.Shop;
import com.dark.api.query.ShopQuery;
import com.dark.api.vo.ShopVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper {
    void add(Shop shop);

    List<ShopVO> find(ShopQuery shopQuery);

    ShopVO findById(long id);

    void update(Shop shop);

    @Delete("delete from shop where id = #{id}")
    void deleteById(long id);

    @Select("select max(id) from shop")
    Long getMaxId();

    @Select("select name FROM province ORDER BY RAND() LIMIT 1")
    String getProvince();

}
