package com.xueqiu.app.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.xueqiu.app.page.App;
import com.xueqiu.app.page.BasePage;
import com.xueqiu.app.page.TestCaseSteps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestSteps {

    @Test
    void testSteps() throws JsonProcessingException {
        HashMap<String, TestCaseSteps> testcase = new HashMap<>();
        TestCaseSteps testCaseSteps = new TestCaseSteps();
        List<HashMap<String,String>> steps = new ArrayList<>();
        HashMap<String,String> location = new HashMap<>();

        location.put("id","aaa");
        location.put("send","bbb");
        steps.add(location);
        steps.add(location);

        testCaseSteps.setSteps(steps);
        testcase.put("search111",testCaseSteps);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testcase));
    }

    @Test
    void parseSteps(){
        App.start();
        BasePage basePage = new BasePage();
        basePage.parseSteps("search");
    }
}
