package pizza;

import java.util.ArrayList;


/**
 * Represents a customizable pizza where users can add their own toppings.
 * This class extends the Pizza class and includes logic for calculating
 * the price based on the number of toppings and pizza size.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public class BuildYourOwn extends Pizza {

    private static final double TOPPING_PRICE = 1.69;
    private static final double SMALL_BASE_PRICE = 8.99;
    private static final double MEDIUM_BASE_PRICE = 10.99;
    private static final double LARGE_BASE_PRICE = 12.99;

    /**
     * Constructs a customizable pizza with no default toppings.
     */
    public BuildYourOwn() {
        super(new ArrayList<>());
    }

    /**
     * Calculates the price of the customizable pizza based on its size and number of toppings.
     *
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        double addedPrice = this.getToppings().size() * TOPPING_PRICE;
        double basePrice = switch (this.getSize()) {
            case SMALL -> SMALL_BASE_PRICE;
            case MEDIUM -> MEDIUM_BASE_PRICE;
            case LARGE -> LARGE_BASE_PRICE;
        };
        double totalPrice = basePrice + addedPrice;
        return Double.parseDouble(String.format("%.2f", totalPrice));
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
