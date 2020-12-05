package test;

import java.util.*;

public class RandomNumTest {
    public static void main(String[] args) {
        String s = "";
        int num = 0;
     //   System.out.println(num);

        /*while (s.length()<4) {
            num = (int) (Math.random()*10);
            *//*List<Integer> list = new ArrayList<>();
            if(list.contains(num)) {
                list.add(num);
                //s += num;
            }

            for (Integer integer : list) {
                s += integer;
            }*//*
            Set<Integer>  set = new HashSet<Integer>();
            set.add(num);
            Iterator<Integer> iterator = set.iterator();
            if(iterator.hasNext()) {
                s += iterator.next();
            }

        }*/

        Set<Integer>  set = new HashSet<Integer>();
        set.add(1);
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        if (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


       /* for (int i=0;i<4;i++) {
            num = (int) (Math.random()*10);
            s += num;
            if ((int)s.charAt(i) != num) {

            }
        }
*/

        System.out.println(s);
    }
}
