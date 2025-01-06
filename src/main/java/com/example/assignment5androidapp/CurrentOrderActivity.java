package com.example.assignment5androidapp;
import pizza.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.util.Log;


/**
 * Activity for managing the current order in the pizza ordering system.
 * Authors: Sophia Olakangil, Arushi Pradhan
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private EditText orderNumberField;
    private RecyclerView orderListView;
    private TextView subtotalField;
    private TextView salesTaxField;
    private TextView orderTotalField;
    private TextView amtOfPizzasField;


    private Order currentOrder = GlobalData.getInstance().getCurrentOrder();
    private PizzaAdapter pizzaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        orderListView = findViewById(R.id.orderRecyclerView);
        orderListView.setLayoutManager(new LinearLayoutManager(this));

        if (currentOrder == null) {
            currentOrder = new Order();
            GlobalData.getInstance().setCurrentOrder(currentOrder); // Prevent NullPointerException
        }

        pizzaAdapter = new PizzaAdapter(currentOrder.getPizzas(), this);
        orderListView.setAdapter(pizzaAdapter);
        Log.d("CurrentOrder", "Pizzas in order: " + currentOrder.getPizzas().size());

        orderNumberField = findViewById(R.id.orderNumberField);
        subtotalField = findViewById(R.id.subtotalField);
        salesTaxField = findViewById(R.id.salesTaxField);
        orderTotalField = findViewById(R.id.orderTotalField);
        amtOfPizzasField = findViewById(R.id.amtOfPizzasField);
        Button removePizzaButton = findViewById(R.id.removePizzaButton);
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        Button clearOrderButton = findViewById(R.id.clearOrderButton);
        Button addPizzaButton = findViewById(R.id.addPizzaButton);
        ImageButton addAnotherPizzaButton = findViewById(R.id.pizza_ordering_button);
        ImageButton orderHistoryButton = findViewById(R.id.order_history_button);
        ImageButton cartButton = findViewById(R.id.cart_button);
        ImageButton homeButton = findViewById(R.id.home_button);
        // Initialize UI fields
        initializeUI();
        // Set button actions
        removePizzaButton.setOnClickListener(v -> showRemovePizzaDialog());
        placeOrderButton.setOnClickListener(v -> placeOrder());
        clearOrderButton.setOnClickListener(v -> clearOrder());
        addAnotherPizzaButton.setOnClickListener(v -> addAnotherPizzaButtonHandler());
        addPizzaButton.setOnClickListener(v -> addAnotherPizzaButtonHandler());
        orderHistoryButton.setOnClickListener(v -> orderHistoryButtonHandler());
        cartButton.setOnClickListener(v -> cartButtonHandler());
        homeButton.setOnClickListener(v -> homeButtonHandler());
    }


    private void addAnotherPizzaButtonHandler(){
        Intent AddAnotherPizzaIntent = new Intent(this, MainMenu.class);
        startActivity(AddAnotherPizzaIntent);
    }

    private void orderHistoryButtonHandler(){
        Intent OrderHistoryIntent = new Intent(this, OrderHistoryActivity.class);
        startActivity(OrderHistoryIntent);
    }

    private void cartButtonHandler(){
        Intent CartIntent = new Intent(this, CurrentOrderActivity.class);
        startActivity(CartIntent);
    }

    private void homeButtonHandler(){
        Intent HomeIntent = new Intent(this, MainMenu.class);
        startActivity(HomeIntent);
    }

    private void initializeUI() {
        orderNumberField.setText(String.valueOf(currentOrder.getNumber()));
        updateOrderTotals();
    }

    private void updateOrderTotals() {

        subtotalField.setText(String.format("%.2f", currentOrder.getSubtotal()));
        salesTaxField.setText(String.format("%.2f", currentOrder.getTax()));
        orderTotalField.setText(String.format("%.2f", currentOrder.totalPrice()));
        amtOfPizzasField.setText(String.valueOf(currentOrder.getPizzas().size()));
    }



    private void placeOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        GlobalData.getInstance().getOrderHistoryList().add(currentOrder);
        currentOrder = new Order();
        GlobalData.getInstance().setCurrentOrder(currentOrder);

        // Refresh UI
        initializeUI();
        pizzaAdapter = new PizzaAdapter(currentOrder.getPizzas(), this);
        orderListView.setAdapter(pizzaAdapter);
        updateOrderTotals();

        Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
    }

    private void removePizza() {
        Log.d("RemovePizza", "Remove button clicked. Current activity: " + this.getClass().getSimpleName());
        int selectedIndex = pizzaAdapter.getSelectedPosition();
        if (selectedIndex != RecyclerView.NO_POSITION  && selectedIndex < currentOrder.getPizzas().size()){
            Pizza removedPizza = currentOrder.getPizzas().get(selectedIndex);
            Log.d("RemovePizza", "Removing pizza: " + removedPizza.getSimpleName() + ", Index: " + selectedIndex);
            currentOrder.removePizza(removedPizza); // Update order
            GlobalData.getInstance().setCurrentOrder(currentOrder); // Remove from the data list
            pizzaAdapter = new PizzaAdapter(currentOrder.getPizzas(), this);
            orderListView.setAdapter(pizzaAdapter);// Notify adapter
            updateOrderTotals(); // Update totals
            Log.d("RemovePizza", "Pizza removed successfully.");

        } else {
            Toast.makeText(this, "No pizza selected!", Toast.LENGTH_SHORT).show();
            Log.d("RemovePizza", "No pizza selected for removal.");
        }
    }


    private void clearOrder() {
        int itemCount = currentOrder.getPizzas().size();
        if (itemCount > 0) {
            GlobalData.getInstance().getCurrentOrder().getPizzas().clear();
            pizzaAdapter.notifyItemRangeRemoved(0, currentOrder.getPizzas().size());
            currentOrder.getPizzas().clear(); // Clear the pizzas list
            pizzaAdapter.notifyDataSetChanged(); // Refresh the adapter
            updateOrderTotals(); // Update totals
            Toast.makeText(this, "Cart cleared!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cart is already empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showRemovePizzaDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Remove Pizza")
                .setMessage("Are you sure you want to remove pizza ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Execute the confirmed action
                    removePizza();
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Cancel action
                    dialog.dismiss();
                })
                .show();
    }

}

