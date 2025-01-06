package pizza;

/**
 * Interface for pizza factories.
 * This interface is implemented by classes that create different types of pizzas.
 * Defines methods to create different types of pizzas.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public interface PizzaFactory {
    /**
     * Creates a Deluxe pizza.
     *
     * @return a Pizza instance.
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza.
     *
     * @return a Pizza instance.
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQChicken pizza.
     *
     * @return a Pizza instance.
     */
    Pizza createBBQChicken();

    /**
     * Creates a BuildYourOwn pizza.
     *
     * @return a Pizza instance.
     */
    Pizza createBuildYourOwn();
}
