package com.trendyol.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Test Ortamı", "QA");
            extent.setSystemInfo("Tarayıcı", "Chrome");
            extent.setSystemInfo("Tester", "Tugcegul");
        }
        return extent;
    }
}
