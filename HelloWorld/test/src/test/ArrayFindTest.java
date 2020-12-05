package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 输出数组中相同字符的次数
 */
public class ArrayFindTest {

    public static void main(String[] args) throws IOException {

        int[] arr = {1,4,1,4,2,5,4,5,8,7,8,77,88,5,4,9,6,2,4,1,5};
        HashMap<Integer, Integer> map = new HashMap();

        for(int i:arr){
            //返回指定的键被映射到的值，或者如果该映射不包含该键的映射，则返回NULL
            Integer count = map.get(i);
            if(count == null){
                map.put(i, 1);
            }else{
                map.put(i, count+1);
            }
        }
    
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();

        for(Map.Entry<Integer, Integer> entry:set){
            System.out.println(entry.getKey()+"出现:"+entry.getValue());
        }

    }
}
