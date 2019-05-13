public class Environment {
    static int numberOfMonthPerYear = 12;
    static int currentMonth = 1;
    static int placeInTheWorld = 100;

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

    static boolean noPlaceInWorld(){
        if (placeInTheWorld < 1) return true;
        return false;
    }

    static void takePlace(Agent agent){
        placeInTheWorld -=agent.takingPlace;
    }

    static void regainPlace(Agent agent){
        placeInTheWorld +=agent.takingPlace;
    }

}
