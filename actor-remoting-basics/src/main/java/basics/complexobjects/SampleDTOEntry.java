package basics.complexobjects;

import java.io.Serializable;

class SampleDTOEntry implements Serializable {

    String x = "x";
    String y = "y";

    public SampleDTOEntry() {
    }

    public SampleDTOEntry(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
