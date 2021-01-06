package edu.project.utility_bills.view;

import javax.persistence.Column;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class UtilityResponse {
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateOfWriteUtilityMeter;
    private int hotWater;
    private int coldWater;
    private int gas;
    private int electricity;
    private int houseUtility;
    private int capitalRepair;


    public LocalDate getDateOfWriteUtilityMeter() {
        return dateOfWriteUtilityMeter;
    }

    public void setDateOfWriteUtilityMeter(LocalDate dateOfWriteUtilityMeter) {
        this.dateOfWriteUtilityMeter = dateOfWriteUtilityMeter;
    }

    public int getHotWater() {
        return hotWater;
    }

    public void setHotWater(int hotWater) {
        this.hotWater = hotWater;
    }

    public int getColdWater() {
        return coldWater;
    }

    public void setColdWater(int coldWater) {
        this.coldWater = coldWater;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public int getHouseUtility() {
        return houseUtility;
    }

    public void setHouseUtility(int houseUtility) {
        this.houseUtility = houseUtility;
    }

    public int getCapitalRepair() {
        return capitalRepair;
    }

    public void setCapitalRepair(int capitalRepair) {
        this.capitalRepair = capitalRepair;
    }
}
