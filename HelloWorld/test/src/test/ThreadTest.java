package test;

public class ThreadTest extends Thread{
    public static void main(String[] args) {
        System.out.println("主线程开始执行....");
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"  开始执行存储过程..");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName()+"  存储过程执行完毕...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }
        System.out.println("主线程执行完毕....");
    }

    //public void add(final int a)


}
