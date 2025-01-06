package com.example.assignment5androidapp;

import java.util.ArrayList;

import pizza.*;

public final class GlobalData {

    private static GlobalData instance;
    
    private Order currentOrder = new Order();
    private ArrayList<Order> orderHistoryList = new ArrayList<>();
    

    private GlobalData() {
     //do nothing
    }

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }


    public void setOrderHistoryList(ArrayList<Order> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    public ArrayList<Order> getOrderHistoryList() {
        if (orderHistoryList == null) {
            orderHistoryList = new ArrayList<>();
        }
        return orderHistoryList;
    }


}
