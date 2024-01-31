package com.eappeal.report;

import net.sf.jasperreports.engine.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.*;


public class AppealReport {
    public static void main (String[] args) {

        try {
            String jasperReportFilePath = "/home/omar/IdeaProjects/jsfJpaSecurity/src/main/resources/reports/TribunalCauseList.jrxml";
            Param p = new Param();
            Map<String, Object> parameters = p.getTribunalCauseListParams();

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportFilePath);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/omar/Documents/codes/server-lib-others/jasperReport/TribunalCauseList.pdf");


        } catch (Exception e) {
            System.out.println("Exception while creating report" + e);
        }
    }




    private static Map<String, Object> getAppealParams() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appealOfficeName", "কাস্টমস, এক্সাইজ ও ভ্যাট আপিলাত ট্রাইব্যুনাল");
        parameters.put("appealOfficeAddress", "রাজস্ব ভবন (১১ তলা), প্লট- এফ, ১/এ, আগারগাঁও, শেরেবাংলানগর, ঢাকা-১২০৭।");
        parameters.put("appealOfficeWebsite", "www.cevt.gov.bd");

        parameters.put("appealSubject", "আপিলাত ট্রাইব্যুনালে আপিল দায়ের নিমিত্ত নির্ধারিত ফরম- মোকামঃ কাস্টমস, এক্সাইজ ও ভ্যাট আপিলাত ট্রাইব্যুনাল, ঢাকা।");
        parameters.put("appealNumber", Utils.englishToBanglaDigitConversion("2017331042"));

        parameters.put("appellantNameAddressMobileEmail", "সাকিব আল হাসান, প্রোপাইটরঃ মুমু এন্টারপ্রাইজ, " +
                "ঠিকানাঃ বাসা-১২৯/এ, দক্ষিণ নালা পাড়া, ডাকঘরঃ জিপও-৪০০০, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম");
        parameters.put("respondentNameAddressMobileEmail", "রাজস্ব কর্মকর্তা, সেকশন-২, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম | রাজস্ব কর্মকর্তা, সেকশন-২, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম | রাজস্ব কর্মকর্তা, সেকশন-২, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম | রাজস্ব কর্মকর্তা, সেকশন-২, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম");

        parameters.put("subjectOfAllegation", "শুল্ক মূল্যায়ন বিধিমালা ২০০০ এর বিধি বিধান প্রতিপালন না করে মনগড়া ভাবে অতি উচ্চমূল্যে বেআইনিভাবে শুল্কায়নের আদেশকে তর্কিত করে সূত্রপাত।  শুল্ক মূল্যায়ন বিধিমালা ২০০০ এর বিধি বিধান প্রতিপালন না করে মনগড়া ভাবে অতি উচ্চমূল্যে বেআইনিভাবে শুল্কায়নের আদেশকে তর্কিত করে সূত্রপাত।");

        parameters.put("orderNumber", Utils.englishToBanglaDigitConversion("123456789"));
        parameters.put("issueDate", DateUtils.getLocalizedDate(LocalDate.now(), new Locale.Builder().setLanguage("bn").build()));
        parameters.put("receivedDate", DateUtils.getLocalizedDate(LocalDate.now(), new Locale.Builder().setLanguage("bn").build()));

        parameters.put("contactNameAddressMobileEmail", "সাকিব আল হাসান, প্রোপাইটরঃ মুমু এন্টারপ্রাইজ, " +
                "ঠিকানাঃ বাসা-১২৯/এ, দক্ষিণ নালা পাড়া, ডাকঘরঃ জিপও-৪০০০, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম");
        parameters.put("companyNameAddressMobileEmail", "সাকিব আল হাসান, প্রোপাইটরঃ মুমু এন্টারপ্রাইজ, " +
                "ঠিকানাঃ বাসা-১২৯/এ, দক্ষিণ নালা পাড়া, ডাকঘরঃ জিপও-৪০০০, ডাবলমুরিং, চট্টগ্রাম সিটি কর্পোরেশন, চট্টগ্রাম");

        parameters.put("totalAmount", Utils.englishToBanglaDigitConversion(Utils.banglaMoneyFormatter(BigDecimal.valueOf(4341414570.39d))));
        parameters.put("dutyTaxAmount", Utils.englishToBanglaDigitConversion(BigDecimal.valueOf(470.39d).toString()));
        parameters.put("fineAmount", Utils.englishToBanglaDigitConversion(BigDecimal.valueOf(698.21d).toString()));
        parameters.put("depositAmount", Utils.englishToBanglaDigitConversion(BigDecimal.valueOf(698.21d).toString()));
        parameters.put("valueOfGoodsForServiceAmount", Utils.englishToBanglaDigitConversion(BigDecimal.valueOf(9000d).toString()));

        parameters.put("appealClaimAmount", Utils.englishToBanglaDigitConversion(BigDecimal.valueOf(531.89d).toString()));

        parameters.put("applicationFeeNumber", Utils.englishToBanglaDigitConversion("201733150"));
        parameters.put("applicationFeeDate", DateUtils.getLocalizedDate(LocalDate.now(), new Locale.Builder().setLanguage("bn").build()));

        parameters.put("appellantName", "সাকিব আল হাসান");

        parameters.put("appealDayOfMonth", DateUtils.getLocalizedDayOfMonth(LocalDate.now(), new Locale.Builder().setLanguage("bn").build()));
        parameters.put("appealMonthValue", DateUtils.getLocalizedMonthValue(LocalDate.now(), new Locale.Builder().setLanguage("bn").build()));
        parameters.put("appealYear", DateUtils.getLocalizedYear(LocalDate.now(), new Locale.Builder().setLanguage("bn").build()));

        return parameters;
    }

}
