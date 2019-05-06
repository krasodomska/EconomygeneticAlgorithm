import java.util.ArrayList;
import java.util.HashMap;

public class Buildings {
    static ArrayList<Integer> allYearProduction = new ArrayList<Integer>();
    static ArrayList<Integer> twicePerYearProduction = new ArrayList<Integer>();
    static HashMap<BuildingName, Building> buildings = new HashMap();


    /**
     * create list of all months per year, and list of middle month in year and last month
     * @param numberOfMonthPerYear - how mony month have year
     */
    void createMonthList(int numberOfMonthPerYear){
        for(int i = 1; i <= numberOfMonthPerYear; i++){
            allYearProduction.add(i);
        }
        int halfYear = numberOfMonthPerYear/2;
        twicePerYearProduction.add(halfYear);
        twicePerYearProduction.add(numberOfMonthPerYear);
    }

    Buildings(int numberOfMonthPerYear){
        createMonthList(numberOfMonthPerYear);

        buildings.put(BuildingName.BASKET,
                new Building(BuildingName.BASKET, 1,0, ItemName.FOOD, allYearProduction));
        buildings.put(BuildingName.COW,
                new Building(BuildingName.COW, 3, -1, ItemName.FOOD, allYearProduction));
        buildings.put(BuildingName.FIELD,
                new Building(BuildingName.FIELD, 15, -1, ItemName.FOOD, allYearProduction));
        buildings.put(BuildingName.ORCHAND,
                new Building(BuildingName.ORCHAND, 2, -1, ItemName.FOOD, allYearProduction));

        buildings.put(BuildingName.AXE,
                new Building(BuildingName.AXE, 1,0, ItemName.LUMBER, allYearProduction));
        buildings.put(BuildingName.BRICK_MANUFACTUR,
                new Building(BuildingName.BRICK_MANUFACTUR, 3, -1, ItemName.LUMBER, allYearProduction));
        buildings.put(BuildingName.QUARRY,
                new Building(BuildingName.QUARRY, 15, -1, ItemName.LUMBER, allYearProduction));

        buildings.put(BuildingName.CRAFTING_SPOT,
                new Building(BuildingName.CRAFTING_SPOT, 1,0, ItemName.TOOL, allYearProduction));
        buildings.put(BuildingName.TOOL_MANUFACTUR,
                new Building(BuildingName.TOOL_MANUFACTUR, 3, -1, ItemName.TOOL, allYearProduction));
        buildings.put(BuildingName.TOOL_JOURNEYMAN,
                new Building(BuildingName.TOOL_JOURNEYMAN, 15, -1, ItemName.TOOL, allYearProduction));

        buildings.put(BuildingName.LOOM,
                new Building(BuildingName.LOOM, 1,0, ItemName.CLOTHES, allYearProduction));
        buildings.put(BuildingName.CLOTHES_MANUFACTUR,
                new Building(BuildingName.CLOTHES_MANUFACTUR, 3, -1, ItemName.CLOTHES, allYearProduction));
        buildings.put(BuildingName.CLOTHES_JOURNEYMAN,
                new Building(BuildingName.CLOTHES_JOURNEYMAN, 15, -1, ItemName.CLOTHES, allYearProduction));
    }

}
