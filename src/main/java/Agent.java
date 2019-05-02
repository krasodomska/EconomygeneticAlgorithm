import java.util.HashMap;

public class Agent {
    String familyColor;
    int food;
    int clothes;
    int tool;
    int lumber;
    int gold;

    boolean damageHaus;
    //when game check if agent have lumber - 0 -> produce 25% less, 1 -> lumber is spent, 2 -> spent lumber and has son
    int chackingLumbersMonth;

    int foodPrice;
    int clothesPrice;
    int toolPrice;
    int lumberPrice;

    static int numberOfBuyPerDay;

    //Tour currentTour; //??

    HashMap<BuildingName, Integer> buildings = new HashMap<BuildingName, Integer>();
}
