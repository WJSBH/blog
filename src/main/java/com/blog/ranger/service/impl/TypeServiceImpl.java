package com.blog.ranger.service.impl;

import com.blog.ranger.dao.mapper.TypeMapper;
import com.blog.ranger.dao.pojo.Type;
import com.blog.ranger.service.TypeService;
import com.blog.ranger.vo.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @create 2023-03-28-20:54
 */
@Transactional
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public Type saveType(Type type) {
        if (typeMapper.getTypeByName(type.getName()) == null){
            typeMapper.saveTypeByName(type.getName());
            return typeMapper.getTypeByName(type.getName());
        }

        return null;
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public List<Type> listType(Integer page, Integer rows) {
        if (page < 1) page = 1;
        List<Type> typeList = typeMapper.getTypes((page - 1) * rows, rows);
        return typeList;
    }

    @Override
    public Type updateType(Long id, Type type) {
        typeMapper.updateType(id, type);
        return type;
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteTypeById(id);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.getAllTypes();
    }

    @Override
    public List<TypeVo> getHotTypes(Integer limit) {
        return typeMapper.getHotTypes(limit);
    }

    @Override
    public Integer getTypeCount() {
        return typeMapper.getTypeCount();
    }
}
