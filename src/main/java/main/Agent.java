package main;

import coreElements.BuildingName;
import coreElements.ItemName;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static coreElements.Utils.randomBetween;
import static main.Buildings.buildings;
import static main.BuildingsListForDraw.*;

public class Agent {
    String familyColor;
    int gold;
    boolean damageHouse;
    boolean newAgent = false;
    //when game check if agent have lumber - 0 -> produce 25% less, 1 -> lumber is spent, 2 -> spent lumber and has son
    int checkingLumbersMonth;
    int age;
    int takingPlace;

    BuildingsListForDraw buildingsListForDraw = new BuildingsListForDraw();


    HashMap<BuildingName, Integer> agentBuildings = new HashMap<>();
    HashMap<ItemName, Stock> items = new HashMap<>();

    public Agent(String familyColor, int startGold, HashMap<BuildingName, Integer> agentBuildings, int checkingLumbersMonth, Map<ItemName, Stock> items) {
        this.gold = startGold;
        this.agentBuildings.putAll(agentBuildings);
        this.familyColor = familyColor;
        this.checkingLumbersMonth = checkingLumbersMonth;
        damageHouse = false;
        this.age = 0;
        createItemsList();

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
     * crate list of items that agent have
     */
    void createItemsList() {
        for (ItemName name : ItemName.values()) {
            items.put(name, new Stock(name));
        }
    }

    /**
     * checking how many place take agentBuildings form this agent
     */
    void takePlace() {
        takingPlace = 0;
        for (BuildingName building : agentBuildings.keySet()) {
            takingPlace += buildings.get(building).takingPlace;
        }
    }

    /**
     * add amount of item that was produced during month
     *
     * @param currentMonth - checking if in this month production work
     */
    public void production(int currentMonth) {
        for (BuildingName building : agentBuildings.keySet()) {
            Building currentBuilding = buildings.get(building);
            items.get(currentBuilding.itemProduced).amount +=
                    currentBuilding.production(agentBuildings.get(building), currentMonth, age) * productionModifier(building);
        }
        mintGold();
    }

    private void mintGold() {
        if (items.get(ItemName.GOLD).amount > 0) {
            gold += items.get(ItemName.GOLD).amount;
            items.get(ItemName.GOLD).amount = 0;
        }
    }

    private float productionModifier(BuildingName building) {
        float modifier = 1;
        if (items.get(ItemName.CLOTHES).amount == 0) modifier -= 0.25;
        if (damageHouse) modifier -= 0.25;
        if (buildings.get(building).toolNeed && items.get(ItemName.TOOL).amount == 0) modifier -= 0.5;
        return modifier;
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
        if (Environment.monthWithHouseDestruction.contains(currentMonth) && items.get(ItemName.LUMBER).amount >= 10) {
            items.get(ItemName.LUMBER).amount -= 10;
        } else {
            damageHouse = true;
        }


        if (currentMonth == checkingLumbersMonth) {
            if (items.get(ItemName.LUMBER).amount >= 10) {
                items.get(ItemName.LUMBER).amount -= 10;
                if (items.get(ItemName.LUMBER).amount >= 10 && !starvationDeath()) {
                    newAgent = true;
                    items.get(ItemName.LUMBER).amount -= 10;
                }
            } else {
                damageHouse = true;
            }
        }
        //clothes
        if (Environment.monthWithClothesDestruction.contains(currentMonth) && items.get(ItemName.CLOTHES).amount >= 20) {
            items.get(ItemName.CLOTHES).amount -= 20;
        }

        //tool
        for (BuildingName building : agentBuildings.keySet()) {
            if (buildings.get(building).toolNeed && items.get(ItemName.TOOL).amount >= 10) {
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
     *
     * @param baseDeathRate
     */
    public boolean randomDeath(double baseDeathRate) {
        double random = Math.random();
        double foodModifier = Math.abs(Math.min(items.get(ItemName.FOOD).amount / 300, 0));
        double clothesModifier = (items.get(ItemName.CLOTHES).amount == 0) ? 2 : 1;
        double damageHouseModifier = (damageHouse) ? 2 : 1;

        return random <= (baseDeathRate + foodModifier) * clothesModifier * damageHouseModifier;
    }

    /**
     * create new agent, when reproduction is possible
     */
    public void reproduction() {

        this.gold /= 2;

        Agent bornAgent = new Agent(familyColor, this.gold, inheritanceBuildings(), inheritanceLumberMonth(), this.items);
        if (Environment.noPlaceInWorld()) {
            bornAgent.buildingsDownGrade();
        }

        Agents.agents.add(bornAgent);
        Environment.takePlace(bornAgent);
        newAgent = false;
    }

    /**
     * when there is too little place agents can have only base agentBuildings
     */
    private void buildingsDownGrade() {
        Set<BuildingName> buildingForDemolition = new HashSet<>(agentBuildings.keySet());

        for (BuildingName building : buildingForDemolition) {
            ItemName item = buildings.get(building).itemProduced;
            int buildingsAmount = agentBuildings.get(building);
            agentBuildings.remove(building);
            agentBuildings.put(BuildingsListForDraw.baseBuilding.get(item), buildingsAmount);
        }
        takePlace();
    }

    /**
     * genetic check probability if checkingLumberMonth should be changed and how much
     *
     * @return checkingLumberMonth for child of this agent
     */
    int inheritanceLumberMonth() {
        double random = Math.random();
        if (random < 0.005) return (checkingLumbersMonth + 2) % 12;
        else if (random < 0.01) return (checkingLumbersMonth - 2) % 12;
        else if (random < 0.025) return (checkingLumbersMonth + 1) % 12;
        else if (random < 0.04) return (checkingLumbersMonth - 1) % 12;
        return checkingLumbersMonth;
    }

    /**
     * genetic check which agentBuildings will be inherited and in which way
     *
     * @return childs list of agentBuildings
     */
    HashMap<BuildingName, Integer> inheritanceBuildings() {
        HashMap<BuildingName, Integer> newBuildings = new HashMap<>();

        for (BuildingName building : agentBuildings.keySet()) {
            int howMany = agentBuildings.get(building);
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
     * genetic inheritance of single building
     *
     * @param building - building form parent (this agent)
     * @return mutated building
     */
    BuildingName buildingMutation(BuildingName building) {
        double random = Math.random();
        if (random < 0.01) return allBuildings.get(randomBetween(0, allBuildings.size() - 1));
        else if (random < 0.04) return getBuildingsWithEqualProfit(building).get(randomBetween(0, getBuildingsWithEqualProfit(building).size()));
        else if (random < 0.1) return getBuildingsWithSameItem(building).get(randomBetween(0, getBuildingsWithSameItem(building).size()));
        return building;
    }


    @Override
    public String toString() {
        String agent = familyColor + " age:" + age + " building:";
        for (BuildingName building : agentBuildings.keySet()) {
            agent += " " + building + " " + agentBuildings.get(building) + ",";
        }
//        agent += " taking place: " + takingPlace;
        agent += "; gold: " + gold + "; ";
        for (Stock stock : items.values()) {
            agent += " " + stock.name + ": " + stock.amount
                    + ", sellPrice: " + stock.sellPrice + ", buyPrice: " + stock.buyPrice;
        }

        return agent;
    }

}