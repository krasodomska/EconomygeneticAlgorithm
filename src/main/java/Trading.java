import java.util.LinkedList;

public class Trading {

    static LinkedList<Agent> salesMen = new LinkedList();
    static LinkedList<Agent> buyers = new LinkedList<>();

    /**
     * full trading per one month in simulation
     * @param tradingShifts
     * @param agents
     */
    public static void trade(int tradingShifts, LinkedList<Agent> agents) {
        //System.out.println();

        //
        // System.out.println();
        for (ItemName item : ItemName.values()) {
            int shifts = tradingShifts;
            while (shifts > 0) {
                divideAgents(item, agents);
                while (buyers.size() > 0 && salesMen.size() > 0) {
                    Agent buyer = buyer();
                    Agent salesMan = salesMan();
                    if (item == ItemName.FOOD) foodPanic(salesMan);
                    oneMeeting(salesMan, buyer, item);
                    if (salesMan.items.get(item).amount < 3)
                        salesMen.remove(salesMan);
                    buyers.remove(buyer);
                    if(item == ItemName.GOLD) break;
                    //debug
                    //System.out.println(item);
                }
                shifts--;
            }

        }
    }

    /**
     * divine agents too buyer and seller
     * @param item
     * @param agents
     */
    static void divideAgents(ItemName item, LinkedList<Agent> agents) {
        buyers.clear();
        salesMen.clear();

        agents.forEach(agent -> {
            if (agent.items.get(item).amount < 30) {
                if (agent.gold > 0)
                    buyers.add(agent);
            } else if (agent.items.get(item).amount > 50) {
                salesMen.add(agent);
            } else if (Math.random() > 0.5) {
                if (agent.gold > 0)
                    buyers.add(agent);
            } else {
                salesMen.add(agent);
            }
        });
    }

    /**
     * meet random salesMan and buyer - check if trasation is possible make trasation
     * @param salesMan
     * @param buyer
     * @param item
     */
    static void oneMeeting(Agent salesMan, Agent buyer, ItemName item) {
        int sellPrice = salesMan.items.get(item).sellPrice;
        int buyPrice;
        if (item == ItemName.FOOD) buyPrice = Math.min(foodPrice(buyer, salesMan), buyer.gold);
        else buyPrice = Math.min(buyer.items.get(item).buyPrice, buyer.gold);

        if (sellPrice > buyPrice) {


            if (sellPrice > 1)
                salesMan.items.get(item).sellPrice--;
            if (buyPrice < buyer.gold)
                buyer.items.get(item).buyPrice++;
        } else {

            //debug
//            System.out.println();
//            System.out.println("trade");
//            System.out.println("SellMan: " + salesMan);
//            System.out.println("Buyer: " + buyer);
//            System.out.println(item + ": ");
//            System.out.println("sellPriceBefore: " + sellPrice);
//            System.out.println("BuyPriceBerofe: " + buyPrice);


            transaction(salesMan, buyer, item, sellPrice, buyPrice);
            if (sellPrice == buyPrice) {
                salesMan.items.get(item).sellPrice++;
                if (buyPrice > 1)
                    buyer.items.get(item).buyPrice--;
            }

            //debug
//            System.out.println("sellPriceAfter: " + salesMan.items.get(item).sellPrice);
//            System.out.println("buyPriceAfter: " + buyer.items.get(item).buyPrice);
//            System.out.println("SellMan: " + salesMan);
//            System.out.println("Buyer: " + buyer);
//            System.out.println();
        }

    }

    /**
     * transaction between salesMan and byer
     * @param salesMan
     * @param buyer
     * @param item
     * @param sellPrice
     * @param buyPrice
     */
    static void transaction(Agent salesMan, Agent buyer, ItemName item, int sellPrice, int buyPrice) {
        int itemPrice = (buyPrice - sellPrice) / 2 + sellPrice;
        salesMan.items.get(item).sellPrice = itemPrice;
        salesMan.items.get(item).amount--;
        salesMan.gold += itemPrice;

        buyer.items.get(item).buyPrice = itemPrice;
        buyer.items.get(item).amount++;
        buyer.gold -= itemPrice;
    }

    /**
     * create random salesMan
     * @return
     */
    static Agent salesMan() {
        return salesMen.get((int) Math.random() * (salesMen.size() - 1));
    }

    /**
     * create random buyer
     * @return
     */
    static Agent buyer() {
        return buyers.get((int) Math.random() * (salesMen.size() - 1));
    }

    /**
     *
     * @param buyer
     * @param salesMan
     * @return how much is for food after mofification
     */
    static int foodPrice(Agent buyer, Agent salesMan) {
        int buyPrice = buyer.items.get(ItemName.FOOD).buyPrice;
        int foodAmount = buyer.items.get(ItemName.FOOD).amount;
        if (foodAmount > 0)
            return buyPrice;
        else if (foodAmount == 0)
            return (int) (buyPrice * 1.1);
        else if (foodAmount == -1)
            return (int) (buyPrice * 1.25);
        else if (foodAmount == -2)
            return (int) (buyPrice * 1.5);
        else return salesMan.items.get(ItemName.FOOD).sellPrice;

    }

    /**
     * when there is problem with food agent have one additional trade
     * @param salesMan
     */
    static void foodPanic(Agent salesMan) {
        for (Agent agent : buyers) {
            if (agent.items.get(ItemName.FOOD).amount == -3) {
                oneMeeting(salesMan, agent, ItemName.FOOD);
            }
        }
    }
}
