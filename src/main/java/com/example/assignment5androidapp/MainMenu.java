package com.example.assignment5androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeButtonHandlers();
    }

    private void initializeButtonHandlers() {
        bindButtonHandler(R.id.NewYorkPizzaButton, NewYorkPizzaActivity.class);
        bindButtonHandler(R.id.ChicagoPizzaButton, ChicagoPizzaActivity.class);
        bindButtonHandler(R.id.CartButton, CurrentOrderActivity.class);
        bindButtonHandler(R.id.OrderHistoryButton, OrderHistoryActivity.class);
        bindButtonHandler(R.id.pizza_ordering_button, MainMenu.class);
        bindButtonHandler(R.id.order_history_button, OrderHistoryActivity.class);
        bindButtonHandler(R.id.cart_button, CurrentOrderActivity.class);
        bindButtonHandler(R.id.home_button, MainMenu.class);
    }

    private void bindButtonHandler(int buttonId, Class<?> targetActivity) {
        findViewById(buttonId).setOnClickListener(v -> startActivity(new Intent(this, targetActivity)));
    }
}
