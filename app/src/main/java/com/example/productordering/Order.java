package com.example.productordering;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private final String flavour;
    private final ArrayList<String> toppings;
    private final String scoops;
    private final String syrup;

    public Order(String flavour, ArrayList<String> toppings, String scoops, String syrup){
        this.flavour = flavour;
        this.toppings = new ArrayList<>(toppings);
        this.scoops = scoops;
        this.syrup = syrup;
    }

    public String getFlavour() {
        return flavour;
    }

    public String getScoops() {
        return scoops;
    }

    public String getSyrup() {
        return syrup;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }
}
