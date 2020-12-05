package test;

import java.lang.invoke.CallSite;
import java.util.*;

/**
 * HashMap排序
 * 已知一个HashMap<Integer，User>集合， User有name（String）和 age（int）属性。请写一个方法实现对
 * HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，
 * 要求对HashMap中的User的age倒序进行排序。排序时key=value键值对不得拆散。
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer,User> map = new HashMap<Integer,User>();
        map.put(1,new User("zhangsan",23));
        map.put(3,new User("lisi",24));
        map.put(2,new User("wangwu",25));
        System.out.println("排序前：" + map);
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("排序后：" + sortMap(map) );

        ArrayList<Integer> list  = new ArrayList<>(5);

        System.out.println(list.size());
        list.add(2);
        System.out.println(list.size());


    }



    /**
     * 排序
     * @param map
     * @return
     */
    public static HashMap<Integer,User> sortMap(HashMap<Integer,User> map) {
        //获取key集合
        Set<Map.Entry<Integer, User>> entries = map.entrySet();
        System.out.println(entries);

        //转换为list，可以用工具类排序
        List<Map.Entry<Integer, User>> list = new ArrayList<Map.Entry<Integer, User>>(entries);

        //用Collections工具类排序list,自定义排序规则(age排序)
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                //排序
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });
        //System.out.println(list);

        //新建LinkedHashMap，将list数据存入
        LinkedHashMap<Integer,User> resultMap = new LinkedHashMap<Integer,User>();
        for (Map.Entry<Integer, User> entry : list) {
            resultMap.put(entry.getKey(),entry.getValue());
        }


        return resultMap;
    }


}
