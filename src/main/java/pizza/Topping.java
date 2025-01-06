package pizza;

/**
 * Represents the toppings that can be added to a pizza.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    BBQ_CHICKEN("BBQ Chicken"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    BEEF("Beef"),
    HAM("Ham"),
    BACON("Bacon"),
    SPINACH("Spinach"),
    PINEAPPLE("Pineapple");

   private final String displayName;

    /**
     * Constructs a Topping enum with the specified display name.
     * @param displayName the display name of the topping.
     */
    Topping(String displayName) {
      this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString(){
        return displayName;
    }

}

