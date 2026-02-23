public class Event{
    private String type;
    private String description;
    private int fanImpact;
    private double moneyImpact;


    public Event(String type, String description, int fanImpact, int moneyImpact) {
        this.type = type;
        this.description = description;
        this.fanImpact = fanImpact;
        this.moneyImpact = moneyImpact;
    }


    public String toString() {
        if (fanImpact < 0 && moneyImpact < 0) {
            return type + " - " + description + " fans -" + fanImpact + " money -" + moneyImpact;
        } else if (fanImpact > 0 && moneyImpact < 0) {
            return type + " - " + description + " fans +" + fanImpact + " money -" + moneyImpact;
        } else if (fanImpact < 0 && moneyImpact > 0) {
            return type + " - " + description + " fans -" + fanImpact + " money +" + moneyImpact;
        } else if (fanImpact > 0 && moneyImpact > 0) {
            return type + " - " + description + " fans +" + fanImpact + " money +" + moneyImpact;
        } else {
            System.out.println("Some error happened with event toString, unknown values or so");
        }

        return "Some error happened with event toString, unknown values or so on and so forth";
    }

    public String getType() {
        return type;
    }

    public double getMoneyImpact() {
        return moneyImpact;
    }

    public int getFanImpact() {
        return fanImpact;
    }

    public String getDescription() {
        return description;
    }
}