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
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateOfWriteUtilityMeter;
    @Column(name = "hot_water")
    private int hotWater;
    @Column(name = "cold_water")
    private int coldWater;
    @Column(name = "gas")
    private int gas;
    @Column(name = "electricity")
    private int electricity;
    @Column(name = "house_utility")
    private int houseUtility;
    @Column(name = "capital_repair")
    private int capitalRepair;
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

    public int getHotWater() {
        return hotWater;
    }

    public void setHotWater(Integer hotWater) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
