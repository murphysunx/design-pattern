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