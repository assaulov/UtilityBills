package edu.project.utility_bills.view;
/*
Оображение запроса
 */
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class UtilityRequest {


    private long id;
    private long utilityId;
    private String username;
    private LocalDate dateOfWriteUtilityMeter;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UtilityRequest{" +
                "UserId=" + id +
                ", utilityId=" + utilityId +
                ", username='" + username + '\'' +
                ", dateOfWriteUtilityMeter=" + dateOfWriteUtilityMeter +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
