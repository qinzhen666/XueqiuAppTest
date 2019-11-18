package com.xueqiu.app.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App  extends BasePage{


    public static void start() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("deviceName","aaa");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity",".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("unicodeKeyboard",true);
        desiredCapabilities.setCapability("resetKeyboard",true);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        //真机测试时往往不需要每次清理数据和缓存
        desiredCapabilities.setCapability("noReset",false);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //对首页进入使用显示等待，利用搜索控件的出现来判断是否进入了首页，这样不影响其他元素隐式等待的时间，也解决了首页初始化加载时间过长的问题
        //但是这样有个情况不能解决：加入加载完成后有弹框出现，可能就一直无法定位到首页元素，但是实际上已经加载完成
        /*new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("home_search")));*/

        //对上述显示等待进一步处理，利用pageSource进行判断，连同一些加载完成后会出现的弹框一起判断是否初始化完成进入了主页面
        new WebDriverWait(driver,30)
                .until(x ->{
                    String xml = driver.getPageSource();
                    Boolean checkResult = xml.contains("home_search") || xml.contains("com.xueqiu.android:id/ib_close");
                    System.out.println("主页元素查找的结果是:" + checkResult);
                    return checkResult;
                });

    }

    public static SearchPage toSearch() {
        click(By.id("home_search"));
        return new SearchPage();
    }

    public static StockPage toStocks(){
        click(By.xpath("//*[contains(@resource-id, 'tab_name') and @text='自选']"));
        return new StockPage();
    }
}
