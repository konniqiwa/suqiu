package test;

public class Test extends Base {
    public static void main(String[] args) {
        new Test().method();
    }

    public void method() {
        System.err.println(super.getClass().getName());
        System.err.println(this.getClass().getSuperclass().getName());
    }
}

class Base {
    public Base() {}
}