package com.xueqiu.app.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class StockPage extends BasePage{

    By search = By.id("action_search");
    By searchInput = By.id("search_input_text");
    By stockNameChose = By.id("name");
    By addOptional = By.id("follow_btn");
    By cancel = By.id("action_close");

    public StockPage deleteAll(){
        click(By.id("edit_group"));
        click(By.id("check_all"));
        click(By.id("cancel_follow"));
        click(By.id("md_buttonDefaultPositive"));
        click(By.id("action_close"));
        return this;
    }

    public List<String> getAllStocks(){
        handleAlertByPageSource();

        List<String> list = new ArrayList<>();
        findElements(By.id("portfolio_stockName")).forEach(element -> {
           list.add(element.getText());
       });
        System.out.println(list);
        return list;
    }

    public StockPage addDefaultStocks() {
        if (getAllStocks().size() >= 1){
            deleteAll();
        }
        click(By.id("add_to_portfolio_stock"));
        return this;
    }

    public StockPage addOptionalStocks(String stockName){
        click(search);
        sendKeys(searchInput,stockName);
        click(searchInput);
        click(stockNameChose);
        /*new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(addOptional));*/
        click(addOptional);
        click(cancel);
        return this;
    }
}
