package main;

import coreElements.BuildingName;
import coreElements.ItemName;

import java.util.ArrayList;
import java.util.List;

public class Building {
    String name;
    ItemName itemProduced;

    int scaledProfit;
    int unscaledProfit;
    boolean toolNeed = false;
    int takingPlace = 0;
    private List<Integer> monthWithProfit = new ArrayList<>();
    private Integer ageProfitStart = 0;

    Building(BuildingName name, ItemName itemProduced, int scaledProfit, int unscaledProfit, ArrayList<Integer> monthWithProfit) {
        this.name = name.toString();
        this.scaledProfit = scaledProfit;
        this.unscaledProfit = unscaledProfit;
        this.itemProduced = itemProduced;
        this.monthWithProfit.addAll(monthWithProfit);
        if (scaledProfit > 10) takingPlace = 1;
    }

    Building(BuildingName name, ItemName itemProduced, int scaledProfit, int unscaledProfit, ArrayList<Integer> monthWithProfit, boolean toolNeed) {
        this.name = name.toString();
        this.scaledProfit = scaledProfit;
        this.unscaledProfit = unscaledProfit;
        this.itemProduced = itemProduced;
        this.monthWithProfit.addAll(monthWithProfit);
        this.toolNeed = toolNeed;
        if (scaledProfit > 10) takingPlace = 1;
    }

    public Building(String name, ItemName itemProduced, int scaledProfit, int unscaledProfit, List<Integer> monthWithProfit, Integer ageProfitStart, boolean toolNeed) {
        this.name = name;
        this.itemProduced = itemProduced;
        this.scaledProfit = scaledProfit;
        this.unscaledProfit = unscaledProfit;
        this.monthWithProfit = monthWithProfit;
        this.ageProfitStart = ageProfitStart;
        this.toolNeed = toolNeed;
    }

    /**
     * following methods and functions are generic and required for serialising
     */
    public Building() {
    }

    /**
     * @param numberOfBuilding - how many agentBuildings agent have
     *                         production is calculate form pattern scaledProfit*numberOfBuilding + unscaledProfit
     * @return - how many items is produce form agentBuildings with the same type, which belong to one agent
     */
    public int production(int numberOfBuilding, int currentMonth, int age) {
        if (age >= ageProfitStart) {
            if (monthWithProfit.contains(currentMonth))
                return scaledProfit * numberOfBuilding + unscaledProfit;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemName getItemProduced() {
        return itemProduced;
    }

    public void setItemProduced(ItemName itemProduced) {
        this.itemProduced = itemProduced;
    }

    public int getScaledProfit() {
        return scaledProfit;
    }

    public void setScaledProfit(int scaledProfit) {
        this.scaledProfit = scaledProfit;
    }

    public int getUnscaledProfit() {
        return unscaledProfit;
    }

    public void setUnscaledProfit(int unscaledProfit) {
        this.unscaledProfit = unscaledProfit;
    }

    public boolean isToolNeed() {
        return toolNeed;
    }

    public void setToolNeed(boolean toolNeed) {
        this.toolNeed = toolNeed;
    }

    public int getTakingPlace() {
        return takingPlace;
    }

    public void setTakingPlace(int takingPlace) {
        this.takingPlace = takingPlace;
    }

    public List<Integer> getMonthWithProfit() {
        return monthWithProfit;
    }

    public void setMonthWithProfit(List<Integer> monthWithProfit) {
        this.monthWithProfit = monthWithProfit;
    }

    public Integer getAgeProfitStart() {
        return ageProfitStart;
    }

    public void setAgeProfitStart(Integer ageProfitStart) {
        this.ageProfitStart = ageProfitStart;
    }
}
