package factory.factorymethod.order;

import factory.factorymethod.pizza.BJCheesePizza;
import factory.factorymethod.pizza.BJPepperPizza;
import factory.factorymethod.pizza.Pizza;

public class BJOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
