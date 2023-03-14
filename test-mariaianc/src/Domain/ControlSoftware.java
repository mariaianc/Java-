package Domain;

public class ControlSoftware extends FlightControl{

    private int version;

    public ControlSoftware(String code, boolean maintenance, int version) {
        super(code, maintenance);
        this.version = version;
    }


    @Override
    public double getPrice() {
        double price = 0;
        if(this.version<10) price = 100;
        if(this.version>10) price = 200;
        return price;
    }

    @Override
    public String toString() {
        return "ControlSoftware{" +
                "version=" + version +
                ", code='" + code + '\'' +
                ", maintenance=" + maintenance +
                '}';
    }


}
