package br.com.iuriraredu.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportUtils implements BeforeAllCallback, AfterAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback{
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_PATH = "target/reports/";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Override
    public void beforeAll(ExtensionContext context){
        if (extent == null){
            String reportName = "TestReport_" + dtf.format(LocalDateTime.now()) + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH + reportName);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    @Override
    public void afterAll(ExtensionContext context){
        if (extent != null){
            extent.flush();
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext context){
        test = extent.createTest(context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context){
        if (context.getExecutionException().isPresent()){
            test.log(Status.FAIL, "Test failed: " + context.getExecutionException().get().getMessage());
        } else {
            test.log(Status.PASS, "Test passed");
        }
    }

    public static void logInfo(String message){
        test.log(Status.INFO, message);
    }
}
