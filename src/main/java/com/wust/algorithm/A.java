package com.wust.algorithm;

public class A {
    public void show(A a){
        System.out.println("AA");
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();
        a.show(a);
        a.show(b);
        a.show(c);
        a.show(d);
        b.show(a);
        b.show(b);
        b.show(c);
        b.show(d);
        c.show(a);
        c.show(b);
        c.show(c);
        c.show(d);
        d.show(a);
        d.show(b);
        d.show(c);
        d.show(d);

    }
}
class B extends A{
    public void show(A a){
        System.out.println("AB");
    }
    public void show(B b){
        System.out.println("BB");
    }
}
class C extends B{
    public void show(A a){
        System.out.println("AC");
    }
    public void show(B b){
        System.out.println("BC");
    }
    public void show(C c){
        System.out.println("CC");
    }
}
class D extends C{
    public void show(A a){
        System.out.println("AD");
    }
    public void show(B b){
        System.out.println("BD");
    }


}

