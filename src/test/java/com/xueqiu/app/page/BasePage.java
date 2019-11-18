package com.xueqiu.app.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static AndroidDriver<WebElement> driver;

    public static WebElement findElement(By by) {
        //todo:递归更好
        //todo:定位的元素为动态变化位置的
        //todo:解决找不到弹框后死循环问题
        try {
            System.out.println(by);
            return driver.findElement(by);
        } catch (Exception e) {
            System.out.println("进入弹框处理");
            handleAlertByPageSource();
                return driver.findElement(by); //最后调用自身完成递归，防止多弹框同时出现造成定位失败
            }
    }

    public static List<WebElement> findElements(By by){
            return driver.findElements(by);
    }

    public static void click(By by){
        findElement(by).click();
    }

    public static void sendKeys(By by,String sendContext){
        findElement(by).sendKeys(sendContext);
    }

    public static By byText(String text){
        return By.xpath("//*[@text='" + text + "']");
    }

    public static void findAndClick(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            handleAlertByPageSource();
            driver.findElement(by).click();
        }
    }

    private static void handleAlert(){
        //确定弹框的
        //缺点：每次都需要处理，影响执行效率，速度较慢
        //todo:不需要所有的弹框都判断是否存在
        List<By> alertBox = new ArrayList<>();
        alertBox.add(By.id("ib_close"));   //广告弹窗
        alertBox.add(By.xpath("ib_close"));

        alertBox.forEach(alert->{
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if (ads.size() >= 1) {
                ads.get(0).click();
            }
        });
    }

    //很多弹框的话，最好的是直接定位到到底哪个弹框在界面上，元素的判断使用xpath
    public static void handleAlertByPageSource(){
        //todo:动态弹框的处理

        String pageSource = driver.getPageSource();//可以得到一个文本字符串，也可以理解为当前页面的xml
        //黑名单
        String adBox = "com.xueqiu.android:id/ib_close";
        String gesturePromptBox = "com.xueqiu.android:id/snb_tip_text";
        String evaluateBox = "com.xueqiu.android:id/md_buttonDefaultNegative";

        //将标记和定位符存入map
        HashMap<String,By> map = new HashMap<>();
        map.put(adBox,By.id("ib_close"));
        map.put(gesturePromptBox,By.id("snb_tip_text"));
        map.put(evaluateBox,By.id("md_buttonDefaultNegative"));

        //临时修改隐式等待时间，防止查找黑名单中元素过久
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //遍历map，判断黑名单弹框元素是否存在于当前pageSource，存在即点击处理
        map.entrySet().forEach(entry ->{
            if (pageSource.contains(entry.getKey())){
                if (entry.getKey().equals("com.xueqiu.android:id/snb_tip_text")){
                    System.out.println("gesturePromptBox found");
                    Dimension size = driver.manage().window().getSize();
                    /*try {
                        if (driver.findElements(entry.getValue()))
                    }*/
                    new TouchAction<>(driver).tap(PointOption.point(size.width/2,size.height/2)).perform();
                }else {
                    driver.findElement(entry.getValue()).click();
                }
            }
        });
        //判断完成后将隐式等待时间恢复
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }




}
