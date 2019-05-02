import java.util.ArrayList;

public class Building {
    int scaledProfit;
    int unscaledProfit;
    ItemName itemProduced;
    BuildingName name;
    ArrayList<Integer> monthWithProfit = new ArrayList<Integer>();

    Building(BuildingName name, int scaledProfit, int unscaledProfit, ItemName itemProduced, ArrayList<Integer> monthWithProfit) {
        this.name = name;
        this.scaledProfit = scaledProfit;
        this.unscaledProfit = unscaledProfit;
        this.itemProduced = itemProduced;
        this.monthWithProfit.addAll(monthWithProfit);
    }

    /**
     * @param numberOfBuilding - how many buildings agent have
     *                         production is calculate form pattern scaledProfit*numberOfBuilding + unscaledProfit
     * @return - how many items is produce form buildings with the same type, which belong to one agent
     */
    public int production(int numberOfBuilding, int currentMonth) {
        if (monthWithProfit.contains(currentMonth))
            return scaledProfit * numberOfBuilding + unscaledProfit;
        else return 0;
    }

    /**
     * following methods and functions are generic and required for serialising
     */
    public Building() {
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

    public ItemName getItemProduced() {
        return itemProduced;
    }

    public void setItemProduced(ItemName itemProduced) {
        this.itemProduced = itemProduced;
    }

    public BuildingName getName() {
        return name;
    }

    public void setName(BuildingName name) {
        this.name = name;
    }

    public ArrayList<Integer> getMonthWithProfit() {
        return monthWithProfit;
    }

    public void setMonthWithProfit(ArrayList<Integer> monthWithProfit) {
        this.monthWithProfit = monthWithProfit;
    }
}
