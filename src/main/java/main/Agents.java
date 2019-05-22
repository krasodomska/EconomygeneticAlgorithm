package main;

import coreElements.BuildingName;
import coreElements.ItemName;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static coreElements.Utils.randomBetween;

public class Agents {
    private static final int itemsAmount = 40;
    private static final int buyPrice = 10;
    private static final int sellPrice = 5;

    static LinkedList<Agent> agents = new LinkedList<>();
    private static Map<ItemName, Stock> startItems = new HashMap<>();
    private static String[] familyColor = {"red", "green", "blue", "purple", "black", "white", "pink", "olive", "gold", "silver", "aqua"};

    private static void startItems() {
        for (ItemName name : ItemName.values()) {
            startItems.put(name, new Stock(name));
            startItems.get(name).buyPrice = buyPrice;
            startItems.get(name).sellPrice = sellPrice;
            startItems.get(name).amount = itemsAmount;
            if (name == ItemName.GOLD) startItems.get(name).amount = 0;
        }
    }

    static void createAgents(int numberOfAgent, int numberOfMonthPerYear) {
        startItems();
        int colorIterator = 0;
        while (numberOfAgent > 0) {
            HashMap<BuildingName, Integer> newBuildings = new HashMap<>();
            for (int i = 0; i < 2; i++) {
                int randomIndex = randomBetween(0, BuildingsListForDraw.allBuildings.size() - 1);
                BuildingName newBuilding = BuildingsListForDraw.allBuildings.get(randomIndex);
                if (newBuildings.containsKey(newBuilding)) {
                    newBuildings.put(newBuilding, newBuildings.get(newBuilding) + 1);
                } else {
                    newBuildings.put(newBuilding, 1);
                }
            }

            agents.add(new Agent(
                    familyColor[colorIterator],
                    100,
                    newBuildings,
                    randomBetween(1, numberOfMonthPerYear),
                    startItems)
            );

            colorIterator = (colorIterator + 1) % familyColor.length;
            numberOfAgent--;
        }

        agents.forEach(Environment::takePlace);
    }

}
