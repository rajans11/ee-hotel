package com.ee.cucumber.cucumber;

import com.ee.cucumber.cucumber.reports.ReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
public class ScenarioWrapper {

    @Autowired
    private ReportGenerator reportGenerator;

    @PostConstruct
    public void generateReport() {
        try {
            Runtime.getRuntime().addShutdownHook(reportGenerator);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }
}
