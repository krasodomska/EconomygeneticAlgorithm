public class Statistic {
    /**
     *
     * @param item - which item is tested
     * @return - how many items per person is in population
     */
    static double itemPerPerson(ItemName item){
        double itemAmount = 0;
        for (Agent agent : Agents.agents) {
            itemAmount += agent.items.get(item).amount;
        }
        return itemAmount/Agents.agents.size();
    }

    /**
     * show statistics about item in this population
     */
    public static void printItemPerPerson(){
        for(ItemName item: ItemName.values()){
            System.out.println(" " + item+": " + itemPerPerson(item));
        }
    }


}
