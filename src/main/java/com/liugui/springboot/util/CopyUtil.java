package com.liugui.springboot.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyUtil<T> {
    public static<T> T copy(Object source,Class<T> clazz){
        if(source==null){
            return null;
        }
        T obj=null;
        try {
            obj=clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source,obj);
        return obj;
    }
    public static<T> List<T> copyList(List source,Class<T> clazz){
        List<T> target = new ArrayList<>();
        if(!CollectionUtils.isEmpty(source)) {
            for (Object o : source) {
                T t = copy(o, clazz);
                target.add(t);
            }
        }
        return target;
    }

}