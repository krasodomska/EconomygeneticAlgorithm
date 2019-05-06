import java.util.HashMap;
import java.util.LinkedList;

public class ExistingWorld {
    static int numberOfMonthPerYear = 12;
    static int currentMonth = 1;
    static Buildings buildings = new Buildings(numberOfMonthPerYear);
    static BuildingsListForDraw buildingsListForDraw = new BuildingsListForDraw();
    static LinkedList<Agent> agents = new LinkedList<>();


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

    static void createAgents(int numberOfAgent) {
        while (numberOfAgent > 0) {
            HashMap<BuildingName, Integer> newBuildings = new HashMap<>();
            for (int i = 0; i < 3; i++) {
                int randomIndex = (int) (Math.random() * buildingsListForDraw.allBuildings.size() - 1);
                BuildingName newBuilding = buildingsListForDraw.allBuildings.get(randomIndex);
                if (newBuildings.containsKey(newBuilding)) {
                    newBuildings.put(newBuilding, newBuildings.get(newBuilding) + 1);
                } else {
                    newBuildings.put(newBuilding, 1);
                }
            }
            agents.add(new Agent(10,2, 1000, newBuildings));
            numberOfAgent--;
        }
    }

    public static void main(String[] args) {
        createAgents(5);
        int a = 24;
        agents.forEach(agent -> System.out.println(agent));
        System.out.println();
        while (a > 0) {
            newMonth();
            //System.out.print(agents);
            agents.forEach(agent -> agent.production(currentMonth));
          //agents.forEach(agent -> agent.consumption(currentMonth));
            agents.forEach(agent -> System.out.println(agent));
            System.out.println();
            a--;

        }
    }
}
