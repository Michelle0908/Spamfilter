package dist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Spamfilter {

    private final static String     FILE_ROOT_PATH          = "src/main/files/";
    private final static String     HAM_MAILS_LEARNING      = "ham-anlern/";
    private final static String     SPAM_MAILS_LEARNING     = "spam-anlern/";
    private final static String     HAM_MAILS_CALIBRATION   = "ham-kallibrierung/";
    private final static String     SPAM_MAILS_CALIBRATION  = "spam-kallibrierung/";
    private final static String     HAM_MAILS_TEST          = "ham-test/";
    private final static String     SPAM_MAILS_TEST         = "spam-test/";

    private static File[]           listOfHamLearningFiles;
    private static File[]           listOfSpamLearningFiles;
    private static File[]           listOfHamCalibrationFiles;
    private static File[]           listOfSpamCalibrationFiles;
    private static File[]           listOfHamtestFiles;
    private static File[]           listOfSpamtestFiles;

    private Map<String, Integer>    spamWords               = new HashMap<>();
    private Map<String, Integer>    hamWords                = new HashMap<>();
    private int                     spamCount               = 0;
    private int                     hamCount                = 0;

    public static void main(String[] args) {

        readAllFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        /*
        Test if gets read correctly
         */
        File testFile = new File(FILE_ROOT_PATH + HAM_MAILS_LEARNING + "0126.d002ec3f8a9aff31258bf03d62abdafa");
        String input = readFromTextFile(testFile);
        input = input.replaceAll("<[^>]*>|;", "");
        Set<String> output = split(input);
        System.out.println(output);

    }

    public static void readAllFiles(){
        File folder = new File(FILE_ROOT_PATH   + HAM_MAILS_LEARNING);
        listOfHamLearningFiles = folder.listFiles();

        folder = new File(FILE_ROOT_PATH        + SPAM_MAILS_LEARNING);
        listOfSpamLearningFiles = folder.listFiles();

        folder = new File(FILE_ROOT_PATH        + HAM_MAILS_CALIBRATION);
        listOfHamCalibrationFiles = folder.listFiles();

        folder = new File(FILE_ROOT_PATH        + SPAM_MAILS_CALIBRATION);
        listOfSpamCalibrationFiles = folder.listFiles();

        folder = new File(FILE_ROOT_PATH        + HAM_MAILS_TEST);
        listOfHamtestFiles = folder.listFiles();

        folder = new File(FILE_ROOT_PATH        + SPAM_MAILS_TEST);
        listOfSpamtestFiles = folder.listFiles();
    }

    public static Set<String> split(String str){
        return Stream.of(str.split(" |\n"))
            .map (elem -> new String(elem))
            .collect(Collectors.toSet());
    }

    public static String readFromTextFile(File file){
        try {
            return Files.readString(file.toPath());
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
