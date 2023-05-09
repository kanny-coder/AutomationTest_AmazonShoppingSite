package utils;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.TestBase;

public class Reporting extends TestBase {

	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;

	public void setUpReport() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + prop.getProperty("extentReportLoc")
				+ prop.getProperty("extentReportTitle") + ".html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle(prop.getProperty("extentReportTitle"));
		extent.attachReporter(spark);
	}

	public void testCase(String testName, String description) {
		test = extent.createTest(testName, description);
	}

	public void info(String msg) {
		test.info(msg);
	}

	public void pass(String msg) {
		test.pass(msg);
	}

	public void fail(String msg) {
		test.fail(msg);
	}

	public void flushReport() {
		extent.flush();
	}

	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "*****Test Case Failed***** " + "\n" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "*****Test Case Passed*****");
		} else {
			test.log(Status.SKIP, "*****Test Case Passed*****");
		}
	}
}