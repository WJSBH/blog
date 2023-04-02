package com.blog.ranger.dao.mapper;

import com.blog.ranger.dao.pojo.Type;
import com.blog.ranger.vo.TypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @create 2023-03-28-20:58
 */
@Mapper
public interface TypeMapper {

    void updateType(Long id, Type type);
    void saveTypeByName(@Param("name") String name);
    Type getTypeByName(@Param("name") String name);
    Type getTypeById(@Param("id") Long id);
    List<Type> getTypes(@Param("offset") Integer offset, @Param("rows") Integer rows);
    void deleteTypeById(@Param("id") Long id);
    List<Type> getAllTypes();
    List<TypeVo> getHotTypes(Integer limit);

    Integer getTypeCount();
}
