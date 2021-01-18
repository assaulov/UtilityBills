package edu.project.utility_bills.view;

import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class UtilityRequest {

    private long userId;

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
