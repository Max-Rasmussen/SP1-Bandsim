public class Venue {
    private String name;
    private int capacity;
    private int payoutAmount;
    private int lvlThreshHold;


    Venue(String name, int capacity, int payoutAmount, int lvlThreshHold) {
        this.name = name;
        this.capacity = capacity;
        this.payoutAmount = payoutAmount;
        this.lvlThreshHold = lvlThreshHold;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public int getPayoutAmount() {
        return payoutAmount;
    }

    public int getLvlThreshHold() {
        return lvlThreshHold;
    }
}