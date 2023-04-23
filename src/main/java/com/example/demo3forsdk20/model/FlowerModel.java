package com.example.demo3forsdk20.model;

public class FlowerModel {

    private int FLOWER_ID = -1;
    private String NAME;
    private String IMAGE;
    private String FLOWER_TYPE;
    private String LIGHT_NEED;
    private String WATER_NEED;
    private String DESCRIPTION;

    public FlowerModel(int FLOWER_ID, String NAME, String IMAGE, String FLOWER_TYPE, String LIGHT_NEED, String WATER_NEED, String DESCRIPTION) {
        this.FLOWER_ID = FLOWER_ID;
        this.NAME = NAME;
        this.IMAGE = IMAGE;
        this.FLOWER_TYPE = FLOWER_TYPE;
        this.LIGHT_NEED = LIGHT_NEED;
        this.WATER_NEED = WATER_NEED;
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getFLOWER_ID() {
        return FLOWER_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public String getFLOWER_TYPE() {
        return FLOWER_TYPE;
    }

    public String getLIGHT_NEED() {
        return LIGHT_NEED;
    }

    public String getWATER_NEED() {
        return WATER_NEED;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
}
