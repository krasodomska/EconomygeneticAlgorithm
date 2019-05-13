import java.util.HashMap;
import java.util.LinkedList;

public class Agents {
    static int itemsAmount = 40;
    static int buyPrice = 10;
    static int sellPrice = 5;
    static LinkedList<Agent> agents = new LinkedList<>();
    static HashMap<ItemName, Item> startItems(){
        HashMap<ItemName, Item> startItems = new HashMap<>();
        for (ItemName name : ItemName.values()) {
            startItems.put(name, new Item(name));
            startItems.get(name).buyPrice = buyPrice;
            startItems.get(name).sellPrice = sellPrice;
            startItems.get(name).amount = itemsAmount;
            if(name == ItemName.GOLD) startItems.get(name).amount = 0;

        }

        return startItems;
    }

    static void createAgents(int numberOfAgent, int numberOfMonthPerYear) {
        String[] familyColor = {"red", "green", "blue", "purple", "black", "white", "pink", "olive", "gold", "silver", "aqua"};
        int colorIterator = 0;
        while (numberOfAgent > 0) {
            HashMap<BuildingName, Integer> newBuildings = new HashMap<>();
            for (int i = 0; i < 2; i++) {
                int randomIndex = (int) (Math.random() * BuildingsListForDraw.allBuildings.size() - 1);
                BuildingName newBuilding = BuildingsListForDraw.allBuildings.get(randomIndex);
                if (newBuildings.containsKey(newBuilding)) {
                    newBuildings.put(newBuilding, newBuildings.get(newBuilding) + 1);
                } else {
                    newBuildings.put(newBuilding, 1);
                }
            }

            agents.add(new Agent(familyColor[colorIterator], 100, newBuildings, (int) Math.random() * (numberOfMonthPerYear-1)+1,startItems()));

            colorIterator++;
            numberOfAgent--;

        }

        agents.forEach(agent -> Environment.takePlace(agent));
    }

}
