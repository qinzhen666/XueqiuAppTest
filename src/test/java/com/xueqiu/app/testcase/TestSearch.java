package com.xueqiu.app.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.xueqiu.app.page.App;
import com.xueqiu.app.page.SearchPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestSearch {
    public static SearchPage searchPage;

    @BeforeAll
    static void beforeAll(){
        App.start();
        searchPage = App.toSearch();
    }


    @ParameterizedTest
    @MethodSource("searchYamlData")
    void search(String searchInfo,String exceptPrice ){
        Double currentPrice = searchPage.search(searchInfo).getCurrentPrice();
        assertThat(currentPrice,greaterThanOrEqualTo(Double.parseDouble(exceptPrice)));
    }

    static Stream<Arguments> searchYamlData() throws IOException {
        Arguments arguments = null;
        List<Arguments> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        String path1 = "/com.xueqiu.app" + TestSearch.class.getCanonicalName().split("app")[1].replace(".","/") + ".yaml";
        System.out.println(path1);
        Object[][] searchData = mapper.readValue(TestSearch.class.getResourceAsStream(path1), Object[][].class);
        for (Object[] entrySet : Arrays.asList(searchData)){
            String key = Arrays.asList(entrySet).get(0).toString();
            String value =  Arrays.asList(entrySet).get(1).toString();
            arguments = arguments(key,value);
            list.add(arguments);
        }
        return Stream.of(list.get(0),list.get(1),list.get(2));
    }

    @AfterAll
    static void cancel(){
        searchPage.cancel();
    }
}
