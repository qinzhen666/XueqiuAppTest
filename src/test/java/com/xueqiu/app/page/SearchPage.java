package com.xueqiu.app.page;

import org.openqa.selenium.By;

public class SearchPage extends BasePage{

    private By inputBox = By.id("search_input_text");


    public SearchPage search(String inputText){
        findElement(inputBox).sendKeys(inputText);
        findElement(By.id("name")).click();  //默认点击找到的第一个
        return this;
    }

    public Double getCurrentPrice(){
        return Double.valueOf(findElement(By.id("current_price")).getText());
    }

    public void cancel(){
        findElement(By.id("action_close")).click();
    }
}
