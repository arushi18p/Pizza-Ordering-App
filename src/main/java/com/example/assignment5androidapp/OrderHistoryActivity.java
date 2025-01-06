package com.example.assignment5androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pizza.Order;

public class OrderHistoryActivity extends AppCompatActivity {

    private ListView orderList;
    private ArrayAdapter<Order> orderAdapter;
    private ArrayList<Order> orderHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Button cancelOrderButton = findViewById(R.id.cancelOrderButton);
        orderList = findViewById(R.id.orderListView);

        orderHistoryList = GlobalData.getInstance().getOrderHistoryList();
        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, orderHistoryList );
        orderList.setAdapter(orderAdapter);

        cancelOrderButton.setOnClickListener(v -> cancelOrder());

        ImageButton addAnotherPizzaButton = findViewById(R.id.pizza_ordering_button);
        ImageButton orderHistoryButton = findViewById(R.id.order_history_button);
        ImageButton cartButton = findViewById(R.id.cart_button);
        ImageButton homeButton = findViewById(R.id.home_button);

        addAnotherPizzaButton.setOnClickListener(v -> addAnotherPizzaButtonHandler());
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

    private void cancelOrder() {

        int selectedPosition = orderList.getCheckedItemPosition(); // Get the selected position

        if (selectedPosition != ListView.INVALID_POSITION) { // Check if a valid item is selected
            orderHistoryList = GlobalData.getInstance().getOrderHistoryList();
            Order order = orderHistoryList.get(selectedPosition); // Get the selected order

            orderHistoryList.remove(order);
            GlobalData.getInstance().setOrderHistoryList(orderHistoryList); // Update the order history list
            orderAdapter.notifyDataSetChanged(); // Notify the adapter of the change
            orderList.clearChoices(); // Clear the selection to avoid stale state
        }
    }


}
