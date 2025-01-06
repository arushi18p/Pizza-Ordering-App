package pizza;

import java.util.ArrayList;

/**
 * Represents the base class for all types of pizzas.
 * Contains shared properties like toppings, crust, and size,
 * and defines abstract methods for subclasses.
 * Authors: Arushi Pradhan, Sophia Olakangil
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings; //Topping is a Enum class
    private Crust crust; //Crust is a Enum class
    private Size size; //Size is a Enum class

    private String style;

    /**
     * Constructs a Pizza object with the specified toppings.
     *
     * @param toppings the list of toppings for the pizza.
     */
    protected Pizza(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Retrieves the style of the pizza.
     * @return style the style of the pizza.
     */
    public String getStyle(){
        return this.style;
    }

    /**
     * Sets the style of the pizza.
     * @param style the style of the pizza.
     */
    public void setStyle(String style){
        this.style = style;
    }

    /**
     * Calculates the price of the pizza based on its size.
     *
     * @return the price of the pizza.
     */
    public abstract double price();

    /**
     * Sets the crust of the pizza.
     * @param crust the crust of the pizza.
    **/
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Returns the crust of the pizza.
     * @return the crust of the pizza.
    **/
    public Crust getCrust() {
        return this.crust;
    }

    /**
     * Sets the size of the pizza.
     * @param size the size of the pizza.
     **/
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Returns the size of the pizza.
     * @return the size of the pizza.
     **/
    public Size getSize() {
        return this.size;
    }

    /**
     * Returns the list of toppings for the pizza.
     * @return the list of toppings for the pizza.
     **/
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Adds a topping to the pizza.
     * @param topping the topping to be added.
     **/
    public void addToppings(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Removes a topping from the pizza.
     * @param topping the topping to be removed.
     **/
    public void removeToppings(Topping topping) {
        this.toppings.remove(topping);
    }

    /**
     * Retrieves the simple name of the pizza type.
     * @return the name of the class as a string.
     **/
    public abstract String getSimpleName();

    /**
     * Returns a string representation of the pizza.
     * @return a string representation of the pizza.
     **/
    @Override
    public String toString() {
        return getSimpleName();
    }

}
