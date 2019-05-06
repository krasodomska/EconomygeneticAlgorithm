import java.util.ArrayList;
import java.util.HashMap;

public class BuildingsListForDraw {
    ArrayList<BuildingName> allBuildings = new ArrayList<BuildingName>();
    HashMap<ItemName, ArrayList<BuildingName>> buildingsProductSameItem = new HashMap<>();
    HashMap<Integer, ArrayList<BuildingName>> buildingsProductSameAmoutOfItem = new HashMap<>();

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

            if (buildingsProductSameAmoutOfItem.containsKey(building.scaledProfit)) {
                buildingsProductSameAmoutOfItem.get(building.scaledProfit).add(building.name);
            } else {
                ArrayList<BuildingName> name = new ArrayList<>();
                name.add(building.name);
                buildingsProductSameAmoutOfItem.put(building.scaledProfit, name);
            }

        }
    }
}

