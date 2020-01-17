/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regressionexcellcreator.csv;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import regressionexcellcreator.objects.Cases;
import regressionexcellcreator.objects.MainClass;
import regressionexcellcreator.objects.Suites;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Max
 */
public class Json2CsvUpdated {
    static List<Cases> failedCasesList = new ArrayList<Cases>();
    static String newFileNamer= "1.0.99_Jenkin_run_all";
    static Map<String, List<Cases>> defectMap= new HashMap();
    static int unableToInterogateDevice=0;
    static int DeviceSessionNotStartedException=0;
    static int DriverCreationException=0;
    static int TelemetryTimedOutException=0;
    static int NoSuchSessionException=0;
    static int NoDevicesDiscoveredException=0;
    static int TelBridgeDisconnectedException=0;
    static int TargetDeviceNotAmongDiscoveredDevicesException=0;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        listFilesForFolder(new File("src\\main\\resources"));
        write2CSVForReport(defectMap);
        write2CSVForReportSummery(defectMap);
        System.out.println("count ::"+count);
    }

    public static void write2CSVForReport(Map<String, List<Cases>> failedMap) {
        String NEW_LINE_SEPARATOR = "\n";
        Object[] FILE_HEADER = {"#", "Device Type", "Spec name", "Test Id", "Feature name", "Failure Reason", "Failure category",
                "Defect", "Analysis comment", "Local Run Result",  "Duration", "Screenshot Location", "Node", "Status"};
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            fileWriter = new FileWriter(newFileNamer+".csv");
            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);
            int seq = 1;
            for(Map.Entry entry:failedMap.entrySet()){
                List<Cases> caseList = (List<Cases>) entry.getValue();
                for (Cases user : caseList) {
                    List userDataRecord = new ArrayList();
                    userDataRecord.add(seq++);
                    userDataRecord.add(user.getDeviceType());
                    //userDataRecord.add("=HYPERLINK(http://mspm1bapps1465:8080/job/"+user.getSuitName()+"/testReport/, "+user.getSpecName()+")");
                    userDataRecord.add(user.getSpecName());
                    userDataRecord.add(user.getCbId());
                    userDataRecord.add(user.getFeatureName());
                    userDataRecord.add(user.getErrorDetails());
                    userDataRecord.add(user.getFailureType());
                    userDataRecord.add("");
                    userDataRecord.add(user.getFailureAnalysis());
                    //userDataRecord.add(user.getErrorStackTrace());
                    userDataRecord.add("");
                    userDataRecord.add(user.getDuration());
                    userDataRecord.add(user.getScreenShotLocation());
                    userDataRecord.add(user.getSuitName());
                    userDataRecord.add(user.getStatus());
                    userDataRecord.add(user.getClassName());
                    //userDataRecord.add(user.getStderr());
                    csvFilePrinter.printRecord(userDataRecord);
                }
            }
            System.out.println("================================");
            System.out.println("CSV file "+newFileNamer+" was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }

    public static void write2CSVForReportSummery(Map<String, List<Cases>> failedMap) {
        String NEW_LINE_SEPARATOR = "\n";
        //CSV file header
        Object[] FILE_HEADER = {"Feature", "Base Pass", "Base Fail", "Value Pass", "Value Fail"};
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            fileWriter = new FileWriter(newFileNamer+"_summery.csv");
            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);
            System.out.println("failedMap size :::"+failedMap.size());
            System.out.println("================================////////////////////////////////////////////");
            int seq = 1;
            String[] featureList = {"aboutsystem","adaptivestim","diaries","energy","fluoroscopy","groupmanagement","impedancemeasurement",
                    "leadmanipulation","leadselection","mri","nonfunctionaltest","notifications","painmap","patientaccess","patientinfo",
                    "programmanagement","reports","simplifieddeviceconnectivity","smoketest","softwaresystemtests","trainusers","workflows",
                    "localization"};
            for(String feature:featureList){
                List userDataRecord = new ArrayList();
                userDataRecord.add(feature);
                userDataRecord.add(failedMap.containsKey(feature+" Base PASSED")? failedMap.get(feature+" Base PASSED").size() : "0");
                userDataRecord.add(failedMap.containsKey(feature+" Base FAILED")? failedMap.get(feature+" Base FAILED").size() : "0");
                userDataRecord.add(failedMap.containsKey(feature+" Value PASSED")? failedMap.get(feature+" Value PASSED").size() : "0");
                userDataRecord.add(failedMap.containsKey(feature+" Value FAILED")? failedMap.get(feature+" Value FAILED").size() : "0");
                csvFilePrinter.printRecord(userDataRecord);
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }

    static  void listFilesForFolder(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            getJsonFromFile(fileEntry.getName());
        }
    }

    static void getJsonFromFile(String path){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON file to Java object
            mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MainClass mainClass = mapper.readValue(new File("src\\main\\resources\\"+path), MainClass.class);
            Suites[] ss = mainClass.getSuites();

            for (Suites suit:ss) {
                Cases[] cases = suit.getCases();
                for (int j=0;j<cases.length ; j++) {
                    Cases caseObj = cases[j];
                    getCBIDandscreenshotLocation(caseObj, path);
                    String resut= caseObj.getStatus().equalsIgnoreCase("PASSED") || caseObj.getStatus().equalsIgnoreCase("FIXED") ? " PASSED": " FAILED";
                    List<Cases> failList = defectMap.get(caseObj.getFeatureName()+" "+caseObj.getDeviceType()+resut);
                    if(failList!=null){
                        failList.add(caseObj);
                        defectMap.put(caseObj.getFeatureName()+" "+caseObj.getDeviceType()+resut,failList);
                    }else{
                        failList = new ArrayList<>();
                        failList.add(caseObj);
                        defectMap.put(caseObj.getFeatureName()+" "+caseObj.getDeviceType()+resut,failList);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("file name is ::"+path);
            e.printStackTrace();
        }
    }

    private static Cases getCBIDandscreenshotLocation(Cases caseObj, String path) {
        String screenshotsURL = "https://s3.amazonaws.com/mdt-testlogs/VantaScreenshots_";
        String errorStack = caseObj.getStdout();
        errorStack = errorStack.replaceAll("[^\\u0000-\\uFFFF]", "");
        int index = errorStack.indexOf("Codebeamer Test ID");
        caseObj.setCbId(errorStack.substring(index+19, index+25));
        index = errorStack.indexOf(screenshotsURL);
        if(index>-1){
            caseObj.setScreenShotLocation(errorStack.substring(index, index+103-11));
        }
        if(!(caseObj.getStatus().equalsIgnoreCase("PASSED") || caseObj.getStatus().equalsIgnoreCase("FIXED"))){
            getFailureReasons(caseObj);
        }

        String[] classname=caseObj.getClassName().split(Pattern.quote("."));
        caseObj.setSpecName(classname[classname.length-1]);
        caseObj.setFeatureName(classname[classname.length-2]);
        if(path.contains("_Base_"))
            caseObj.setDeviceType("Base");
        else
            caseObj.setDeviceType("Value");
        caseObj.setSuitName(path.substring(0,path.length()-5));
        return caseObj;
    }

    private static void getFailureReasons(Cases caseObj) {
        if(caseObj.getErrorStackTrace().contains("connectButton.displayed\n" +
                "|             |\n" +
                "|             false")){
            caseObj.setFailureAnalysis("Connect button not found");
        }else if(caseObj.getErrorStackTrace().contains("$('--description(\\\"Close\\\")')\n" +
                "|\n" +
                "[]")){
            caseObj.setFailureAnalysis("app crashes while closing");
        }else if(caseObj.getErrorStackTrace().contains("java.lang.Exception: Unable to interrogate device in 5 attempts")){
            caseObj.setFailureAnalysis("Unable to interrogate in 5 attempts");
            unableToInterogateDevice++;
            //System.out.println("Unable to interrogate:::::::");
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("com.medtronic.neuro.ultrahelper.exceptions.DeviceSessionNotStartedException")){
            caseObj.setFailureAnalysis("DeviceSessionNotStartedException");
            //System.out.println("DeviceSessionNotStartedException::::");
            DeviceSessionNotStartedException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("geb.driver.DriverCreationException")){
            caseObj.setFailureAnalysis("DriverCreationException issue");
            //System.out.println("DriverCreationException::::");
            DriverCreationException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("com.medtronic.neuro.ultrahelper.exceptions.TelemetryTimedOutException")){
            caseObj.setFailureAnalysis("TelemetryTimedOutException issue");
            //System.out.println("TelemetryTimedOutException:::::");
            TelemetryTimedOutException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("NoSuchSessionException: Session ID is null")){
            caseObj.setFailureAnalysis("NoSuchSessionException issue");
            //System.out.println("NoSuchSessionException::::::");
            NoSuchSessionException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("com.medtronic.neuro.ultrahelper.exceptions.NoDevicesDiscoveredException")){
            caseObj.setFailureAnalysis("NoDevicesDiscoveredException issue");
            ////System.out.println("NoDevicesDiscoveredException::::::");
            NoDevicesDiscoveredException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("TelBridgeDisconnectedException: Bridge disconnected")){
            caseObj.setFailureAnalysis("TelBridgeDisconnectedException issue");
            //System.out.println("TelBridgeDisconnectedException::::::");
            TelBridgeDisconnectedException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("TargetDeviceNotAmongDiscoveredDevicesException: Target device serial number not among discovered device serial numbers")){
            caseObj.setFailureAnalysis("TargetDeviceNotAmongDiscoveredDevicesException issue");
            //System.out.println("TargetDeviceNotAmongDiscoveredDevicesException::::::");
            TargetDeviceNotAmongDiscoveredDevicesException++;
            caseObj.setFailureType("Environment");
        }else if(caseObj.getErrorStackTrace().contains("java.lang.RuntimeException: Failed to start session: com.medtronic.neuro.ultrahelper.exceptions.NoResponseFromUltraServerException:")){
            caseObj.setFailureAnalysis("NoResponseFromUltraServerException issue");
            //System.out.println("TargetDeviceNotAmongDiscoveredDevicesException::::::");
            caseObj.setFailureType("Environment");
        }
    }
}
