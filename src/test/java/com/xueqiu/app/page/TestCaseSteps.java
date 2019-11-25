package com.xueqiu.app.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCaseSteps {
    public List<HashMap<String, String>> getSteps() {
        return steps;
    }

    public void setSteps(List<HashMap<String, String>> steps) {
        this.steps = steps;
    }

    public List<HashMap<String,String>> steps = new ArrayList<>();
}
