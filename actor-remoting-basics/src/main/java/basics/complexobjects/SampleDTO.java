package basics.complexobjects;

import java.io.Serializable;
import java.util.List;

public class SampleDTO implements Serializable {

    String name = "blabla";
    int value = 13;
    double dvalue = 13.13;
    Object array[] = { 1, 2, 3, "Vier", "Fünf"};
    List<SampleDTOEntry> list = List.of(
        new SampleDTOEntry( "a", "b"),  new SampleDTOEntry( "c", "d")
    );

    public List<SampleDTOEntry> getList() {
        return list;
    }

    public void setList(List<SampleDTOEntry> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getDvalue() {
        return dvalue;
    }

    public void setDvalue(double dvalue) {
        this.dvalue = dvalue;
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }
}
