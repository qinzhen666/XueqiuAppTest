package com.xueqiu.app.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

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
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static SearchPage toSearch() {
        click(By.id("home_search"));
        return new SearchPage();
    }

    public static StockPage toStocks(){
        click(byText(""));
        return new StockPage();
    }
}
