package com.example.assignment5androidapp;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pizza.Pizza;
import pizza.Topping;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private static final int PADDINGSIXTEEN = 16;
    private static final int PADDING_TWO_HUNDRED = 200;
    private static final int TRAILINGCOMMAS = 2;
    
    private List<Pizza> pizzas;
    private int selectedPosition = RecyclerView.NO_POSITION; // Track selected item
    private Context context;
    

    public PizzaAdapter(List<Pizza> pizzas, Context context) {
        this.pizzas = pizzas != null ? pizzas : new ArrayList<>(); // Handle null list
        this.context=context;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(PADDINGSIXTEEN, PADDINGSIXTEEN, PADDINGSIXTEEN, PADDINGSIXTEEN);
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(PADDING_TWO_HUNDRED, PADDING_TWO_HUNDRED);
        imageView.setLayoutParams(imageParams);
        imageView.setId(View.generateViewId());
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textView.setLayoutParams(textParams);
        textView.setPadding(PADDINGSIXTEEN, 0, 0, 0);
        textView.setTextSize(PADDINGSIXTEEN);
        textView.setTextColor(Color.BLACK);
        textView.setId(View.generateViewId());

        layout.addView(imageView);
        layout.addView(textView);
        return new PizzaViewHolder(layout, imageView, textView);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        if (position >= pizzas.size()) {
            return;
        }
        Pizza pizza = pizzas.get(position);
        String pizzaString = pizza.getStyle() + "(" + pizza.getSimpleName() + ")" + ", Crust: " + pizza.getCrust() +
                ", Size: " + pizza.getSize() + ", Price: $" + pizza.price() +
                ", Toppings: " + buildToppingsString(pizza.getToppings());
        holder.pizzaTextView.setText(pizzaString);
        holder.pizzaImageView.setImageResource(getPizzaImage(pizza.getStyle(), pizza.getSimpleName()));

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            // Update visuals for selection
            if (position == selectedPosition) {
                holder.itemView.setBackgroundColor(Color.CYAN); // Highlight selected item
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Default color
            }

            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);
            Log.d("PizzaAdapter", "Pizza selected: " + pizza.getSimpleName() + ", Position: " + selectedPosition);

        });
    }

    private int getPizzaImage(String pizzaStyle, String pizzaName) {
        if (pizzaStyle.equalsIgnoreCase("New York")) {
            switch (pizzaName) {
                case "Meatzza":
                    return R.drawable.new_york_meatzza;
                case "Deluxe":
                    return R.drawable.new_york_deluxe;
                case "BBQChicken":
                    return R.drawable.new_york_bbq;
                case "BuildYourOwn":
                    return R.drawable.new_york_cheese;
                default:
                    return R.drawable.newyorkstylepizzaphoto;
            }
        } else if (pizzaStyle.equalsIgnoreCase("Chicago")) {
            switch (pizzaName) {
                case "Meatzza":
                    return R.drawable.chicago_meatzza;
                case "Deluxe":
                    return R.drawable.chicago_deluxe;
                case "BBQChicken":
                    return R.drawable.chicago_bbq_chicken;
                case "BuildYourOwn":
                    return R.drawable.chicago_build_your_own;
                default:
                    return R.drawable.chicagostylepizzaphoto;
            }
        }
        // Fallback for undefined styles
        return R.drawable.pizza_icon;
    }


    private String buildToppingsString(List<Topping> toppings) {
        if (toppings == null || toppings.isEmpty()) {
            return "No toppings";
        }
        StringBuilder toppingsString = new StringBuilder();
        for (Topping topping : toppings) {
            toppingsString.append(topping.toString()).append(", ");
        }
        if (toppingsString.length() > 0) {
            toppingsString.setLength(toppingsString.length() - TRAILINGCOMMAS); // Remove trailing comma
        }
        return toppingsString.toString();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void removeItem(int position) {
        if (position >= 0 && position < pizzas.size()) {
            pizzas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, pizzas.size()-position);
            selectedPosition = RecyclerView.NO_POSITION; // Reset selection
        }
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaTextView;
        ImageView pizzaImageView;


        public PizzaViewHolder(@NonNull LinearLayout layout, ImageView pizzaImageView, TextView pizzaTextView) {
            super(layout);
            this.pizzaTextView = pizzaTextView;
            this.pizzaImageView=pizzaImageView;
        }
    }
}
