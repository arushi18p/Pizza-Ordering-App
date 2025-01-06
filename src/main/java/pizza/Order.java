package pizza;
import java.util.ArrayList;

/**
 * Represents an order containing multiple pizzas.
 * Each order is identified by a unique order number and includes functionality to calculate
 * subtotals, taxes, and the total price of the order.
 * Authors: Arushi Pradhan, Sophia Olakangil
 */
public class Order {
    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>
    private static final double tax = 0.06625;
    private static int counter = 1;

    public Order(){
       this.number= counter++;
       this.pizzas=new ArrayList<>();
    }

//    public void finalizeOrder() {
//        this.number = counter++;
//    }

    /**
     * Retrieves the order number.
     *
     * @return the order number.
     */
    public int getNumber(){
        return number;
    }



    /**
     * Retrieves the list of pizzas in the order.
     *
     * @return list/Pizza> of pizzas.
     */
    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza the pizza to add.
     */
    public void addPizza(Pizza pizza) {
        if(pizza!=null)
            pizzas.add(pizza);

    }

    /**
     * Removes a pizza from the order.
     *
     * @param pizza the pizza to remove.
     */
    public void removePizza(Pizza pizza){
        pizzas.remove(pizza);
    }

    /**
     * Calculates the subtotal of the order.
     *
     * @return the subtotal of the order.
     */
    public double getSubtotal(){
        double subtotal=0;
        for (Pizza pizza : pizzas) {
            subtotal+= pizza.price();
        }
        return subtotal;
    }

    /**
     * Calculates the tax of the order.
     *
     * @return the tax of the order.
     */
    public double getTax(){
        return getSubtotal() * tax;
    }

    /**
     * Calculates the total price of the order.
     *
     * @return the total price of the order.
     */
    public double totalPrice() {
        return getSubtotal()+getTax();
    }


    /**
     * Returns a string representation of the order, including the order number,
     * list of pizzas, and total price.
     *
     * @return a string representation of the order.
     */
    @Override
    public String toString() {
        return "Order(" +
                " number= " + number +
                "), pizzas= " + pizzas + " Total Price: " + String.format("%.2f", totalPrice());
    }


}


