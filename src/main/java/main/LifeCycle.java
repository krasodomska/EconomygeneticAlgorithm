package main;

import java.util.LinkedList;

public class LifeCycle {

    static void production() {
        Agents.agents.forEach(agent -> agent.production(Environment.currentMonth));
    }

    static void consumption() {
        Agents.agents.forEach(agent -> agent.consumption(Environment.currentMonth));

    }

    static void reproduction() {
        LinkedList<Agent> newAgents = new LinkedList<>();
        Agents.agents.forEach(agent -> { if (agent.newAgent) newAgents.add(agent); });

        for (Agent newAgent : newAgents) {
            Agents.agents.get(Agents.agents.indexOf(newAgent)).reproduction();
        }
    }

    static void dying() {
        LinkedList<Agent> deadAgent = new LinkedList<>();

        Agents.agents.forEach(agent -> {
            if (agent.starvationDeath()) deadAgent.add(agent);
            else if (agent.randomDeath(0.005)) deadAgent.add(agent);
        });

        deadAgent.forEach(zombie -> {
//            System.out.println();
            Environment.regainPlace(zombie);
//            System.out.println("Zombie " + zombie);
//            System.out.println();
            Agents.agents.remove(zombie);

        });
    }

    static void aging() {
        Agents.agents.forEach(agent -> agent.age++);
    }

    static void trading() {
        Trading.trade(30, Agents.agents);
    }
}
