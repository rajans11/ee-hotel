package com.ee.cucumber.cucumber;

import java.util.HashMap;
import java.util.Map;

public class ScenarioSession {

    private Map<String, Object> sessionData = new HashMap<String, Object>();

    public ScenarioSession putData(String key, Object value) {
        sessionData.put(key, value);
        return this;
    }

    public Object getData(String key) {
        return sessionData.get(key);
    }
}