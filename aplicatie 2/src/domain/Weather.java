package domain;

public class Weather {

    private int id;
    private int start;
    private int end;
    private int temperature;
    private int prec_prob;
    private String description;

    public Weather(int id, int start, int end, int temperature, int prec_prob, String description) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.temperature = temperature;
        this.prec_prob = prec_prob;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "start=" + start +
                ", end=" + end +
                ", temperature=" + temperature +
                ", prec_prob=" + prec_prob +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPrec_prob() {
        return prec_prob;
    }

    public void setPrec_prob(int prec_prob) {
        this.prec_prob = prec_prob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
