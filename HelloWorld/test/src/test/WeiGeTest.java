package test;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

public class WeiGeTest {
    public static void main(String[] args) {
        //低浓度的人:"张三 李四 王五"
        //高浓度的人:"张三 赵六 周七"
        //高胆固醇的人:"周七 王尼玛 草泥马"

        String 低浓度的人 = "张三 李四 王五";
        String 高浓度的人 = "张三 赵六 周七";
        String 高胆固醇的人 = "周七 王尼玛 草泥马";
        String[] lowPots = 低浓度的人.split(" ");
        String[] highPots = 高浓度的人.split(" ");
        String[] highs = 高胆固醇的人.split(" ");


       /* String[] lowPots = {"张三", "李四", "王五"};
        String[] highPots = {"张三", "赵六", "周七"};
        String[] highs = {"周七", "赵六", "草泥马"};*/
        Map<String, String> map = new HashMap<String, String>();
        for (String lowPot : lowPots) {
            map.put(lowPot, lowPot);
        }
        for (String highPot : highPots) {
            map.put(highPot, highPot);
        }

        /*int count = lowPots.length + highPots.length;
        for (String lowPot : lowPots) {
            for (String highPot : highPots) {
                for (String high : highs) {
                    if (lowPot == highPot) {
                        count--;
                        //count--;
                        if (lowPot == high) {
                            //count--;
                            if (highPot == high) {
                                count--;
                            }
                        }
                    }
                }
            }*/
        for (String high : highs) {
            map.put(high,high);
        }

        System.out.println("异常的人数："+map.keySet().size());
    }
        //System.out.println(count);

        //String[] lowPot = {"张三","李四","王五"};

}
