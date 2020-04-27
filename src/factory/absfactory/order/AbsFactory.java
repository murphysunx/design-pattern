package factory.absfactory.order;

import factory.absfactory.pizza.Pizza;

public interface AbsFactory {

    public Pizza createPizza(String orderType);
}
