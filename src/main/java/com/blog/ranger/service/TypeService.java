package com.blog.ranger.service;

import com.blog.ranger.dao.pojo.Type;
import com.blog.ranger.vo.TypeVo;

import java.util.List;


/**
 * @create 2023-03-28-20:43
 */
public interface TypeService {

    Type saveType(Type type);
    Type getType(Long id);
    List<Type> listType(Integer page, Integer rows);
    Type updateType(Long id, Type type);
    void deleteType(Long id);

    List<Type> getAllTypes();
    List<TypeVo> getHotTypes(Integer limit);
    Integer getTypeCount();
}
