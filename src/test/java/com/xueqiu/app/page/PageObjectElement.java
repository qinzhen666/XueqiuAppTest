package com.xueqiu.app.page;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageObjectElement {

    public List<HashMap<String,String>> element = new ArrayList<>();

    public By getLocator() {
        String osOrigin = BasePage.driver.getCapabilities().getPlatform().toString().toLowerCase();
        String id = "xxx";
        return By.id(id);
    }

    public By getLocator(String os,String version){
        for (HashMap<String, String> map : element) {
            if (map.get("os") == os && map.get("version") == version){
                if (map.get("id") != null){
                    return By.id(map.get("id"));
                }else if (map.get("xpath") != null){
                    return By.xpath(map.get("xpath"));
                }
            }
            break;
        }
        return null;
    }
}
