package services.types;

import java.util.Arrays;

public class StringArray {
    private String[] values;

    public StringArray(String values) {
        values.substring(1,values.length()-1);
        this.values = values.split(",");
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringArray that = (StringArray) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        return "StringArray{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
