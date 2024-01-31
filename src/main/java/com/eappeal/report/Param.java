package com.eappeal.report;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Param {
    public Map<String, Object> getTribunalCauseListParams() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appealOfficeName", "কাস্টমস, এক্সাইজ ও ভ্যাট আপিলাত ট্রাইব্যুনাল");
        parameters.put("appealOfficeAddress", "রাজস্ব ভবন (১১ তলা), প্লট- এফ, ১/এ, আগারগাঁও, শেরেবাংলানগর, ঢাকা-১২০৭।");
        parameters.put("appealOfficeWebsite", "www.cevt.gov.bd");

        parameters.put("scheduleDate", Utils.getBanglaDate(LocalDate.now()));
        parameters.put("benchNumber", Utils.englishToBanglaDigitConversion("3"));


        List<CauseList> causeListCollection = getCauseListCollection();
        parameters.put("causeListCollectionParam", new JRBeanCollectionDataSource(causeListCollection));

        return parameters;
    }




    private List<CauseList> getCauseListCollection() {
        CauseList c1 = new CauseList("1234", "Abdul Kuddus", "commssioner, Dhaka", "12/12/2012", "12/12/2012", "I want to break freee");
        CauseList c2 = new CauseList("1234", "Abdul Kuddus", "commssioner, Dhaka", "12/12/2012", "12/12/2012", "I want to break freee");
        CauseList c3 = new CauseList("1234", "Abdul Kuddus", "commssioner, Dhaka", "12/12/2012", "12/12/2012", "I want to break freee");

//        c1.setAppealNumber("1234");
//        c1.setAppellantName("Abdul Kuddus");
//        c1.setRespondentPostAndOffice("commissioner, Dhaka");
//        c1.setScheduleTime("12/12/2012");
//        c1.setExpiryDate("12/12/2012");
//        c1.setRemarks("I want to break free");
//        System.out.println(c1.toString());

        List<CauseList> causeLists = new ArrayList<>();
        causeLists.add(c1);
        causeLists.add(c2);
        causeLists.add(c3);
        return causeLists;
    }

}
