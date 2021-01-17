package edu.project.utility_bills.view;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;



public class UtilityRequest {

    private long userId;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateOfWriteUtilityMeter;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDateOfWriteUtilityMeter() {
        return dateOfWriteUtilityMeter;
    }

    public void setDateOfWriteUtilityMeter(LocalDate dateOfWriteUtilityMeter) {
        this.dateOfWriteUtilityMeter = dateOfWriteUtilityMeter;
    }

    @Override
    public String toString() {
        return "UtilityRequest{" +
                "userId=" + userId +
                ", dateOfWriteUtilityMeter=" + dateOfWriteUtilityMeter +
                '}';
    }
}
