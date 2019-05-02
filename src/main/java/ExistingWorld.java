import java.util.LinkedList;

public class ExistingWorld {
    static int numberOfMonthPerYear = 12;
    static int currentMonth = 1;
    LinkedList<Agent> agents = new LinkedList<Agent>();


    static void createAgent(int numberOfAgent) {

    }

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
        newMonth();
    }
}
