import java.util.ArrayList;
import java.util.HashMap;

public class BuildingsListForDraw {
    static ArrayList<BuildingName> allBuildings = new ArrayList<BuildingName>();
    static HashMap<ItemName, ArrayList<BuildingName>> buildingsProductSameItem = new HashMap<>();
    static HashMap<Integer, ArrayList<BuildingName>> buildingsProductSameAmountOfItem = new HashMap<>();
    static HashMap<ItemName, BuildingName> baseBuilding = new HashMap<>();

    /**
     * create list specyfic type of buildings - for randomise first list of agents
     * then when simulation work this list are neccecery for genetic algorithm
     */
    BuildingsListForDraw() {
        for (Building building : Buildings.buildings.values()) {
            allBuildings.add(building.name);
            if (buildingsProductSameItem.containsKey(building.itemProduced)) {
                buildingsProductSameItem.get(building.itemProduced).add(building.name);
            } else {
                ArrayList<BuildingName> name = new ArrayList<>();
                name.add(building.name);
                buildingsProductSameItem.put(building.itemProduced, name);
            }

            if (buildingsProductSameAmountOfItem.containsKey(building.scaledProfit)) {
                buildingsProductSameAmountOfItem.get(building.scaledProfit).add(building.name);
            } else {
                ArrayList<BuildingName> names = new ArrayList<>();
                names.add(building.name);
                buildingsProductSameAmountOfItem.put(building.scaledProfit, names);
            }

            if(building.scaledProfit <= 10){
                baseBuilding.put(building.itemProduced, building.name);
            }

        }
    }
}

