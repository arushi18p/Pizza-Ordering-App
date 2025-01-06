package pizza;

/**
 * Represents a factory for creating Chicago-style pizzas.
 * This class implements the Pizza Factory interface and provides methods
 * for creating various types of pizzas with Chicago-specific attributes.
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     * Creates a Deluxe pizza with a deep-dish crust.
     *
     * @return a Deluxe pizza instance.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.DEEP_DISH);
        return pizza;
    }

    /**
     * Creates a BBQChicken pizza with a deep-dish crust.
     *
     * @return a BBQChicken pizza instance.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }

    /**
     * Creates a Meatzza pizza with a stuffed crust.
     *
     * @return a Meatzza pizza instance.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.STUFFED);
        return pizza;
    }

    /**
     * Creates a BuildYourOwn pizza with a pan crust.
     *
     * @return a BuildYourOwn pizza instance.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }

    
}
