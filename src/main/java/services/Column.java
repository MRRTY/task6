package services;

import services.enums.DataType;

import java.io.Serializable;

public class Column implements Serializable {
    private String name;
    private DataType type;

    public Column(String name, DataType type) {
        this.name = name;
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}
