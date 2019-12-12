package com.xueqiu.app.page;

import java.util.HashMap;

public class PageObjectModel {
    public HashMap<String, PageObjectElement> elements = new HashMap<>();
    public HashMap<String, PageObjectMethod> methods = new HashMap<>();
    public HashMap<String, PageObjectAssert> asserts = new HashMap<>();
}
