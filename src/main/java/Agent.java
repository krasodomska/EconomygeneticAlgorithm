import java.util.HashMap;

public class Agent {
    String familyColor;
    int startItemsAmount;
    int startPrice;
    int gold;
    int checkingMonth;
    boolean damageHaus;
    //when game check if agent have lumber - 0 -> produce 25% less, 1 -> lumber is spent, 2 -> spent lumber and has son
    int chackingLumbersMonth;

    static int numberOfBuyPerDay = 3;

    //Tour currentTour; //??

    HashMap<BuildingName, Integer> buildings = new HashMap<>();
    HashMap<ItemName, Integer> itemsSellPrices = new HashMap<>();
    HashMap<ItemName, Integer> itemsBuyPrices = new HashMap<>();
    HashMap<ItemName, Integer> itemsAmount = new HashMap<>();

    void createItemsPrices() {
        for (ItemName name : ItemName.values()) {
            itemsSellPrices.put(name, startPrice);
            itemsBuyPrices.put(name, startPrice);
            itemsAmount.put(name, startItemsAmount);
        }
    }

    public Agent(int startPrice, int startItemsAmount, int startGold, HashMap<BuildingName, Integer> buildings) {
        this.startPrice = startPrice;
        this.startItemsAmount = startItemsAmount;
        this.gold = startGold;
        this.buildings.putAll(buildings);
        createItemsPrices();
    }

    public void production(int currentMonth) {
        for (BuildingName building : buildings.keySet()) {
            itemsAmount.put(Buildings.buildings.get(building).itemProduced,
                    //itemsAmount.get(Buildings.buildings.get(building).itemProduced) +
                            Buildings.buildings.get(building).production(buildings.get(building), currentMonth));
        }
    }

    public void consumption(int currentMonth){
        itemsAmount.put(ItemName.FOOD, itemsAmount.get(ItemName.FOOD) - 1);


    }

    @Override
    public String toString() {
        String agent = "Agent:building:";
        for (BuildingName building : buildings.keySet()) {
            agent += " " + building + " " + buildings.get(building) + ",";
        }
        agent += "; gold: " + gold + "; ";
        for (ItemName item : itemsAmount.keySet()) {
            agent += " " + item + ": " + itemsAmount.get(item) + ",";
        }

        return agent;
    }
}