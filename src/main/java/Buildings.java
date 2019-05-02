import java.util.ArrayList;

public class Buildings {
    static ArrayList<Integer> allYearProduction = new ArrayList<Integer>();
    static ArrayList<Integer> twicePerYearProduction = new ArrayList<Integer>();
    static ArrayList<Building> buildings = new ArrayList<Building>();


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

    void createBuildinsList(){
        buildings.add(new Building(BuildingName.BASKET, 1,0, ItemName.FOOD, allYearProduction));
    }

    /**
     *
     * @param name - name of the searching building
     * @return - where is searching building on the list
     */

    public static int getListPosition(BuildingName name){
        for (Building building : buildings) {
            if (building.name == name) {
                return buildings.indexOf(building);
            }
        }
        return -1;
    }
}
