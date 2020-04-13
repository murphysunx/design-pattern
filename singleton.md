## 单例模式

1. 饿汉式（静态常量）
2. 饿汉式（静态代码块）
3. 懒汉式（线程不安全）
4. 懒汉式（线程安全，同步方法）
5. 懒汉式（线程安全，同步代码块）
6. 双重检查
7. 静态内部类
8. 枚举



### 饿汉式（静态常量）

#### 步骤

1. 构造器私有化（防止new）
2. 类的内部创建对象
3. 向外暴露一个静态的公共方法
4. 代码实现

```java
class Singleton {
    // 构造器私有化
    private Singleton() {
        
    }
    
    // 本类内部创建对象实例
    private final static Singleton instance = new Singleton();
    
    // 提供一个共有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return instance;
    }
}
```

#### 分析

1. 简单，在类装在时就完成实例化。避免了线程同步问题
2. 缺点：在类装载的时候就完成实例化，没有达到lazy loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
3. 结论：这种单例模式可用，**可能**造成内存浪费



### 饿汉式（静态代码块）

```java
class Singleton {
    // 构造器私有化
    private Singleton() {
        
    }
    
    // 本类内部创建对象实例
    private static Singleton instance;
 
    static {
        instance = new Singleton();
    }
    
    // 提供一个共有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return instance;
    }
}
```

#### 分析

1. 与上面的方式类似，也是在类装载的时候就执行静态代码块的代码，初始化类的实例



### 懒汉式（线程不安全）

```java
class Singleton {
    private static Singleton instance;
    
    private Singleton() {
        
    }
    
    // 提供一个静态的公有方法，当使用到该方法时，才去创建instance
    // 即懒汉式
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

#### 分析

1. 起到lazy loading的效果，但是只能在单线程下使用
2. 如果在多线程下，可能产生多个实例
3. 在实际开发中，不要使用这种方式



### 懒汉式（线程安全，同步方法）

```java
class Singleton {
    private static Singleton instance;
    
    private Singleton() {
        
    }
    
    // 加入同步代码，解决线程不安全问题
    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

#### 分析

1. 解决了线程不安全问题
2. **效率低**。每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。
3. 在实际开发中，不推荐使用



### 懒汉式（线程安全，同步代码块）

```java
class Singleton {
    private static Singleton instance;
    
    private Singleton() {
        
    }
    
    // 加入同步代码，解决线程不安全问题
    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
            	instance = new Singleton();   
            }
        }
        return instance;
    }
}
```

#### 分析

1. 这种同步并不能起到线程同步的左右
2. 不能使用这种方式



### 双重检查

```java
class Singleton {
    private static volatile Singleton singleton;
    private Singleton() {}
    public static Singleton getInstance() {
        if(singleton == null) {
            synchronized (Singleton.class) {
                if (singleton==null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

#### 分析

1. Double-check概念时多线程开发中常使用到的
2. 避免反复进行方法同步
3. 线程安全；延迟加载；效率较高
4. 在实际开发中，推荐使用这种单例模式设计

### 静态内部类

```java
class Singleton {
    private Singleton() {}

    // 写一个静态内部类，该类有一个静态属性Singleton
    // jvm在类装载时是线程安全的
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton;
    }

    // 童工一个静态的共有方法，直接返回SingletonInstance.INSTANCE
	public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
```

#### 分析

1. 采用了类装载机制来保证初始化实例时只有一个线程
2. 静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。
3. 类的静态属性只会第一次加载类的时候初始化，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的
4. 优点：避免了**线程不安全**， 利用静态内部类特点实现延加载，效率高
5. 推荐使用



### 枚举

```java
enum Singleton {
    INSTANCE;
    public void sayOK() {
        System.out.println("ok");
    }
}
```

#### 分析

1. 借助枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
2. 这种方式是Effective Java作者Josh Bloch提倡的方式
3. 推荐使用



##  JDK源码分析

**java.lang.Runtime**



### 注意事项

1. 单利模式保证了系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能。
2. 当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new
3. 单例模式使用场景：
   1. 需要频繁的进行创建和销毁的对象，创建对象时耗时过多或耗费资源过多（即：重量级对象），但又经常用到的对象，工具类对象、频繁访问数据库或文件的对象（比如数据源。session工厂等）