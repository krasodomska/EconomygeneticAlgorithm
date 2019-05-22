package main;

import coreElements.ItemName;

public class Stock {
    ItemName name;
    int sellPrice = 1;
    int buyPrice = 1;
    int amount = 0;

    Stock(ItemName name){
        this.name = name;
//        sellPrice= startPrice;
//        buyPrice = startPrice;
//        amount = startAmount;
    }

    @Override
    public String toString(){
        return name + ": " + amount;
    }
}
