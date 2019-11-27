package com.xueqiu.app.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.xueqiu.app.page.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestSteps {

    @Test
    void testSteps() throws JsonProcessingException {
        HashMap<String, PageObjectMethod> testcase = new HashMap<>();
        PageObjectMethod testCaseSteps = new PageObjectMethod();
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
        App.getInstance().start();
        BasePage basePage = new BasePage();
        basePage.parseSteps("search");
    }

    @Test
    void testMethod(){
        Arrays.stream(Thread.currentThread().getStackTrace()).forEach(stack ->{
            System.out.println(stack.getClassName()+" : "+stack.getMethodName());
        });

        System.out.println("===="+Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    void testPOM() throws JsonProcessingException {
        PageObjectModel model = new PageObjectModel();
        PageObjectElement element = new PageObjectElement();

        HashMap<String,String> map2 = new HashMap<>();
        map2.put("id","aaa");
        map2.put("xpath","xxx");
        element.element.add(map2);
        model.elements.put("search_locator", element);

        PageObjectMethod method = new PageObjectMethod();
        List<HashMap<String ,String>> steps = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();

        map.put("id","bbb");
        map.put("send","ccc");
        steps.add(map);
        steps.add(map);
        steps.add(map);
        method.setSteps(steps);
        model.methods.put("search", method);
        model.methods.put("cancel",method);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model));
    }

    @Test
    void scrollTest() throws InterruptedException {
        App.getInstance().start();
        for (int i = 0; i < 5; i++) {
            TouchAction action  = new TouchAction(App.driver);
            Dimension size = App.driver.manage().window().getSize();
            Duration duration=Duration.ofMillis(500);
            action
            .press(PointOption.point(size.width/2,size.height/2))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(size.width/2,size.height/4))
            .release().perform();
            System.out.println("scrollTest " + i +"次");
            Thread.sleep(1000);
        }
    }
}
