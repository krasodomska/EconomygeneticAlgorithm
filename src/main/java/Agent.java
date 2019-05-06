import java.util.ArrayList;
import java.util.HashMap;

public class Agent {
    String familyColor;
    int startItemsAmount;
    int startPrice;
    int gold;
    boolean damageHouse;
    boolean newAgent = false;
    //when game check if agent have lumber - 0 -> produce 25% less, 1 -> lumber is spent, 2 -> spent lumber and has son
    int checkingLumbersMonth;

    static int numberOfBuyPerDay = 3;
    BuildingsListForDraw buildingsListForDraw = new BuildingsListForDraw();


    HashMap<BuildingName, Integer> buildings = new HashMap<>();
    HashMap<ItemName, Item> items = new HashMap<>();
    ArrayList<Integer> monthWithClothesDestruction = new ArrayList<>();
    ArrayList<Integer> monthWithHouseDestruction = new ArrayList<>();

    /**
     * crate list of items that agent have
     */
    void createItemsPrices() {
        for (ItemName name : ItemName.values()) {
            items.put(name, new Item(name, startPrice, startItemsAmount));
        }
    }

    /**
     * set when clothes will be destroy
     */
    void setMonthWithClothesDestruction() {
        monthWithClothesDestruction.add(1);
        monthWithClothesDestruction.add(4);
        monthWithClothesDestruction.add(7);
        monthWithClothesDestruction.add(10);
    }

    void setMonthWithHousDestruction() {
        monthWithHouseDestruction.add(1);
        monthWithHouseDestruction.add(5);
        monthWithHouseDestruction.add(11);

    }

    public Agent(String familyColor, int startPrice, int startItemsAmount, int startGold, HashMap<BuildingName, Integer> buildings, int checkingLumbersMonth) {
        this.startPrice = startPrice;
        this.startItemsAmount = startItemsAmount;
        this.gold = startGold;
        this.buildings.putAll(buildings);
        this.familyColor = familyColor;
        this.checkingLumbersMonth = checkingLumbersMonth;
        damageHouse = false;
        createItemsPrices();
        setMonthWithClothesDestruction();
    }

    /**
     * add amount of item that was produced during month
     *
     * @param currentMonth - checking if in this month production work
     */
    public void production(int currentMonth) {
        for (BuildingName building : buildings.keySet()) {
            float modifier = 1;
            if (items.get(ItemName.CLOTHES).amount == 0) modifier -= 0.25;
            if (damageHouse) modifier -= 0.25;
            if (Buildings.buildings.get(building).toolNeed && items.get(ItemName.TOOL).amount == 0) modifier -= 0.25;

            items.get(Buildings.buildings.get(building).itemProduced).amount +=
                    Buildings.buildings.get(building).production(buildings.get(building), currentMonth) * modifier;
        }
    }

    /**
     * take amout of item that was consumed during month
     *
     * @param currentMonth - some items are taken only at specific month
     */
    public void consumption(int currentMonth) {
        items.get(ItemName.FOOD).amount -= 2;

        if (monthWithHouseDestruction.contains(currentMonth) && items.get(ItemName.LUMBER).amount > 0) {
            items.get(ItemName.LUMBER).amount--;
        }
        if (currentMonth == checkingLumbersMonth) {
            if (items.get(ItemName.LUMBER).amount > 0) {
                items.get(ItemName.LUMBER).amount--;
                if (items.get(ItemName.LUMBER).amount > 0 && !starvationDeath()) {
                    newAgent = true;
                    items.get(ItemName.LUMBER).amount--;
                }
            } else {
                damageHouse = true;
            }
        }

        if (monthWithClothesDestruction.contains(currentMonth) && items.get(ItemName.CLOTHES).amount > 0) {
            items.get(ItemName.CLOTHES).amount--;
        }
        for (BuildingName building : buildings.keySet()) {
            if (Buildings.buildings.get(building).toolNeed && items.get(ItemName.TOOL).amount > 0) {
                items.get(ItemName.TOOL).amount--;
            }
        }
    }

    /**
     * @return should this agent be deleted form agents list
     */
    public boolean starvationDeath() {
        return items.get(ItemName.FOOD).amount <= -3;
    }

    public void reproduction() {

        ExistingWorld.agents.add(new Agent(familyColor, startPrice, startItemsAmount, 1000, inheriatanceBuildings(), inheriatanceLumberMonth()));
        System.out.println("new born");
        newAgent = false;
    }

    //a little hardcoding

    /**
     * genetic check probability if checkingLumberMonth should be changed and how much
     *
     * @return checkingLumberMonth for child of this agent
     */
    int inheriatanceLumberMonth() {
        double random = Math.random();
        if (random < 0.005) {
            if (checkingLumbersMonth + 2 > 12)
                return checkingLumbersMonth + 2 - 12;
            else return checkingLumbersMonth + 2;
        } else if (random < 0.01) {
            if (checkingLumbersMonth - 2 < 1)
                return checkingLumbersMonth - 2 + 12;
            else return checkingLumbersMonth - 2;
        } else if (random < 0.025) {
            if (checkingLumbersMonth + 1 > 12)
                return checkingLumbersMonth + 1 - 12;
            else return checkingLumbersMonth + 1;
        } else if (random < 0.04) {
            if (checkingLumbersMonth - 1 < 1)
                return checkingLumbersMonth - 1 + 12;
            else return checkingLumbersMonth - 1;

        }
        return checkingLumbersMonth;
    }

    HashMap<BuildingName, Integer> inheriatanceBuildings() {
        HashMap<BuildingName, Integer> newBuildings = new HashMap<>();

        for (BuildingName building : buildings.keySet()) {
            int howMany = buildings.get(building);
            while (howMany > 0) {
                BuildingName newBuilding = buildingMutation(building);
                if (newBuildings.containsKey(newBuilding)) {
                    newBuildings.put(newBuilding, newBuildings.get(newBuilding) + 1);
                } else {
                    newBuildings.put(newBuilding, 1);
                }
                howMany--;
            }
        }


        return newBuildings;
    }

    BuildingName buildingMutation(BuildingName building) {
        double random = Math.random();
        if (random < 0.01) {
            return buildingsListForDraw.allBuildings.get(
                    (int) (Math.random()*(buildingsListForDraw.allBuildings.size()-1)));
        } else if (random < 0.04) {
            return buildingsListForDraw.buildingsProductSameAmoutOfItem.get(Buildings.buildings.get(building).scaledProfit).
                    get((int) (Math.random()*(buildingsListForDraw.buildingsProductSameAmoutOfItem.get(Buildings.buildings.get(building).scaledProfit).size())));
        } else if (random < 0.1) {
            return buildingsListForDraw.buildingsProductSameItem.get(Buildings.buildings.get(building).itemProduced).
                    get((int) (Math.random() * (buildingsListForDraw.buildingsProductSameItem.get(Buildings.buildings.get(building).itemProduced).size())));
        }
        return building;
    }

    @Override
    public String toString() {
        String agent = familyColor + " building:";
        for (BuildingName building : buildings.keySet()) {
            agent += " " + building + " " + buildings.get(building) + ",";
        }
        agent += "; gold: " + gold + "; ";
        for (Item item : items.values()) {
            agent += " " + item.name + ": " + item.amount + ",";
        }

        return agent;
    }
}