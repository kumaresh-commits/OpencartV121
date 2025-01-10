package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spartReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the method
	String repName;

	public void onStart(ITestContext testcontext) {

		/*
		 * SimpleDateFormat df = new SimpleDateFormat(); Date dt=new Date(); String
		 * currentdatetimestamp=df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		repName = "Test-Report-" + timeStamp + ".html";
		spartReporter = new ExtentSparkReporter(".\\reports\\"+repName); // Specify location of the report

		spartReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of report
		spartReporter.config().setReportName("Opencart Functional testing"); // name of the report
		spartReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(spartReporter);
		extent.setSystemInfo("Alppication", "Opencart");
		extent.setSystemInfo("Modul", "Admin");
		extent.setSystemInfo("Sub Modul", "Costomers");
		extent.setSystemInfo("Engineer", "Kumaresan Durairaj");
		//extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		
			List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
			if (!includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includedGroups.toString());
			}
	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups()); // to display group in report
		test.log(Status.PASS, result.getName() + " got successfully executed");

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {

		extent.flush();

		String pathOfExtentReports = System.getProperty("user.dir")+"\\reports\\"+ repName;
		File extentReports = new File(pathOfExtentReports);
		
		  try { 
			  Desktop.getDesktop().browse(extentReports.toURI()); 
		  } catch
		  (IOException e) 
		  { 
		  e.printStackTrace(); 
		  }
		  
		 

		// don't multiple time by your own email id, may your gmail id will blocked.
		/*
		 * try { URL url = new URL("file:///" + System.getProperty("user.dir") +
		 * "\\reports\\" + repName);
		 * 
		 * // Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("www.gmail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("kumararesh.d@gmail.com",
		 * "Omshivam@12345")); email.setSSLOnConnect(true);
		 * email.setFrom("kumararesh.d@gmail.com"); // Sender
		 * email.setSubject("Test Result");
		 * email.setMsg("Please find attached report...");
		 * email.addTo("sunderpichhai@gmail.com"); // Recevier email.attach(url,
		 * "extent report", "Please chech report"); email.send(); } catch (Exception e)
		 * { e.printStackTrace(); }
		 */
	}

}
