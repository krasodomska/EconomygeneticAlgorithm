import java.util.ArrayList;
import java.util.LinkedList;

public class ExistingWorld {
    static int numberOfMonthPerYear = 12;
    static int currentMonth = 1;
    static Buildings buildings = new Buildings(numberOfMonthPerYear);
    static BuildingsListForDraw buildingsListForDraw = new BuildingsListForDraw();


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

    public static void main(String[] args) {
        while(true) {
            newMonth();
        }
    }
}
