package com.xueqiu.app.testcase;

import com.xueqiu.app.page.App;
import com.xueqiu.app.page.StockPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class TestStock {

    public static StockPage stockPage;

    @BeforeAll
    static void beforeAll(){
        App.start();
        stockPage = App.toStocks();
    }

    @Test
    void addDefaultSelectedStocks(){
        List<String> allStocks = stockPage.addDefaultStocks().getAllStocks();
        assertThat(allStocks.size(),greaterThanOrEqualTo(6));
    }
}
