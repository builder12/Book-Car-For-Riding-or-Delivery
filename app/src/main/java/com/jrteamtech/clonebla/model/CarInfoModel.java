package com.jrteamtech.clonebla.model;

import java.io.Serializable;

public class CarInfoModel implements Serializable {

    public static String TABLE_NAME = "`car_info`";

    public static String COLUMN_ID = "_id";
    public static String COLUMN_IMAGE = "_image";
    public static String COLUMN_MAKE = "_make";
    public static String COLUMN_MODEL = "_model";
    public static String COLUMN_TYPE = "_type";
    public static String COLUMN_COLOR_LABEL = "_color_label";
    public static String COLUMN_COLOR_VALUE = "_color_value";
    public static String COLUMN_YEAR = "_year";

    public static String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COLUMN_IMAGE + " INTEGER, "
            + COLUMN_MAKE + " TEXT, "
            + COLUMN_MODEL + " TEXT, "
            + COLUMN_TYPE +	" TEXT, "
            + COLUMN_COLOR_LABEL + " TEXT, "
            + COLUMN_COLOR_VALUE + " INTEGER, "
            + COLUMN_YEAR +	" TEXT"
            + ");";

    private int id;
    private int image;
    private String make;
    private String model;
    private String type;
    private String color_label;
    private int color_value;
    private String year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getColor_label() {
        return color_label;
    }

    public void setColor_label(String color_label) {
        this.color_label = color_label;
    }

    public int getColor_value() {
        return color_value;
    }

    public void setColor_value(int color_value) {
        this.color_value = color_value;
    }
}
