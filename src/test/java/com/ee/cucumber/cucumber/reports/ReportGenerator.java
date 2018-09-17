package com.ee.cucumber.cucumber.reports;

import net.masterthought.cucumber.ReportBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to generate Cucumber HTML reports (http://www.masterthought.net/section/cucumber-reporting)
 * Adds a shutdown hook that will look for json reports in target path and generate HTML report in.
 */
@Component
public class ReportGenerator extends Thread {

    public void run() {
        String now  = LocalDateTime.now().toString();
        File reportOutputDirectory = new File("build/cucumber-reporting-"+now);
        List<String> jsonReportFiles = new ArrayList<String>();
        jsonReportFiles.add("build/cucumber.json");

        try {
            ReportBuilder reportBuilder = new ReportBuilder(jsonReportFiles,
                    reportOutputDirectory,
                    "",
                    "local build",
                    "cucumber-jvm",
                    false,
                    false,
                    true,
                    true,
                    false,
                    "",
                    true);
            reportBuilder.generateReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}