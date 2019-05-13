public class Statistic {

    static double itemPerPerson(ItemName item){
        double itemAmount = 0;
        for (Agent agent : Agents.agents) {
            itemAmount += agent.items.get(item).amount;
        }
        return itemAmount/Agents.agents.size();
    }


}
