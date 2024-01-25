package com.eappeal.report;

import net.sf.jasperreports.engine.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AppealReport {
    public static void main (String[] args) {
        try {
            String jasperReportFilePath = "/home/omar/IdeaProjects/jsfJpaSecurity/src/main/resources/reports/AppealForm.jrxml";

            Map<String, Object> parameters = getStringObjectMap();


            JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportFilePath);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/omar/Documents/codes/server-lib-others/jasperReport/AppealForm.pdf");


        } catch (Exception e) {
            System.out.println("Exception while creating report" + e);
        }
    }

    private static Map<String, Object> getStringObjectMap() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appealOfficeName", "কাস্টমস, এক্সাইজ ও ভ্যাট আপিলাত ট্রাইব্যুনাল");
        parameters.put("appealOfficeAddress", "রাজস্ব ভবন (১১ তলা), প্লট- এফ, ১/এ, আগারগাঁও, শেরেবাংলানগর, ঢাকা-১২০৭।");
        parameters.put("appealOfficeWebsite", "www.cevt.gov.bd");

        parameters.put("appealSubject", "আপিলাত ট্রাইব্যুনালে আপিল দায়ের নিমিত্ত নির্ধারিত ফরম- মোকামঃ কাস্টমস, এক্সাইজ ও ভ্যাট আপিলাত ট্রাইব্যুনাল, ঢাকা।");
        parameters.put("appealNumber", "2017331042");

        parameters.put("appellantNameAddressMobileEmail", "সাকিব আল হাসান, প্রোপাইটরঃ মুমু এন্টারপ্রাইজ, " +
                "ঠিকানাঃ বাসা-১২৯/এ, দক্ষিণ নালা পাড়া, ডাকঘরঃ জিপও-৪০০০, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম");
        parameters.put("respondentNameAddressMobileEmail", "রাজস্ব কর্মকর্তা, সেকশন-২, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম");

        parameters.put("subjectOfAllegation", "শুল্ক মূল্যায়ন বিধিমালা ২০০০ এর বিধি বিধান প্রতিপালন না করে মনগড়া ভাবে অতি উচ্চমূল্যে বেআইনিভাবে শুল্কায়নের আদেশকে তর্কিত করে সূত্রপাত।  শুল্ক মূল্যায়ন বিধিমালা ২০০০ এর বিধি বিধান প্রতিপালন না করে মনগড়া ভাবে অতি উচ্চমূল্যে বেআইনিভাবে শুল্কায়নের আদেশকে তর্কিত করে সূত্রপাত।");

        parameters.put("ordernumber", "123456789");
        parameters.put("issueDate", LocalDate.now());
        parameters.put("receivedDate", LocalDate.now());

        return parameters;
    }
}
