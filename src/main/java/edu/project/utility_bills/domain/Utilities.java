package edu.project.utility_bills.domain;




import edu.project.utility_bills.view.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;



@Table(name = "ub_utilities")
@Entity
public class Utilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utility_id")
    private long utilityId;
    @Column(name = "date_of_write_utility_meter")
    private LocalDate dateOfWriteUtilityMeter;
    @Column(name = "hot_water")
    private double hotWater;
    @Column(name = "cold_water")
    private int coldWater;
    @Column(name = "gas")
    private double gas;
    @Column(name = "electricity")
    private double electricity;
    @Column(name = "house_utility")
    private double houseUtility;
    @Column(name = "capital_repair")
    private double capitalRepair;
    @ManyToOne (fetch=FetchType.EAGER, optional=true, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    private User user;

    public long getUtilityId() {
        return utilityId;
    }

    public void setUtilityId(long utilityId) {
        this.utilityId = utilityId;
    }

    public LocalDate getDateOfWriteUtilityMeter() {
        return dateOfWriteUtilityMeter;
    }

    public void setDateOfWriteUtilityMeter(LocalDate dateOfWriteUtilityMeter) {
        this.dateOfWriteUtilityMeter = dateOfWriteUtilityMeter;
    }

    public double getHotWater() {
        return hotWater;
    }

    public void setHotWater(double hotWater) {
        this.hotWater = hotWater;
    }

    public int getColdWater() {
        return coldWater;
    }

    public void setColdWater(int coldWater) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
