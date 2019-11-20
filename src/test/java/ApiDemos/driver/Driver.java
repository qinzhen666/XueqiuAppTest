package ApiDemos.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    public static AppiumDriver appiumdriver;
    static Driver driver;
    public static Driver getInstance(){
        if (driver == null){
            driver = new Driver();
        }
        return driver;
    }

    public static void start() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("deviceName","aaa");
        desiredCapabilities.setCapability("appPackage","io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity",".ApiDemos");
        desiredCapabilities.setCapability("unicodeKeyboard",true);
        desiredCapabilities.setCapability("resetKeyboard",true);
        //真机测试时往往不需要每次清理数据和缓存
        desiredCapabilities.setCapability("noReset",true);

        try {
            appiumdriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
            appiumdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void testToast(){
        appiumdriver.findElementByXPath("//*[@text='Views']").click();
        //利用Android的API进行滑屏操作
        ((AndroidDriver<MobileElement>)appiumdriver).
                findElementByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                                "scrollIntoView(new UiSelector().text(\"Popup Menu\").instance(0))").click();
        appiumdriver.findElementByXPath("//*[contains(@text,'MAKE')]").click();
        appiumdriver.findElementByXPath("//*[@text='Search']").click();
        //识别Toast
        System.out.println( appiumdriver.findElementByXPath("//*[@class='android.widget.Toast']").getText());
        //System.out.println( appiumdriver.findElementByClassName("android.widget.Toast").getText());  不可用
    }

    /*public static void main(String[] args) {
        start();
        testToast();
    }*/


        private static int sum(int[] arr, int z) {

            if (z == arr.length) {
                return 0;
            }

            int x = sum(arr, z + 1);

            int res = arr[z] + x;

            return res;
        }


        public static void main(String[] args) {
            int arr[] = {1, 2};
            sum(arr, 0);
        }


}
