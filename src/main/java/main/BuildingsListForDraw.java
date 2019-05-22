package main;

import coreElements.BuildingName;
import coreElements.ItemName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.Buildings.buildings;

public class BuildingsListForDraw {
    static ArrayList<BuildingName> allBuildings = new ArrayList<BuildingName>();
    static HashMap<ItemName, ArrayList<BuildingName>> buildingsProductSameItem = new HashMap<>();
    static HashMap<Integer, ArrayList<BuildingName>> buildingsProductSameAmountOfItem = new HashMap<>();
    static HashMap<ItemName, BuildingName> baseBuilding = new HashMap<>();

    /**
     * create list specyfic type of agentBuildings - for randomise first list of agents
     * then when simulation work this list are neccecery for genetic algorithm
     */
    BuildingsListForDraw() {
        for (Building building : Buildings.buildings.values()) {
            allBuildings.add(BuildingName.valueOf(building.name));
            if (buildingsProductSameItem.containsKey(building.itemProduced)) {
                buildingsProductSameItem.get(building.itemProduced).add(BuildingName.valueOf(building.name));
            } else {
                ArrayList<BuildingName> name = new ArrayList<>();
                name.add(BuildingName.valueOf(building.name));
                buildingsProductSameItem.put(building.itemProduced, name);
            }

            if (buildingsProductSameAmountOfItem.containsKey(building.scaledProfit)) {
                buildingsProductSameAmountOfItem.get(building.scaledProfit).add(BuildingName.valueOf(building.name));
            } else {
                ArrayList<BuildingName> names = new ArrayList<>();
                names.add(BuildingName.valueOf(building.name));
                buildingsProductSameAmountOfItem.put(building.scaledProfit, names);
            }

            if (building.scaledProfit <= 10) {
                baseBuilding.put(building.itemProduced, BuildingName.valueOf(building.name));
            }

        }
    }

    public static List<BuildingName> getBuildingsWithEqualProfit(BuildingName buildingName) {
        return buildingsProductSameAmountOfItem.get(buildings.get(buildingName).scaledProfit);
    }

    public static List<BuildingName> getBuildingsWithSameItem(BuildingName buildingName) {
        return buildingsProductSameItem.get(buildings.get(buildingName).itemProduced);
    }
}

