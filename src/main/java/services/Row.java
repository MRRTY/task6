package services;

import exceptions.NoSuchColumnException;

import java.io.Serializable;
import java.util.List;

public class Row implements Serializable {
    private List<Cell> cells;

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
    public Cell getCellByColumn(Column column){
        if(cells.stream().anyMatch(cell -> cell.getColumn().equals(column))){
            return cells.stream().findAny().filter(cell -> cell.getColumn().equals(column)).get();
        }else{
            throw  new NoSuchColumnException();
        }
    }

    @Override
    public String toString() {
        return cells.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        return cells != null ? cells.equals(row.cells) : row.cells == null;
    }

    @Override
    public int hashCode() {
        return cells != null ? cells.hashCode() : 0;
    }
}
