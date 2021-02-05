package edu.project.utility_bills.view;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;



@Component
public class UtilityResponse  {
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private String dateOfWriteUtilityMeter;
    private double hotWater;
    private double coldWater;
    private double gas;
    private double electricity;
    private double houseUtility;
    private double capitalRepair;
    private long utilityId;

    public String getDateOfWriteUtilityMeter() {
        return dateOfWriteUtilityMeter;
    }

    public void setDateOfWriteUtilityMeter(String dateOfWriteUtilityMeter) {
        this.dateOfWriteUtilityMeter = dateOfWriteUtilityMeter;
    }

    public double getHotWater() {
        return hotWater;
    }

    public void setHotWater(double hotWater) {
        this.hotWater = hotWater;
    }

    public double getColdWater() {
        return coldWater;
    }

    public void setColdWater(double coldWater) {
        this.coldWater = coldWater;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public double getElectricity() {
        return electricity;
    }

    public void setElectricity(double electricity) {
        this.electricity = electricity;
    }

    public double getHouseUtility() {
        return houseUtility;
    }

    public void setHouseUtility(double houseUtility) {
        this.houseUtility = houseUtility;
    }

    public double getCapitalRepair() {
        return capitalRepair;
    }

    public void setCapitalRepair(double capitalRepair) {
        this.capitalRepair = capitalRepair;
    }

    public long getUtilityId() {
        return utilityId;
    }

    public void setUtilityId(long utilityId) {
        this.utilityId = utilityId;
    }
}
