package factory.absfactory.order;

import factory.absfactory.pizza.LDCheesePizza;
import factory.absfactory.pizza.LDPepperPizza;
import factory.absfactory.pizza.Pizza;

public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
