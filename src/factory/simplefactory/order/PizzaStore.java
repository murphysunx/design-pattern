package factory.simplefactory.order;

public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new SimpleFactory());
        System.out.println("退出程序");
    }
}
