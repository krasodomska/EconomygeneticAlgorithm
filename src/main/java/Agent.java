import java.util.HashMap;

public class Agent {
    String familyColor;
    int startItemsAmount;
    int startPrice;
    int gold;

    boolean damageHaus;
    //when game check if agent have lumber - 0 -> produce 25% less, 1 -> lumber is spent, 2 -> spent lumber and has son
    int chackingLumbersMonth;

    static int numberOfBuyPerDay = 3;

    //Tour currentTour; //??

    HashMap<BuildingName, Integer> buildings = new HashMap<>();
    HashMap<ItemName, Integer> itemsSellPrices = new HashMap<>();
    HashMap<ItemName, Integer> itemsBuyPrices = new HashMap<>();
    HashMap<ItemName, Integer> itemsAmount = new HashMap<>();

    void createItemsPrices(){
        for(ItemName name: ItemName.values()){
            itemsSellPrices.put(name, startPrice);
            itemsBuyPrices.put(name, startPrice);
            itemsAmount.put(name, startItemsAmount);
        }
    }

    public Agent(int startPrice, int startItemsAmount, int startGold, HashMap<BuildingName, Integer> buildings){
        this.startPrice = startPrice;
        this.startItemsAmount = startItemsAmount;
        this.gold = startGold;
        this.buildings.putAll(buildings);
    }

}