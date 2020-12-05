package test;

public class Hello {
    public static void main(String[] args) {
        /*int i=105;
        while(true) {
            if(i++>105) {
                break;
            }
        }
        System.out.println(i);*/
        /*int a=2,b=3;
        int c = (a+b>5?a++:b++);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);*/
        /*int a=3,b=3;
        if(a++==b){
            System.out.println(a);
        }*/

        /*int x=2,y=2;
        if(x++==3& ++y==3) {
            x=7;
        }
        System.out.println(x);
        System.out.println(y);*/
        /*Out.In in = new Out().new In();
        in.print();*/
        /*Boolean b = true;
        if(b=false) {
            System.out.println("a");
        }*/

        /*int x =2,y=5;
        switch (x) {
            default:y++;
            case 3:
            y++;
            case 4:
                y++;

        }
        System.out.println(y);*/
        int a=10;
        if(a++>10) {
            a=20;
        }
        System.out.println(a);
    }
}
class Out{
    private int age =12;
    class In{
        private int age=13;
        public void print() {
            int age=14;
            System.out.println(age);
            System.out.println(this.age);
            System.out.println(Out.this.age);
        }
    }
}
