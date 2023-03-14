package Domain;

public abstract class FlightControl {

    protected String code;
    protected boolean maintenance;

    public FlightControl(String code, boolean maintenance) {
        this.code = code;
        this.maintenance = maintenance;
    }

    public abstract double getPrice();
    public abstract String toString();

    public boolean isMaintenance() {
        return maintenance;
    }
}
