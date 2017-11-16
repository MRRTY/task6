package services;

import java.io.Serializable;

public class Cell<T> implements Serializable{
    private Column column;
    private T value;

    public Cell(Column column, T value) {
        this.column = column;
        this.value = value;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  value.toString();
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell<?> cell = (Cell<?>) o;

        if (column != null ? !column.equals(cell.column) : cell.column != null) return false;
        return value != null ? value.equals(cell.value) : cell.value == null;
    }

    @Override
    public int hashCode() {
        int result = column != null ? column.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
