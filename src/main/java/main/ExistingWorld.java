package main;

public class ExistingWorld {
    static Buildings buildings = new Buildings(Environment.numberOfMonthPerYear);
    static BuildingsListForDraw buildingsListForDraw = new BuildingsListForDraw();
    static boolean noPlace = false;




    /**
     * there are same action that have to be done before simulation start running
     */
    public static void init() {
        Agents.createAgents(11, Environment.numberOfMonthPerYear);
        Agents.agents.forEach(agent -> System.out.println(agent));
        System.out.println();
    }

    public static void gameRun(int testingTimeInMonth) {
        int i = 0;
        while (testingTimeInMonth > 0) {
            Environment.newMonth();

            LifeCycle.production();
            LifeCycle.consumption();
            LifeCycle.trading();

            for(Agent agent: Agents.agents){
                if (agent.gold < 0)return;
            }
            LifeCycle.reproduction();
            LifeCycle.dieing();
            LifeCycle.ageing();
            testingTimeInMonth--;


            System.out.println(i + " number of agents: "+ Agents.agents.size());
            i++;

            //debug
//            main.Agents.agents.forEach(agent -> System.out.println(agent));
//            System.out.println();

        }

    }


    public static void main(String[] args) {
        init();
        int testingTimeInMonth = 300;
        gameRun(testingTimeInMonth);
        Agents.agents.forEach(agent -> System.out.println(agent));
        System.out.println();
        System.out.println();
        System.out.println("Place: " + Environment.placeInTheWorld);
        System.out.println("number of agents: " + Agents.agents.size());

    }

}
