package edu.project.utility_bills.domain;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ub_utiliti_cost")
public class UtilitiCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_id")
    private long costId;
    @Column(name = "hot_water_cost")
    private int hotWaterCost;
    @Column(name = "cold_water_cost")
    private int coldWaterCost;
    @Column(name = "gas_cost")
    private int gasCoas;
    @Column(name = "electricity_cost")
    private int electricityCost;
    @Column(name = "house_utility_cost")
    private int houseUtilityCost;
    @Column(name = "capital_repair_cost")
    private int capitalRepairCost;

    public long getCostId() {
        return costId;
    }

    public void setCostId(long costId) {
        this.costId = costId;
    }

    public int getHotWaterCost() {
        return hotWaterCost;
    }

    public void setHotWaterCost(int hotWaterCost) {
        this.hotWaterCost = hotWaterCost;
    }

    public int getColdWaterCost() {
        return coldWaterCost;
    }

    public void setColdWaterCost(int coldWaterCost) {
        this.coldWaterCost = coldWaterCost;
    }

    public int getGasCoas() {
        return gasCoas;
    }

    public void setGasCoas(int gasCoas) {
        this.gasCoas = gasCoas;
    }

    public int getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(int electricityCost) {
        this.electricityCost = electricityCost;
    }

    public int getHouseUtilityCost() {
        return houseUtilityCost;
    }

    public void setHouseUtilityCost(int houseUtilityCost) {
        this.houseUtilityCost = houseUtilityCost;
    }

    public int getCapitalRepairCost() {
        return capitalRepairCost;
    }

    public void setCapitalRepairCost(int capitalRepairCost) {
        this.capitalRepairCost = capitalRepairCost;
    }
}
