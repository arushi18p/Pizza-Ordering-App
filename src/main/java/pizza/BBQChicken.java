package pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a BBQ Chicken pizza, a specific type of pizza.
 * This class extends the Pizza class and implements specific pricing logic.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public class BBQChicken extends Pizza{

    private static final double SMALL_BASE_PRICE = 14.99;
    private static final double MEDIUM_BASE_PRICE = 16.99;
    private static final double LARGE_BASE_PRICE = 19.99;

    /**
     * Constructs a BBQ Chicken pizza with default toppings.
     * Default toppings include BBQ Chicken, green pepper, provolone, and cheddar.
     */
    public BBQChicken() {
        super( new ArrayList<>(Arrays.asList(
                Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER,
                Topping.PROVOLONE, Topping.CHEDDAR)));
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size.
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
