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
        String[] familyColor = {"red", "green", "blue", "purple", "black", "white", "pink"};
        int colorIterator = 0;
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

            agents.add(new Agent(familyColor[colorIterator], 10, 2, 1000, newBuildings, (int) Math.random() * (numberOfMonthPerYear-1)+1));
            colorIterator++;
            numberOfAgent--;
        }
    }

    public static void main(String[] args) {
        createAgents(5);
        int testingTimeInMonth = 36;
        agents.forEach(agent -> System.out.println(agent));
        System.out.println();
        while (testingTimeInMonth > 0) {
            newMonth();
            LinkedList<Agent> deadAgent = new LinkedList<>();
            LinkedList<Agent> newAgents = new LinkedList<>();
            agents.forEach(agent -> {
                agent.production(currentMonth);

                //TO DO: MarketTime

                agent.consumption(currentMonth);

                if (agent.starvationDeath()) deadAgent.add(agent);
                if(agent.newAgent) newAgents.add(agent);
            });

            //death time
            for (Agent zombie : deadAgent) {
                System.out.println("Zombie "+ zombie);
                agents.remove(zombie);
            }
//            deadAgent.forEach(zombie -> {
//                System.out.println("Zombie "+ zombie);              ///why this not work ????
//                agents.remove(zombie)
//
//            });

            //born time
            for(Agent newAgent : newAgents){
                agents.get(agents.indexOf(newAgent)).reproduction();
            }


            agents.forEach(agent -> System.out.println(agent));
            System.out.println();
            testingTimeInMonth--;

        }
    }
}
