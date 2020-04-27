package prototype.deepclone;

public class Client {
    public static void main(String[] args) throws Exception {
        DeepPrototype deepPrototype = new DeepPrototype();
        deepPrototype.name = "松江";
        deepPrototype.deepClonableTarget = new DeepClonableTarget("大牛", "大牛的类");

        // 方式1 完成深拷贝
        DeepPrototype deepPrototype1 = (DeepPrototype) deepPrototype.clone();
    }
}
