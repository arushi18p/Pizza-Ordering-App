package com.example.assignment5androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pizza.BBQChicken;
import pizza.BuildYourOwn;
import pizza.Crust;
import pizza.Deluxe;
import pizza.Meatzza;
import pizza.NYPizza;
import pizza.Pizza;
import pizza.PizzaFactory;
import pizza.Size;
import pizza.Topping;

public class NewYorkPizzaActivity extends AppCompatActivity {
    private static final int TOPPINGLIMIT = 7;
    private final PizzaFactory pizzaFactory = new NYPizza();
    private final List<Integer> selectedItems = new ArrayList<>();
    private Spinner sizeSpinner;
    private Spinner pizzaTypeSpinner;
    private Spinner pizzaCrustSpinner;
    private ListView pizzaListView;
    private Pizza pizza;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_pizza);
        Button clearButton = findViewById(R.id.clearButton);
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        ImageButton addAnotherPizzaButton = findViewById(R.id.pizza_ordering_button);
        ImageButton orderHistoryButton = findViewById(R.id.order_history_button);
        ImageButton cartButton = findViewById(R.id.cart_button);
        ImageButton homeButton = findViewById(R.id.home_button);
        initializeUIVariables();
        populateDropDowns(sizeSpinner, pizzaTypeSpinner);

        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handlePizzaTypeListener();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSizeListener();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        pizzaListView.setOnItemClickListener((parent, view, position, id) -> {
            handlePizzaListViewListener(position);
        });

        clearButton.setOnClickListener(v -> handleClearButtonListener());
        placeOrderButton.setOnClickListener(v -> handlePlaceOrderButtonListener());
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


    private void populateDropDowns(Spinner sizeSpinner, Spinner pizzaTypeSpinner) {
        //Populating Size Dropdown
        List<Size> sizeList = Arrays.asList(Size.SMALL, Size.MEDIUM, Size.LARGE);
        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, sizeList);
        sizeAdapter.setDropDownViewResource(R.layout.spinner_layout);
        sizeSpinner.setAdapter(sizeAdapter);

        //Populating Crust Dropdown
        List<String> pizzaTypeList = Arrays.asList("Meatzza", "Deluxe", "BBQ Chicken", "Build Your Own");
        ArrayAdapter<String> pizzaTypeAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, pizzaTypeList);
        pizzaTypeAdapter.setDropDownViewResource(R.layout.spinner_layout);
        pizzaTypeSpinner.setAdapter(pizzaTypeAdapter);
    }

    private Pizza createPizza(Spinner pizzaTypeSpinner, PizzaFactory pizzaFactory) {
        //pizzaStyle = "New York";
        Pizza createdPizza = switch ((String) pizzaTypeSpinner.getSelectedItem()) {
           // return switch ((String) pizzaTypeSpinner.getSelectedItem()) {
            case "Deluxe" -> pizzaFactory.createDeluxe();
            case "Meatzza" -> pizzaFactory.createMeatzza();
            case "BBQ Chicken" -> pizzaFactory.createBBQChicken();
            case "Build Your Own" -> pizzaFactory.createBuildYourOwn();
            default -> null;
        };
        if (createdPizza != null) {
            createdPizza.setStyle("New York");
        }
        return createdPizza;
    }

    private void handlePizzaTypeSelection(Pizza pizza, Spinner pizzaCrustSpinner) {
        List<Crust> pizzaCrustList = Collections.singletonList(pizza.getCrust());
        ArrayAdapter<Crust> pizzaCrustAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, pizzaCrustList);
        pizzaCrustAdapter.setDropDownViewResource(R.layout.spinner_layout);
        pizzaCrustSpinner.setAdapter(pizzaCrustAdapter);
        pizzaCrustSpinner.setSelection(0);
    }

    private void handleToppings(Pizza pizza) {
        ListView pizzaListView = findViewById(R.id.pizzaListView);
        List<Topping> pizzaList = pizza.getToppings();
        ArrayAdapter<Topping> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaList);
        pizzaListView.setAdapter(adapter1);
        if(pizza instanceof BuildYourOwn){
            pizzaList = Arrays.asList(Topping.values());
            adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, pizzaList);
            pizzaListView.setAdapter(adapter1);
        }
    }

    private void handlePrice(Pizza pizza) {
        EditText priceTextView = findViewById(R.id.priceTextViewDynamic);
        priceTextView.setText(((Double)pizza.price()).toString());
    }

    private void handleBuildYourOwn(List<Integer> selectedItems, int position, ListView pizzaListView) {
        if (selectedItems.contains(position)) {
            // Item is already selected, deselect it
            selectedItems.remove(Integer.valueOf(position));
            pizza.removeToppings(Topping.values()[position]);
            pizzaListView.setItemChecked(position, false); // Update UI
        } else if (selectedItems.size() < TOPPINGLIMIT) {
            // Item is not selected and max limit is not reached, select it
            selectedItems.add(position);
            pizza.addToppings(Topping.values()[position]);
            pizzaListView.setItemChecked(position, true); // Update UI
        } else {
            // Max selection limit reached, show a message
            pizzaListView.setItemChecked(position, false);
            Toast.makeText(this, "You can only select up to " + TOPPINGLIMIT + " items.", Toast.LENGTH_SHORT).show();
        }
        handlePrice(pizza);
    }

    private void handlePizzaImage(Pizza pizza) {
        ImageView pizzaImage = findViewById(R.id.pizzaImageView);
        if (pizza instanceof BuildYourOwn) {
            pizzaImage.setImageResource(R.drawable.new_york_cheese); // Replace with your drawable
        } else if (pizza instanceof BBQChicken) {
            pizzaImage.setImageResource(R.drawable.new_york_bbq); // Replace with your drawable
        } else if (pizza instanceof Meatzza) {
            pizzaImage.setImageResource(R.drawable.new_york_meatzza); // Replace with your drawable
        } else if (pizza instanceof Deluxe) {
            pizzaImage.setImageResource(R.drawable.new_york_deluxe); // Replace with your drawable
        } else {
            pizzaImage.setImageResource(R.drawable.newyorkstylepizzaphoto); // Default image
        }
    }

    private void clearSelections() {
        Spinner pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        Spinner pizzaSizeSpinner = findViewById(R.id.pizzaSizeSpinner);

        pizzaTypeSpinner.setSelection(0);
        pizzaSizeSpinner.setSelection(0);
    }

    private void handlePizzaTypeListener(){
        selectedItems.clear();
        pizza = createPizza(pizzaTypeSpinner, pizzaFactory);
        assert pizza != null;
        handlePizzaTypeSelection(pizza, pizzaCrustSpinner);
        handleToppings(pizza);
        pizza.setSize((Size) sizeSpinner.getSelectedItem());
        handlePrice(pizza);
        handlePizzaImage(pizza);
    }

    private void handlePlaceOrderButtonListener(){
        if(pizza != null){
            GlobalData.getInstance().getCurrentOrder().getPizzas().add(pizza);
            Intent intent = new Intent(NewYorkPizzaActivity.this, CurrentOrderActivity.class);
            startActivity(intent);

            if(pizza != null) {
                clearSelections();
            }
            Toast.makeText(this,pizza.getStyle() + " " + pizza.getSimpleName() + " added to order.", Toast.LENGTH_SHORT).show();
        } 
        else{
            Toast.makeText(this, "Please select a pizza", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleClearButtonListener(){
        clearSelections();
        Toast.makeText(this, pizza.getSimpleName() + " selections cleared ", Toast.LENGTH_SHORT).show();
    }

    private void handleSizeListener(){
        pizza.setSize((Size) sizeSpinner.getSelectedItem());
        handlePrice(pizza);
    }

    private void handlePizzaListViewListener(int position){
        if(pizza instanceof BuildYourOwn) {
            handleBuildYourOwn(selectedItems, position, pizzaListView);
        }
    }

    private void initializeUIVariables(){
        sizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        pizzaCrustSpinner = findViewById(R.id.pizzaCrustSpinner);
        pizzaListView = findViewById(R.id.pizzaListView);
    }



}
