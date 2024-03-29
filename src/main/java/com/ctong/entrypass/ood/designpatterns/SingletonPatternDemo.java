package com.ctong.entrypass.ood.designpatterns;

import com.ctong.entrypass.ood.designpatterns.singleton.EagerSingleton;
import com.ctong.entrypass.ood.designpatterns.singleton.LazySingleton;
import com.ctong.entrypass.ood.designpatterns.singleton.LazySingletonStaticInnerClass;

/**
 * Singleton Pattern
 * 单例模式
 * 对象在内存中只有一个实例, 并且无需频繁的创建和销毁对象
 * Prefer lazy initialization with double-checked locking (volatile + synchronized)
 * initialization-on-demand holder idiom
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {
        EagerSingleton eagerIns = EagerSingleton.getInstance();
        LazySingleton lazyIns = LazySingleton.getInstance();
        LazySingletonStaticInnerClass lazyIns2 = LazySingletonStaticInnerClass.getInstance();

        eagerIns.print();
        lazyIns.print();
        lazyIns2.print();
    }
}
