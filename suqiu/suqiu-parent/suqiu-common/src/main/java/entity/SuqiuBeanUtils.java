package entity;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

public class SuqiuBeanUtils {
    /**
     * 复制list
     * @param sourceList
     * @param beanClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> copyListProperties(Object sourceList, Class<?> beanClass) throws Exception{
        List<Object> sList = (List<Object>) sourceList;
        List<Object> tList = new ArrayList<Object>();
        for (Object t : sList) {
            Object dto = beanClass.newInstance();
            BeanUtils.copyProperties(t, dto);
            tList.add(dto);
        }
        return (List<T>) tList;
    }

    public static void copyProperties(Object source, Object target) throws BeansException {
        if(source != null){
            BeanUtils.copyProperties(source,target);
        }
    }
}
