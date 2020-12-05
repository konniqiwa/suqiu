package test;

public class DateDay {
    public static void main(String[] args) {
        String s = "2020-6-1";
        String[] split = s.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        switch (month - 1) {
            case 11:
                day += 30;
            case 10:
                day += 31;
            case 9:
                day += 30;
            case 8:
                day += 31;
            case 7:
                day += 31;
            case 6:
                day += 30;
            case 5:
                day += 31;
            case 4:
                day += 30;
            case 3:
                day += 31;
            case 2:

                if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {// 是

                    day += 29;
                } else {
                    day += 28;
                }
            case 1:
                day += 31;
            default:
                break;
        }

        if(month >= 1 && month <= 12){
            System.out.println("今天是" + year + "年的第" + day + "天");
        }
    }
}
