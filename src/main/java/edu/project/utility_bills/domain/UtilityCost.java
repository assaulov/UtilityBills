package edu.project.utility_bills.domain;


import javax.persistence.*;


@Entity
@Table(name = "ub_utility_cost")
public class UtilityCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_id")
    private long costId;
    @Column(name = "hot_water_cost")
    private double hotWaterCost;
    @Column(name = "cold_water_cost")
    private double coldWaterCost;
    @Column(name = "gas_cost")
    private double gasCost;
    @Column(name = "electricity_cost")
    private double electricityCost;


    public long getCostId() {
        return costId;
    }

    public void setCostId(long costId) {
        this.costId = costId;
    }

    public double getHotWaterCost() {
        return hotWaterCost;
    }

    public void setHotWaterCost(double hotWaterCost) {
        this.hotWaterCost = hotWaterCost;
    }

    public double getColdWaterCost() {
        return coldWaterCost;
    }

    public void setColdWaterCost(double coldWaterCost) {
        this.coldWaterCost = coldWaterCost;
    }

    public double getGasCost() {
        return gasCost;
    }

    public void setGasCost(double gasCost) {
        this.gasCost = gasCost;
    }

    public double getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(double electricityCost) {
        this.electricityCost = electricityCost;
    }


}