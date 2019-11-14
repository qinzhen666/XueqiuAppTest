package com.xueqiu.app.page;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class StockPage extends BasePage{
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
}
