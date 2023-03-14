package Domain;

public class Instrument extends FlightControl{

    private String measurementType;

    public Instrument(String code, boolean maintenance, String measurementType) {
        super(code, maintenance);
        this.measurementType = measurementType;
    }


    @Override
    public double getPrice() {
        double price = 0;
        if(measurementType == "altitude" || measurementType == "direction") price = 50;
        if(measurementType == "engine_state") price = 500;
        return price;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "measurementType='" + measurementType + '\'' +
                ", code='" + code + '\'' +
                ", maintenance=" + maintenance +
                '}';
    }
}
