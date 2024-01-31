package com.eappeal.report;

import java.io.Serializable;

public class CauseList{
    private String appealNumber;
    private String appellantName;
    private String respondentPostAndOffice;
    private String scheduleTime;
    private String expiryDate;
    private String remarks;

    public CauseList(String appealNumber, String appellantName, String respondentPostAndOffice, String scheduleTime, String expiryDate, String remarks) {
        this.appealNumber = appealNumber;
        this.appellantName = appellantName;
        this.respondentPostAndOffice = respondentPostAndOffice;
        this.scheduleTime = scheduleTime;
        this.expiryDate = expiryDate;
        this.remarks = remarks;
    }


    public String getAppealNumber() {
        return appealNumber;
    }

    public void setAppealNumber(String appealNumber) {
        this.appealNumber = appealNumber;
    }

    public String getAppellantName() {
        return appellantName;
    }

    public void setAppellantName(String appellantName) {
        this.appellantName = appellantName;
    }

    public String getRespondentPostAndOffice() {
        return respondentPostAndOffice;
    }

    public void setRespondentPostAndOffice(String respondentPostAndOffice) {
        this.respondentPostAndOffice = respondentPostAndOffice;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}