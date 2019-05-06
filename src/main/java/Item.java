public class Item {
    ItemName name;
    int sellPrice;
    int buyPrice;
    int amount;

    Item(ItemName name, int startPrice, int startAmount){
        this.name = name;
        sellPrice= startPrice;
        buyPrice = startPrice;
        amount = startAmount;
    }
}
