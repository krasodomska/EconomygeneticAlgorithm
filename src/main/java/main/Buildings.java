package main;

import coreElements.BuildingName;
import coreElements.ItemName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Buildings {
    private static ArrayList<Integer> allYearProduction = new ArrayList<Integer>();
    private static ArrayList<Integer> twicePerYearProduction = new ArrayList<Integer>();
    static Map<BuildingName, Building> buildings = new HashMap<>();


    Buildings(int numberOfMonthPerYear) {
        createMonthList(numberOfMonthPerYear);

        buildings.put(BuildingName.BASKET,
                new Building(
                        BuildingName.BASKET,
                        ItemName.FOOD,
                        5,
                        0,
                        allYearProduction
                )
        );
        buildings.put(BuildingName.COW,
                new Building(
                        BuildingName.COW,
                        ItemName.FOOD,
                        30,
                        -10,
                        allYearProduction,
                        true
                )
        );
        buildings.put(BuildingName.FIELD,
                new Building(
                        BuildingName.FIELD,
                        ItemName.FOOD,
                        150,
                        -10,
                        twicePerYearProduction,
                        true
                )
        );
        buildings.put(BuildingName.ORCHAND,
                new Building(
                        BuildingName.ORCHAND.toString(),
                        ItemName.FOOD,
                        20,
                        -10,
                        allYearProduction,
                        12,
                        false
                )
        );

        buildings.put(BuildingName.AXE,
                new Building(
                        BuildingName.AXE,
                        ItemName.LUMBER,
                        5,
                        0,
                        allYearProduction
                )
        );
        buildings.put(BuildingName.BRICK_MANUFACTUR,
                new Building(
                        BuildingName.BRICK_MANUFACTUR,
                        ItemName.LUMBER,
                        30,
                        -10,
                        allYearProduction,
                        true
                )
        );
        buildings.put(BuildingName.QUARRY,
                new Building(
                        BuildingName.QUARRY,
                        ItemName.LUMBER,
                        150,
                        -10,
                        twicePerYearProduction,
                        true
                )
        );

        buildings.put(BuildingName.CRAFTING_SPOT,
                new Building(
                        BuildingName.CRAFTING_SPOT,
                        ItemName.TOOL,
                        5,
                        0,
                        allYearProduction
                )
        );
        buildings.put(BuildingName.TOOL_MANUFACTUR,
                new Building(
                        BuildingName.TOOL_MANUFACTUR,
                        ItemName.TOOL,
                        30,
                        -10,
                        allYearProduction,
                        true
                )
        );
        buildings.put(BuildingName.TOOL_JOURNEYMAN,
                new Building(
                        BuildingName.TOOL_JOURNEYMAN,
                        ItemName.TOOL,
                        150,
                        -10,
                        twicePerYearProduction,
                        true
                )
        );

        buildings.put(BuildingName.LOOM,
                new Building(
                        BuildingName.LOOM,
                        ItemName.CLOTHES,
                        5,
                        0,
                        allYearProduction
                )
        );
        buildings.put(BuildingName.CLOTHES_MANUFACTUR,
                new Building(
                        BuildingName.CLOTHES_MANUFACTUR,
                        ItemName.CLOTHES,
                        30,
                        -10,
                        allYearProduction,
                        true
                )
        );
        buildings.put(BuildingName.CLOTHES_JOURNEYMAN,
                new Building(
                        BuildingName.CLOTHES_JOURNEYMAN,
                        ItemName.CLOTHES,
                        150,
                        -10,
                        twicePerYearProduction,
                        true
                )
        );

        buildings.put(BuildingName.GOLD_STREAM,
                new Building(
                        BuildingName.GOLD_STREAM,
                        ItemName.GOLD,
                        10,
                        0,
                        allYearProduction
                )
        );
        buildings.put(BuildingName.GOLD_MINE,
                new Building(
                        BuildingName.GOLD_MINE,
                        ItemName.GOLD,
                        30,
                        -10,
                        allYearProduction,
                        true
                )
        );
    }

    /**
     * create list of all months per year, and list of middle month in year and last month
     *
     * @param numberOfMonthPerYear - how mony month have year
     */
    void createMonthList(int numberOfMonthPerYear) {
        for (int i = 1; i <= numberOfMonthPerYear; i++) {
            allYearProduction.add(i);
        }
        int halfYear = numberOfMonthPerYear / 2;
        twicePerYearProduction.add(halfYear);
        twicePerYearProduction.add(numberOfMonthPerYear);
    }

}
