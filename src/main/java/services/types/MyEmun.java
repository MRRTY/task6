package services.types;

import java.util.HashSet;
import java.util.Set;

public class MyEmun {
    Set<String> values;

    public MyEmun(String values) {
        this.values = new HashSet<>();
        values = values.substring(1,values.length()-1);
        for (String s : values.split(",")){
            this.values.add(s);
        }
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyEmun myEmun = (MyEmun) o;

        return values != null ? values.equals(myEmun.values) : myEmun.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MyEmun{" +
                "values=" + values +
                '}';
    }
}
