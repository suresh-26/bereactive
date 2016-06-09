/*
 * Copyright (c) Rakuten, Inc. All Rights Reserved.
 * 
 * This program is the information assets which are handled
 * as "Strictly Confidential".
 * Permission of Use is only admitted in Rakuten Inc.
 * Development Department.
 * If you don't have permission , MUST not be published,
 * broadcast, rewritten for broadcast or publication
 * or redistributed directly or indirectly in any medium.
 * 
 * $Id$
 * Created on Feb 1, 2016
 * Updated on $Date$
 */
package org.gradle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.ReportBuilder;

/**
 * @author suresh.gupta
 *
 */
public class CucumberReport {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            generateReport();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void generateReport() throws Exception {

        File reportOutputDirectory = new File("D:/idc repos/testing_integration/build/reportBuilder");
        List<String> list = new ArrayList<String>();
        list.add("D:/idc repos/testing_integration/build/reports/cucumber.json");
        // list.add("build/gwpm.json");
        // list.add("build/gufw.json");
        // list.add("build/gcar.json");
        // list.add("build/rca.json");

        String pluginUrlPath = "";
        String buildNumber = "1";
        String buildProject = "cucumber-jvm";
        boolean skippedFails = false;
        boolean undefinedFails = false;
        boolean flashCharts = true;
        boolean runWithJenkins = false;
        boolean artifactsEnabled = false;
        String artifactConfig = "";
        boolean highCharts = false;

        ReportBuilder reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber,
                buildProject, skippedFails, undefinedFails,
                flashCharts, runWithJenkins, artifactsEnabled, artifactConfig, highCharts);
        reportBuilder.generateReports();
    }

}
