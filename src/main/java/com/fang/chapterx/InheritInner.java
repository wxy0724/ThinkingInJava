package com.fang.chapterx;

public class InheritInner extends WithInner.Inner {
    //! InheritInner() {} //Won`t compile
    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
class WithInner {
    class Inner {}
}
