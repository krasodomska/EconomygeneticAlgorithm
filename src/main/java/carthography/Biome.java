package carthography;

import coreElements.ItemName;

import java.util.List;

public class Biome {
    private String biomeName;
    private List<ItemName> doubleEfficiency;
    private List<ItemName> halfEfficiency;
    private List<ItemName> noEfficiency;

    public Biome(String biomeName, List<ItemName> doubleEfficiency, List<ItemName> halfEfficiency, List<ItemName> noEfficiency) {
        this.biomeName = biomeName;
        this.doubleEfficiency = doubleEfficiency;
        this.halfEfficiency = halfEfficiency;
        this.noEfficiency = noEfficiency;
    }

    public Double efficiencyFor(ItemName itemName){
        if (doubleEfficiency.contains(itemName)) return 2.0;
        if (halfEfficiency.contains(itemName)) return 0.5;
        if (noEfficiency.contains(itemName)) return 0.0;
        return 1.0;
    }

    public String getBiomeName() {
        return biomeName;
    }

    public void setBiomeName(String biomeName) {
        this.biomeName = biomeName;
    }

    public List<ItemName> getDoubleEfficiency() {
        return doubleEfficiency;
    }

    public void setDoubleEfficiency(List<ItemName> doubleEfficiency) {
        this.doubleEfficiency = doubleEfficiency;
    }

    public List<ItemName> getHalfEfficiency() {
        return halfEfficiency;
    }

    public void setHalfEfficiency(List<ItemName> halfEfficiency) {
        this.halfEfficiency = halfEfficiency;
    }

    public List<ItemName> getNoEfficiency() {
        return noEfficiency;
    }

    public void setNoEfficiency(List<ItemName> noEfficiency) {
        this.noEfficiency = noEfficiency;
    }
}
