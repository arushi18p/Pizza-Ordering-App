package pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Meatzza pizza, a specific type of pizza.
 * This class extends the Pizza class
 * Includes pre-defined toppings and pricing logic.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public class Meatzza extends Pizza {

    private static final double SMALL_BASE_PRICE = 17.99;
    private static final double MEDIUM_BASE_PRICE = 19.99;
    private static final double LARGE_BASE_PRICE = 21.99;

    /**
     * Constructs a Meatzza pizza with default toppings.
     * Default toppings include sausage, pepperoni, beef, and ham.
     */
    public Meatzza() {
        super(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF,
                Topping.HAM)));
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        return switch (this.getSize()) {
            case SMALL -> SMALL_BASE_PRICE;
            case MEDIUM -> MEDIUM_BASE_PRICE;
            case LARGE -> LARGE_BASE_PRICE;
        };
    }

    /**
     * Retrieves the simple name of the pizza type.
     *
     * @return the name of the class as a string.
     */
    @Override
    public String getSimpleName() {
        return this.getClass().getSimpleName();
    }

}
