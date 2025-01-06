package pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Deluxe pizza, a specific type of pizza.
 * This class extends the Pizza class
 * Includes pre-defined toppings and pricing logic.
 * Authors: Arushi Pradhan, Sophia Olakangil
 */
public class Deluxe extends Pizza {

    private static final double SMALL_BASE_PRICE = 16.99;
    private static final double MEDIUM_BASE_PRICE = 18.99;
    private static final double LARGE_BASE_PRICE = 20.99;

    /**
     * Constructs a Deluxe pizza with default toppings.
     * Default toppings include sausage, pepperoni, green pepper, onion, and mushroom.
     */
    public Deluxe() {
        super(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER,
                Topping.ONION, Topping.MUSHROOM)));
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
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
