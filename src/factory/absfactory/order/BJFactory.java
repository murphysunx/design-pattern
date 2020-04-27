package factory.absfactory.order;

import factory.absfactory.pizza.BJCheesePizza;
import factory.absfactory.pizza.BJPepperPizza;
import factory.absfactory.pizza.Pizza;

public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
