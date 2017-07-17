package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Product;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface ProductMapper {
    /**
     * 通过id删除商品
     *
     * @param id 商品id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 商品数据
     * @return 影响行数
     */
    int insert(Product record);

    /**
     * 插入商品对象中属性不为null的数据
     *
     * @param record 商品对象
     * @return 影响行数
     */
    int insertSelective(Product record);

    /**
     * 通过id获取商品信息
     *
     * @param id 商品id
     * @return 商品对象
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * 统计同一类别商品的数量
     *
     * @param categoryId 类别id
     * @return 统计结果
     */
    int count(@Param("categoryId") Integer categoryId);

    /**
     * 分页查询同一类别商品信息
     *
     * @param categoryId 商品类别id
     * @param offset     偏移量
     * @param rows       行数
     * @return 商品列表
     */
    List<Product> selectByCategory(@Param("categoryId") Integer categoryId,
                                   @Param("offset") Integer offset,
                                   @Param("rows") Integer rows);

    /**
     * 更新不为null的商品信息
     *
     * @param record 商品对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 更新商品信息
     *
     * @param record 商品对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Product record);
}