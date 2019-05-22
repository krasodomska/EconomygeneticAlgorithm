package main;

import java.util.Arrays;
import java.util.List;

public class Environment {
    static int numberOfMonthPerYear = 12;
    static int currentMonth = 1;
    static int placeInTheWorld = 100;
    static List<Integer> monthWithClothesDestruction = Arrays.asList(1, 4, 7, 10);
    static List<Integer> monthWithHouseDestruction = Arrays.asList(2, 5, 11);

    /**
     * simulate time running - position next month
     */
    static void newMonth() {
        if (currentMonth < numberOfMonthPerYear) {
            currentMonth++;
            return;
        }
        currentMonth = 1;
    }

    static boolean noPlaceInWorld() {
        if (placeInTheWorld < 1) return true;
        return false;
    }

    static void takePlace(Agent agent) {
        placeInTheWorld -= agent.takingPlace;
    }

    static void regainPlace(Agent agent) {
        placeInTheWorld += agent.takingPlace;
    }


}
