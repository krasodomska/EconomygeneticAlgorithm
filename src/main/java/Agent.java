import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Agent {
    String familyColor;
    int gold;
    boolean damageHouse;
    boolean newAgent = false;
    //when game check if agent have lumber - 0 -> produce 25% less, 1 -> lumber is spent, 2 -> spent lumber and has son
    int checkingLumbersMonth;
    int age;
    int takingPlace;

    static int numberOfBuyPerDay = 3;
    BuildingsListForDraw buildingsListForDraw = new BuildingsListForDraw();


    HashMap<BuildingName, Integer> buildings = new HashMap<>();
    HashMap<ItemName, Item> items = new HashMap<>();
    ArrayList<Integer> monthWithClothesDestruction = new ArrayList<>();
    ArrayList<Integer> monthWithHouseDestruction = new ArrayList<>();

    /**
     * crate list of items that agent have
     */
    void createItemsList() {
        for (ItemName name : ItemName.values()) {
            items.put(name, new Item(name));
        }
//        for (ItemName item : items.keySet()) {
//
//        }

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

    /**
     * set when hause will be destroy
     */
    void setMonthWithHouseDestruction() {
        monthWithHouseDestruction.add(2);
        monthWithHouseDestruction.add(5);
        monthWithHouseDestruction.add(11);

    }

    void takePlace() {
        takingPlace = 0;
        for (BuildingName building : buildings.keySet()) {
            takingPlace += Buildings.buildings.get(building).takingPlace;
        }
    }

    public Agent(String familyColor, int startGold, HashMap<BuildingName, Integer> buildings, int checkingLumbersMonth, HashMap<ItemName, Item> items) {
        this.gold = startGold;
        this.buildings.putAll(buildings);
        this.familyColor = familyColor;
        this.checkingLumbersMonth = checkingLumbersMonth;
        damageHouse = false;
        this.age = 0;
        createItemsList();
        setMonthWithHouseDestruction();
        setMonthWithClothesDestruction();

        for (ItemName item : items.keySet()) {
            if (this.items.containsKey(item)) {
                this.items.get(item).amount = items.get(item).amount;
                this.items.get(item).sellPrice = items.get(item).sellPrice;
                this.items.get(item).buyPrice = items.get(item).buyPrice;
//                System.out.println("items: " + this.items.get(item));

            }
        }

        takePlace();

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
            if (Buildings.buildings.get(building).toolNeed && items.get(ItemName.TOOL).amount == 0) modifier -= 0.5;
            //hardcoding ORCHAND
            if (building == BuildingName.ORCHAND && age < 12) return;
            items.get(Buildings.buildings.get(building).itemProduced).amount +=
                    Buildings.buildings.get(building).production(buildings.get(building), currentMonth) * modifier;
            if (Buildings.buildings.get(building).itemProduced == ItemName.GOLD) {
                gold += items.get(Buildings.buildings.get(building).itemProduced).amount;
                items.get(Buildings.buildings.get(building).itemProduced).amount = 0;

            }
        }
    }

    /**
     * take amout of item that was consumed during month
     *
     * @param currentMonth - some items are taken only at specific month
     */
    public void consumption(int currentMonth) {
        //food
        items.get(ItemName.FOOD).amount -= 10;

        //lumber
        if (monthWithHouseDestruction.contains(currentMonth) && items.get(ItemName.LUMBER).amount > 9) {
            items.get(ItemName.LUMBER).amount -= 10;
        } else {
            damageHouse = true;
        }


        if (currentMonth == checkingLumbersMonth) {
            if (items.get(ItemName.LUMBER).amount > 9) {
                items.get(ItemName.LUMBER).amount -= 10;
                if (items.get(ItemName.LUMBER).amount > 9 && !starvationDeath()) {
                    newAgent = true;
                    items.get(ItemName.LUMBER).amount -= 10;
                }
            } else {
                damageHouse = true;
            }
        }
        //clothes
        if (monthWithClothesDestruction.contains(currentMonth) && items.get(ItemName.CLOTHES).amount > 19) {
            items.get(ItemName.CLOTHES).amount -= 20;
        }

        //tool
        for (BuildingName building : buildings.keySet()) {
            if (Buildings.buildings.get(building).toolNeed && items.get(ItemName.TOOL).amount > 9) {
                items.get(ItemName.TOOL).amount -= 10;
            }
        }
    }

    /**
     * @return should this agent be deleted form agents list
     */
    public boolean starvationDeath() {
        return items.get(ItemName.FOOD).amount <= -30;
    }

    /**
     * random death
     * death ratio is bigger when agent have less clothes, food or damaged house
     * @param baseDeathRate
     */
    public boolean randomDeath(double baseDeathRate) {
        double random = Math.random();
        double foodModifier = Math.abs(Math.min(items.get(ItemName.FOOD).amount / 300, 0));
        double clothesModifier = (items.get(ItemName.CLOTHES).amount == 0) ? 2 : 1;
        double damageHauseModifier = (damageHouse) ? 2 : 1;

        if (random <= (baseDeathRate + foodModifier) * clothesModifier *damageHauseModifier)
            return true;
        return false;
    }

    public void reproduction() {

        this.gold /= 2;
//        for (ItemName item : items.keySet()) {
//            if (item == ItemName.FOOD) continue;
//            items.get(item).amount /= 2;
//        }

        Agent bornAgent = new Agent(familyColor, this.gold, inheriatanceBuildings(), inheriatanceLumberMonth(), this.items);
        if (Environment.noPlaceInWorld()) {
            bornAgent.buildingsDownGrade();
        }


        Agents.agents.add(bornAgent);
        Environment.takePlace(bornAgent);
        newAgent = false;
    }

    public void buildingsDownGrade() {
        HashSet<BuildingName> buildingForDemolition = new HashSet<>();

        for (BuildingName building : buildings.keySet()) {
            buildingForDemolition.add(building);
        }
        for (BuildingName building : buildingForDemolition) {
            ItemName item = Buildings.buildings.get(building).itemProduced;
            int buildingsAmount = buildings.get(building);
            buildings.remove(building);
            buildings.put(BuildingsListForDraw.baseBuilding.get(item), buildingsAmount);
        }
        takePlace();
    }

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

    /**
     * genetic check which buildings will be inherited and in which way
     *
     * @return childs list of buildings
     */
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

    /**
     * genetic inherietance of single building
     *
     * @param building - building form parent (this agent)
     * @return mutated building
     */
    BuildingName buildingMutation(BuildingName building) {
        double random = Math.random();
        if (random < 0.01) {
            return buildingsListForDraw.allBuildings.get(
                    (int) (Math.random() * (buildingsListForDraw.allBuildings.size() - 1)));
        } else if (random < 0.04) {
            return buildingsListForDraw.buildingsProductSameAmountOfItem.get(Buildings.buildings.get(building).scaledProfit).
                    get((int) (Math.random() * (buildingsListForDraw.buildingsProductSameAmountOfItem.get(Buildings.buildings.get(building).scaledProfit).size())));
        } else if (random < 0.1) {
            return buildingsListForDraw.buildingsProductSameItem.get(Buildings.buildings.get(building).itemProduced).
                    get((int) (Math.random() * (buildingsListForDraw.buildingsProductSameItem.get(Buildings.buildings.get(building).itemProduced).size())));
        }
        return building;
    }


    @Override
    public String toString() {
        String agent = familyColor + " age:" + age + " building:";
        for (BuildingName building : buildings.keySet()) {
            agent += " " + building + " " + buildings.get(building) + ",";
        }
//        agent += " taking place: " + takingPlace;
        agent += "; gold: " + gold + "; ";
        for (Item item : items.values()) {
            agent += " " + item.name + ": " + item.amount
                    + ", sellPrice: " + item.sellPrice + ", buyPrice: " + item.buyPrice;
        }

        return agent;
    }

}