package listener;

import Bai26Parallel.Testcase.LearnTestListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import salary.com.Helper.CaptureHelpers;

public class Testlistener implements ITestListener {
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getName()+"is skip");

    }

    @Override
    public void onTestStart(ITestResult result) {


    }
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName()+"is pass");

    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName()+"is fail");
        CaptureHelpers.takescreenshot(result);
    }
    @Override
    public void onFinish(ITestContext result) {
        System.out.println(result.getAllTestMethods());

    }

    @Override
    public void onStart(ITestContext result) {
        System.out.println("on start"+result.getHost());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }



}
