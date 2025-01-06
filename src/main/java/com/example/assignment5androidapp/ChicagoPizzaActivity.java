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
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pizza.BBQChicken;
import pizza.BuildYourOwn;
import pizza.ChicagoPizza;
import pizza.Crust;
import pizza.Deluxe;
import pizza.Meatzza;
import pizza.NYPizza;
import pizza.Pizza;
import pizza.PizzaFactory;
import pizza.Size;
import pizza.Topping;


public class ChicagoPizzaActivity extends AppCompatActivity {

    private static final int TOPPINGLIMIT = 7;
    private final PizzaFactory pizzaFactory = new ChicagoPizza();
    private final List<Integer> selectedItems = new ArrayList<>();
    private Pizza pizza;
    private Spinner sizeSpinner;
    private Spinner pizzaTypeSpinner;
    private Spinner pizzaCrustSpinner;
    private ListView pizzaListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_pizza);

        initializeUIVariables();

        sizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        pizzaCrustSpinner = findViewById(R.id.pizzaCrustSpinner);
        pizzaListView = findViewById(R.id.pizzaListView);


        populateDropDowns();
        setupListeners();

    }

    private void initializeUIVariables() {
        sizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        pizzaCrustSpinner = findViewById(R.id.pizzaCrustSpinner);
        pizzaListView = findViewById(R.id.pizzaListView);
        setButtonHandlers(); // Move all button handling logic to a separate method
    }

    private void setButtonHandlers() {
        setButtonClickListener(R.id.clearButton, this::handleClearButtonListener);
        setButtonClickListener(R.id.placeOrderButton, this::handlePlaceOrderButtonListener);
        setButtonClickListener(R.id.pizza_ordering_button, this::addAnotherPizzaButtonHandler);
        setButtonClickListener(R.id.order_history_button, this::orderHistoryButtonHandler);
        setButtonClickListener(R.id.cart_button, this::cartButtonHandler);
        setButtonClickListener(R.id.home_button, this::homeButtonHandler);
    }

    private void setButtonClickListener(int buttonId, Runnable handler) {
        findViewById(buttonId).setOnClickListener(v -> handler.run());
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }

    private void addAnotherPizzaButtonHandler() {
        navigateTo(MainMenu.class);
    }

    private void orderHistoryButtonHandler() {
        navigateTo(OrderHistoryActivity.class);
    }

    private void cartButtonHandler() {
        navigateTo(CurrentOrderActivity.class);
    }

    private void homeButtonHandler() {
        navigateTo(MainMenu.class);
    }

    private void populateDropDowns() {
        populateSpinner(sizeSpinner, Arrays.asList(Size.SMALL, Size.MEDIUM, Size.LARGE));
        populateSpinner(pizzaTypeSpinner, Arrays.asList("Meatzza", "Deluxe", "BBQ Chicken", "Build Your Own"));
    }

    private <T> void populateSpinner(Spinner spinner, List<T> items) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, items);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);
    }

    private void setupListeners() {
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

        pizzaListView.setOnItemClickListener((parent, view, position, id) -> handlePizzaListViewListener(position));
    }

    private Pizza createPizza(Spinner pizzaTypeSpinner) {
        String selectedType = (String) pizzaTypeSpinner.getSelectedItem();
        Pizza createdPizza = switch (selectedType) {
            case "Deluxe" -> pizzaFactory.createDeluxe();
            case "Meatzza" -> pizzaFactory.createMeatzza();
            case "BBQ Chicken" -> pizzaFactory.createBBQChicken();
            case "Build Your Own" -> pizzaFactory.createBuildYourOwn();
            default -> null;
        };
        if (createdPizza != null)
            createdPizza.setStyle("Chicago");
        return createdPizza;
    }

    private void handlePizzaTypeSelection(Pizza pizza, Spinner pizzaCrustSpinner) {
        List<Crust> crustList = Collections.singletonList(pizza.getCrust());
        populateSpinner(pizzaCrustSpinner, crustList);
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
        priceTextView.setText(String.valueOf(pizza.price()));
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
            pizzaImage.setImageResource(R.drawable.chicago_build_your_own); // Replace with your drawable
        } else if (pizza instanceof BBQChicken) {
            pizzaImage.setImageResource(R.drawable.chicago_bbq_chicken); // Replace with your drawable
        } else if (pizza instanceof Meatzza) {
            pizzaImage.setImageResource(R.drawable.chicago_meatzza); // Replace with your drawable
        } else if (pizza instanceof Deluxe) {
            pizzaImage.setImageResource(R.drawable.chicago_deluxe); // Replace with your drawable
        } else {
            pizzaImage.setImageResource(R.drawable.chicago_deluxe); // Default image
        }
    }

    private void clearSelections() {
        pizzaTypeSpinner.setSelection(0);
        sizeSpinner.setSelection(0);
    }

    private void handlePizzaTypeListener(){
        selectedItems.clear();
        pizza = createPizza(pizzaTypeSpinner);
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
            Intent intent = new Intent(this, CurrentOrderActivity.class);
            startActivity(intent);

            if(pizza != null) {
                clearSelections();
            }
            Toast.makeText(this,  pizza.getSimpleName() + pizza.getStyle() + " added to Order", Toast.LENGTH_SHORT).show();
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

}
