package edu.project.utility_bills.view;

import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class UtilityRequest {


    private long userId;
    private long utilityId;
    private LocalDate dateOfWriteUtilityMeter;

    private LocalDate dateFrom;
    private LocalDate dateTo;

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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public long getUtilityId() {
        return utilityId;
    }

    public void setUtilityId(long utilityId) {
        this.utilityId = utilityId;
    }

    @Override
    public String toString() {
        return "UtilityRequest{" +
                "userId=" + userId +
                ", utilityId=" + utilityId +
                ", dateOfWriteUtilityMeter=" + dateOfWriteUtilityMeter +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
