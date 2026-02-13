package com.tianxiawushuang.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 用于存放开发常用的一些工具方法
 * */
@Component
public class GeneralUtils {
//    将字符串类型的数据转换为对应的list集合类型数据
    public static  List<String> convertStringToList(String s){
        if(s == null || s.isEmpty()){
            return List.of();
        }
        return  Arrays.asList((s.substring(1, s.length() - 1))
                .split(","));
    }

//    默认判断传递的参数是否为空
    public static boolean validateAllFieldNotNull(Object obj){
        if(obj == null){
            return false;
        }
        List<Field> fields = Arrays.asList(obj.getClass().getDeclaredFields());
        for (Field field : fields) {
//            设置参数以能够访问一些私有字段
            field.setAccessible(true);
            try{
                if(Objects.isNull(field.get(obj))){
                    return false;
                }
            }catch(IllegalAccessException e){
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

//    支持调用方指定逻辑判断参数
    public static boolean validate(Object obj, Predicate<Object> validationLogic){
        return validationLogic.test(obj);
    }
}
