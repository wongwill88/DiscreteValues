package com.wongwill.sportsdataanalysis.entity;

public class CompanyData {
    float homeVar;
    float index;
    float awayVar;

    public float getHomeVar() {
        return homeVar;
    }

    public void setHomeVar(float homeVar) {
        this.homeVar = homeVar;
    }

    public float getIndex() {
        return index;
    }

    public void setIndex(float index) {
        this.index = index;
    }

    public float getAwayVar() {
        return awayVar;
    }

    public void setAwayVar(float awayVar) {
        this.awayVar = awayVar;
    }

    @Override
    public String toString() {
        return "CompanyData{" +
                "homeVar=" + homeVar +
                ", index=" + index +
                ", awayVar=" + awayVar +
                '}';
    }
}
