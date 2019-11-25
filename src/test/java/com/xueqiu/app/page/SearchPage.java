package com.xueqiu.app.page;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.HashMap;

public class SearchPage extends BasePage{

    private By inputBox = By.id("search_input_text");

    public SearchPage search(String sendText){ //具体的方法不要做数据驱动，否则会丢失测试步骤的执行推导过程
        HashMap<String,Object> data = new HashMap<>();
        data.put("sendText",sendText);
        setParams(data);
        parseSteps(); //todo:可通过反射来读取方法名
        /*findElement(inputBox).sendKeys(inputText);
        findElement(By.id("name")).click(); */ //默认点击找到的第一个
        return this;
    }

    public Double getCurrentPrice(){
        parseSteps();
        return Double.valueOf(getData().get("price").toString());
//        return Double.valueOf(findElement(By.id("current_price")).getText());
    }

    public App cancel(){
//        findElement(By.id("action_close")).click();
        parseSteps();
        return new App();
    }
}
