package com.example.springexampletools.collection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @ClassName: CollectionTools
 * @Author lee
 * @date 2020/7/24 10:04
 */
public class CollectionTools {


    /**
     * Obj to map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<Object, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
