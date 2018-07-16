package com.fang.designpattern;

/**
 * 一般来说，第一种不算单例，
 * 第四种和第三种就是一种，如果算的话，第五种也可以分开写了。
 * 所以说，一般单例都是五种写法。懒汉，恶汉，双重校验锁，枚举和静态内部类。
 */
public class Singletion {

}

/**
 * 第七种
 * 双重校验锁
 */
class DoubleLockSingleton{
   private volatile static  DoubleLockSingleton singleton;
   private DoubleLockSingleton(){}
   public static DoubleLockSingleton getInstance(){
       if(singleton == null){
           synchronized(DoubleLockSingleton.class){
               if(singleton == null){
                   singleton = new DoubleLockSingleton();
               }
           }
        }
        return singleton;
   }
}

/**
 * 第六种
 * 这种方式是Effective Java作者Josh Bloch 提倡的方式，
 * 它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象，可谓是很坚强的壁垒啊，
 * 在深度分析Java的枚举类型—-枚举的线程安全性及序列化问题中有详细介绍枚举的线程安全问题和序列化问题，
 * 不过，个人认为由于1.5中才加入enum特性，用这种方式写不免让人感觉生疏，
 * 在实际工作中，我也很少看见有人这么写过。
 */
enum SingletonEnum{
    INSTANCE;
    public void whateverMethod(){}
}

/**
 * 第五种
 * 静态内部类
 * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程
 * 它跟第三种和第四种方式不同的是（很细微的差别）：
 * 第三种和第四种方式是只要Singleton类被装载了，
 * 那么instance就会被实例化（没有达到lazy loading效果），
 * 而这种方式是Singleton类被装载了，instance不一定被初始化。
 * 因为SingletonHolder类没有被主动使用，
 * 只有显示通过调用getInstance方法时，
 * 才会显示装载SingletonHolder类，
 * 从而实例化instance。
 */
class StaticSingleton{
    private static class StaticSingletonHolder{
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }
    private StaticSingleton(){}
    public static final StaticSingleton getInstance(){
        return StaticSingletonHolder.INSTANCE;
    }
}

/**
 * 第四种
 * 饿汉式变种
 * 表面上看起来差别挺大，其实更第三种方式差不多，都是在类初始化即实例化instance。
 */
class HungrySingletonVersionTwo{
    private static HungrySingletonVersionTwo instance = null;
    static{
        instance = new HungrySingletonVersionTwo();
    }
    private HungrySingletonVersionTwo(){}
    public static HungrySingletonVersionTwo getInstance(){
        return instance;
    }
}

/**
 * 第三种
 * 饿汉
 * 在类刚开始被加载的时候就创建对象
 * 这种方式基于classloder机制
 */
class HungrySingleton{
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){}
    public static HungrySingleton getInstance(){
        return instance;
    }
}

/**
 * 第二种
 * 懒汉   线程安全
 * 这种写法能够在多线程中很好的工作，
 * 而且看起来它也具备很好的lazy loading（只在需要的时候加载必要的数据，避免即时加载带来的不必要开销），
 * 但是，遗憾的是，效率很低，99%情况下不需要同步。
 */
class LazySingletionSynchronized{
    private static LazySingletionSynchronized instance;
    private static synchronized LazySingletionSynchronized getInstance(){
        if(instance == null){
            instance = new LazySingletionSynchronized();
        }
        return instance;
    }

}
/**
 * 第一种
 * 懒汉   线程不安全
 */
class LazySingletionUnsafe{
    private static LazySingletionUnsafe instance;
    private LazySingletionUnsafe(){}
    public static LazySingletionUnsafe getInstance(){
        if(instance == null){
            instance = new LazySingletionUnsafe();
        }
        return instance;
    }
}
