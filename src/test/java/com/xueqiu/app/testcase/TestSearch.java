package com.xueqiu.app.testcase;

import com.xueqiu.app.page.App;
import com.xueqiu.app.page.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class TestSearch {
    public static SearchPage searchPage;

    @BeforeAll
    static void beforeAll(){
        App.start();
        searchPage = App.toSearch();
    }

    @Test
    void search(){
        Double currentPrice = searchPage.search("alibaba").getCurrentPrice();
        assertThat(currentPrice,greaterThanOrEqualTo(120d));
    }

    @AfterEach
    void cancel(){
        searchPage.cancel();
    }
}
