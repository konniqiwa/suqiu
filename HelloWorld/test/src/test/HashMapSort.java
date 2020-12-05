package test;

import java.util.*;

public class HashMapSort {
    public static void main(String[] args) {
        HashMap<Integer,User> map = new HashMap<Integer,User>();
        map.put(1,new User("zhangsan",23));
        map.put(3,new User("lisi",24));
        map.put(2,new User("wangwu",25));
        System.out.println("排序前：" + map);
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("排序后：" + sort(map) );
    }

    public static LinkedHashMap<Integer,User> sort(HashMap<Integer,User> map) {

        Set<Map.Entry<Integer, User>> entries = map.entrySet();

        List<Map.Entry<Integer, User>> list = new ArrayList<Map.Entry<Integer, User>>(entries);

        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o1.getValue().getAge() - o2.getValue().getAge();
            }
        });

        LinkedHashMap<Integer,User> resultMap = new LinkedHashMap<Integer,User>();
        for (Map.Entry<Integer, User> entry : list) {
            resultMap.put(entry.getKey(),entry.getValue());
        }


        return resultMap;
    }
}


