package Bai26Parallel.Testcase;

import Bai26Parallel.Pages.PageSignin;
import Bai26Parallel.Pages.CommonPage;
import salary.com.Helper.ExcelHelpers;
import salary.com.Helper.PropertiesHelper;
import common.BaseTestNew;
import dataprovider.DataProviderManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestcaseSignin extends BaseTestNew{
    public PageSignin pageSignin;
    public CommonPage commonPage;

    @BeforeMethod
    public void Login() {
        pageSignin = new PageSignin();
        commonPage=new CommonPage();
    }
    @Test(priority = 0,dataProvider = "data_provider_login",dataProviderClass = DataProviderManager.class)
    public void LoginSalaryProvider(String username, String password) {
        pageSignin.Login(username,password);
        commonPage.Logout();
    }
    @Test(priority = 0,dataProvider = "data_provider_login_excel",dataProviderClass = DataProviderManager.class)
    public void LoginSalaryProviderExcel(String username, String password) {
        pageSignin.Login(username,password);
        commonPage.Logout();
    }
    @Test(priority = 0,dataProvider = "data_provider_login_excel_by_row",dataProviderClass = DataProviderManager.class)
    public void LoginSalaryProviderExcelHash(Hashtable<String,String>data) {
        pageSignin.Login(data.get("username"),data.get("password"));
        commonPage.Logout();
    }
    @Test(priority = 1)
    public void LoginSuccess() {
        pageSignin.Login(PropertiesHelper.getValue("account"), PropertiesHelper.getValue("password"));
        commonPage.Logout();
    }
    @Test(priority = 2)
    public void LoginSuccessexcel() {
        ExcelHelpers excelHelpers=new ExcelHelpers();
        excelHelpers.setExcelFile("datatest/login.xlsx","Sheet1");
        System.out.println(excelHelpers.getLastRowNumber());
        pageSignin.Login(excelHelpers.getCellData("username",1), excelHelpers.getCellData("password",2));
        commonPage.Logout();
    }

    @Test(priority = 3)
    public void LoginSuccessAllexcel() {
        ExcelHelpers excelHelpers=new ExcelHelpers();
        excelHelpers.setExcelFile("datatest/login.xlsx","Sheet1");
        System.out.println(excelHelpers.getLastRowNumber());
        System.out.println(excelHelpers.getPhysicalNumberOfWRows());
        for(int i=1;i<=excelHelpers.getLastRowNumber();i++){
            System.out.println(excelHelpers.getCellData("username",i));
            System.out.println(excelHelpers.getCellData("password",i));
        }
        //còn setCell data nữa mà thấy ko hay nên ko làm theo
    }

    @Test(priority = 4)
    public void LoginFailed1() {
        pageSignin.Loginwrongusername("admin1", "123456");
    }

    @Test(priority = 5)
    public void LoginFailed2() {
        pageSignin.Loginwrongpassword("admin", "1234567");
    }

    @Test(priority = 6)
    public void LoginOA() {
        pageSignin.LoginOA("220628201", "789789");

    }
}
