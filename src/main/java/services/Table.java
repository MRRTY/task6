package services;

import exceptions.*;
import services.types.MyEmun;
import services.types.StringArray;
import services.types.StringInvl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class Table implements Serializable {
    private String name;
    private List<Column> columns;
    private List<Row> rows;

    private Table(String tableName, List<Column> columns) {
        this.name = tableName;
        this.columns = columns;
        rows = new ArrayList<Row>();
    }


    public void addRow(List<String> objects){
        Row row = new Row();
        row.setCells(createCells(objects));
        rows.add(row);

    }
    private List<Cell> createCells(List<String> objects){
        List<Cell> cells = new ArrayList<>();
        for(int i =0; i<objects.size();i++){

            switch (columns.get(i).getType()){
                case CHAR:
                    cells.add(new Cell<Character>(columns.get(i),objects.get(i).charAt(0)));
                    break;
                case INTEGER:
                    cells.add(new Cell<Integer>(columns.get(i),Integer.parseInt(objects.get(i))));
                    break;
                case REAL:
                    cells.add(new Cell<Double>(columns.get(i),Double.parseDouble(objects.get(i))));
                    break;
                case STRING_ARRAY:
                    cells.add(new Cell<StringArray>(columns.get(i),new StringArray(objects.get(i))));
                    break;
                case STRING_INVL:
                    cells.add(new Cell<StringInvl>(columns.get(i),new StringInvl(objects.get(i))));
                    break;
                case MY_ENUM:
                    cells.add(new Cell<MyEmun>(columns.get(i),new MyEmun(objects.get(i))));
                    break;

                default:
                    throw new InvalidTypeException();
            }
        }
        return cells;
    }

    public Column getColunmByName(String name){
        return columns.stream().filter(column -> column.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public void addRow(List<String> objects,int index){
        Row row = new Row();
        row.setCells(createCells(objects));
        rows.add(index,row);

    }


    public List<Row> selectRowByColumnAndValue(Map<Column,Object> map){
        List<Row> result = new ArrayList<>();
        List<Row> tempTable = new ArrayList<>(rows);
        map.forEach((Column c, Object o)-> {
           result.remove(tempTable.stream().findAny().filter(row -> !(row.getCellByColumn(c).getValue().equals(o))).get());
        });
        return result;
    }


    public Column getColumnByName(String name){
        if(columns.stream().anyMatch(column -> column.getName().equals(name))){
            return columns.stream().findAny().filter(column -> column.getName().equals(name)).get();
        }else {
            throw new NoSuchColumnException();
        }
    }
    public Row getRowByIndex(int index){
        if(rows.get(index)!=null){
            return rows.get(index);
        }else {
            throw new NoSuchRowException();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Column> getColumns() {
        return columns;
    }



    public void editRow(int index, String[] objects) {
        List<String> vals = Arrays.asList(objects);
        rows.remove(index);
        addRow(vals,index);

    }

    public static class Builder{
        private List<Column> builderColumn = new ArrayList<>();
        private String name;

        public Builder setTableName(String name){
            this.name = name;
            return this;
        }
        public Builder addColumn(Column column){
            builderColumn.add(column);
            return this;
        }
        public Table build(){
            if(builderColumn.size()<1 || name==null){
                throw  new InvalidTableException();
            }
            return new Table(this.name,this.builderColumn);
        }

    }
}
