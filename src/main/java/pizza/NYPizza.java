package pizza;

/**
 * Represents a factory for creating New York-style pizzas.
 * This class implements the Pizza Factory interface.
 * Provides methods to create various types of pizzas with New York-specific attributes.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with a Brooklyn crust.
     *
     * @return a Deluxe pizza instance.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.BROOKLYN);
        return pizza;
    }

    /**
     * Creates a BBQChicken pizza with a thin crust.
     *
     * @return a BBQChicken pizza instance.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.THIN);
        return pizza;
    }

    /**
     * Creates a Meatzza pizza with a hand-tossed crust.
     *
     * @return a Meatzza pizza instance.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }

    /**
     * Creates a BuildYourOwn pizza with a hand-tossed crust.
     *
     * @return a BuildYourOwn pizza instance.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }


}
