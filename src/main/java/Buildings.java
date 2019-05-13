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
                new Building(BuildingName.BASKET, 5,0, ItemName.FOOD, allYearProduction, false));
        buildings.put(BuildingName.COW,
                new Building(BuildingName.COW, 30, -10, ItemName.FOOD, allYearProduction, true));
        buildings.put(BuildingName.FIELD,
                new Building(BuildingName.FIELD, 150, -10, ItemName.FOOD, twicePerYearProduction, true));
        buildings.put(BuildingName.ORCHAND,
                new Building(BuildingName.ORCHAND, 20, -10, ItemName.FOOD, allYearProduction, false));

        buildings.put(BuildingName.AXE,
                new Building(BuildingName.AXE, 5,0, ItemName.LUMBER, allYearProduction, false));
        buildings.put(BuildingName.BRICK_MANUFACTUR,
                new Building(BuildingName.BRICK_MANUFACTUR, 30, -10, ItemName.LUMBER, allYearProduction,true));
        buildings.put(BuildingName.QUARRY,
                new Building(BuildingName.QUARRY, 150, -10, ItemName.LUMBER, twicePerYearProduction,true));

        buildings.put(BuildingName.CRAFTING_SPOT,
                new Building(BuildingName.CRAFTING_SPOT, 5,0, ItemName.TOOL, allYearProduction,false));
        buildings.put(BuildingName.TOOL_MANUFACTUR,
                new Building(BuildingName.TOOL_MANUFACTUR, 30, -10, ItemName.TOOL, allYearProduction,true));
        buildings.put(BuildingName.TOOL_JOURNEYMAN,
                new Building(BuildingName.TOOL_JOURNEYMAN, 150, -10, ItemName.TOOL, twicePerYearProduction,true));

        buildings.put(BuildingName.LOOM,
                new Building(BuildingName.LOOM, 5,0, ItemName.CLOTHES, allYearProduction,false));
        buildings.put(BuildingName.CLOTHES_MANUFACTUR,
                new Building(BuildingName.CLOTHES_MANUFACTUR, 30, -10, ItemName.CLOTHES, allYearProduction,true));
        buildings.put(BuildingName.CLOTHES_JOURNEYMAN,
                new Building(BuildingName.CLOTHES_JOURNEYMAN, 150, -10, ItemName.CLOTHES, twicePerYearProduction, true));

        buildings.put(BuildingName.GOLD_STREAM,
                new Building(BuildingName.GOLD_STREAM, 10,0, ItemName.GOLD, allYearProduction,false));
        buildings.put(BuildingName.GOLD_MINE,
                new Building(BuildingName.GOLD_MINE, 30, -10, ItemName.GOLD, allYearProduction,true));
    }

}
