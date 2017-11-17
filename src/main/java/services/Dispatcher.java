package services;

import exceptions.InvalidTypeException;
import services.enums.DataType;

import java.io.IOException;
import java.util.*;

public class Dispatcher {
    private static DatabaseManager databaseManager = DatabaseManager.getInstance();


    private static Database currentDatabase = null;
    private static Table currentTable = null;

    public static void create_database(String[] commandLine){
        String databaseName = commandLine[0];
        databaseManager.createDatabase(databaseName);
    }

    public static void use_database(String[] commandLine) {
        currentDatabase = databaseManager.getDatabaseByName(commandLine[0]);
    }

    public static void save_database(String[] commandLine){
        String path = commandLine[0];
        try {
            currentDatabase.localSave(path);
        } catch (IOException e) {
            System.out.println("Error, Database can't be saved in this path");
        }
    }

    public static void load_database(String[] commandLine){
        String path = commandLine[0];
        try {
            Database loaded_database = Database.loadFromFile(path);
            databaseManager.addDatabase(loaded_database);
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong file");
        }
    }
    public static void delete_database(String[] commandLine) {
        String databaseName = commandLine[0];
        if(currentDatabase.getName().equals(databaseName)){
            currentDatabase = null;
        }
        databaseManager.removeDatabase(databaseName);
    }

    public static void create_table(String[] commandLine){
        if(currentDatabase!=null){
            String tableName = commandLine[0];
            Map<String,DataType> columns = new HashMap<>();
            for (int i = 0; i<(commandLine.length-1)/2; i++){
                columns.put(commandLine[2*(i+1)-1],getDatatype(commandLine[2*(i+1)]));
            }
            currentDatabase.addTable(tableName,columns);
        }else {
            System.out.println("Current database is not found");
        }

    }

    public static void use_table(String[] commandLine) {
        String tableName = commandLine[0];
        if(currentDatabase!= null){
            currentTable = currentDatabase.getTableByName(tableName);
        }else {
            System.out.println("Current database is not found");
        }
    }

    public static void delete_table(String[] commandLine){
        if(currentDatabase!=null){
            String tableName = commandLine[0];
            currentDatabase.removeTable(tableName);
        }else {
            System.out.println("Current database is not found");
        }

    }
    public static void add_row(String[] commandLine) {
        if(currentTable!=null) {
            currentTable.addRow(Arrays.asList(commandLine));
        }else {
            System.out.println("Current table is not found");
        }
    }
    public static void view_table(String[] commandLine){
        if(currentDatabase!=null){
            String tableName = commandLine[0];
            currentDatabase.viewTable(tableName);
        }else {
            System.out.println("Current database is not found");
        }
    }

    public static void edit_row(String[] commandLine){
        if(currentTable!= null){
            int index = Integer.parseInt(commandLine[0]);
            currentTable.editRow(index,Arrays.copyOfRange(commandLine,1,commandLine.length));
        }else {
            System.out.println("Current table is not found");
        }

    }
    private static DataType getDatatype(String type){
        switch (type){
            case ("int"):
                return DataType.INTEGER;
            case ("double"):
                return DataType.REAL;
            case ("char"):
                return DataType.CHAR;
            case ("stringarray"):
                return DataType.STRING_ARRAY;
            case ("stringinvl"):
                return DataType.STRING_INVL;
            case ("enum"):
                return DataType.MY_ENUM;
            default:
                throw new InvalidTypeException();
        }
    }


    public static void status() {
        if(currentDatabase!=null) {
            System.out.println("Current database is " + currentDatabase.getName());
        }else {
            System.out.println("No current database");
        }
        if(currentTable!=null) {
            System.out.println("Current table is " + currentTable.getName());
        }else {
            System.out.println("No current database");
        }
    }


    public static void remove_copy_from_table(String[] commandLine) {
        String tableName = commandLine[0];
        Table table = currentDatabase.getTableByName(tableName);
        List<Row> rowsToRemove = new ArrayList<>();
        for(Row row : table.getRows()){
            for (Row row2: table.getRows()){
                if(row != row2){
                    if(row.equals(row2) && !rowsToRemove.contains(row)){
                        rowsToRemove.add(row2);
                    }
                }
            }
        }
        for (Row row: rowsToRemove){
            table.getRows().remove(row);
        }
    }

    public static void rename_column_name(String[] commandLine) {
        String oldName = commandLine[0];
        String newName = commandLine[1];
        currentTable.getColumnByName(oldName).setName(newName);
    }

    public static Database getCurrentDatabase() {
        return currentDatabase;
    }

    public static Table getCurrentTable() {
        return currentTable;
    }

    public static void setCurrentDatabase(Database currentDatabase) {
        Dispatcher.currentDatabase = currentDatabase;
    }

    public static void setCurrentTable(Table currentTable) {
        Dispatcher.currentTable = currentTable;
    }
}
