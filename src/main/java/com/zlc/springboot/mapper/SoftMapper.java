package com.zlc.springboot.mapper;

import com.zlc.springboot.model.Soft;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftMapper {

    //1.查询所有数据
    List<Soft> selectSoftAll(String key);

    //2.查询所有精品软件数据
    List<Soft> selectSoftOfBoutique(String key);

    //3. 查询所有销量最高软件.
    List<Soft> selectSoftOfManyCount(String key);

    //4. 查询所有免费软件.
    List<Soft> selectSoftOfFree(String key);

    //5. 根据id查询文件名字
    String selectNameById(Integer softid);

    //6. 分页查询所有文件
    List<Soft> selectpageAllSoft(Integer start,Integer end);

    //7. 查询插件数量
    Integer selectCjNum();

    int deleteByPrimaryKey(Integer softid);

    int insert(Soft record);

    int insertSelective(Soft record);

    Soft selectByPrimaryKey(Integer softid);

    int updateByPrimaryKeySelective(Soft record);

    int updateByPrimaryKey(Soft record);
}