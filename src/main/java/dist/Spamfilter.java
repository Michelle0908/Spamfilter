package dist;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Spamfilter {

    private final static String             FILE_ROOT_PATH          = "src/main/files/";
    private final static String             HAM_MAILS_LEARNING      = FILE_ROOT_PATH + "ham-anlern/";
    private final static String             SPAM_MAILS_LEARNING     = FILE_ROOT_PATH + "spam-anlern/";
    private final static String             HAM_MAILS_CALIBRATION   = FILE_ROOT_PATH + "ham-kallibrierung/";
    private final static String             SPAM_MAILS_CALIBRATION  = FILE_ROOT_PATH + "spam-kallibrierung/";
    private final static String             HAM_MAILS_TEST          = FILE_ROOT_PATH + "ham-test/";
    private final static String             SPAM_MAILS_TEST         = FILE_ROOT_PATH + "spam-test/";

    private static File[]                   listOfHamLearningFiles;
    private static File[]                   listOfSpamLearningFiles;
    private static File[]                   listOfHamCalibrationFiles;
    private static File[]                   listOfSpamCalibrationFiles;
    private static File[]                   listOfHamtestFiles;
    private static File[]                   listOfSpamtestFiles;

    private static Map<String, Integer>     spamWords               = new HashMap<>();
    private static Map<String, Integer>     hamWords                = new HashMap<>();
    private static int                      spamMailsCount          = 0;
    private static int                      hamMailsCount           = 0;

    public static void main(String[] args) {

        /***********************************
        Read All Files in the folders and store them in an Array of File Objects
        ************************************/

        readAllFiles();

        /***********************************
        Learning Process:
        Go Through all the Files/Mails in the folder, read them as Strings, chop up the Strings, export all distinct
        words as a Set, look up words and increase the count in the hashmap if the word has already appeared or add them
        to the hashmap and last but not least, increment the right count to keep track on how many files have been
        analysed.
        ************************************/

        spamMailsCount  = learnWordsFromAFolder(listOfSpamLearningFiles,   spamWords);
        hamMailsCount   = learnWordsFromAFolder(listOfHamLearningFiles,    hamWords);

        System.out.println("The End");
        /*
        Test Space
         */
//        File testFile = new File(FILE_ROOT_PATH + HAM_MAILS_LEARNING + "0126.d002ec3f8a9aff31258bf03d62abdafa");
//        String input = readFromTextFile(testFile);
//        input = input.replaceAll("<[^>]*>|;", "");
//        Set<String> output = split(input);
//        System.out.println(output);

//        System.out.println(spamMailsCount);
//        System.out.println(hamMailsCount);
//        System.out.println(spamWords);
//        System.out.println(hamWords);

    }

    public static void readAllFiles(){
        File folder = new File(HAM_MAILS_LEARNING);
        listOfHamLearningFiles = folder.listFiles();

        folder = new File(SPAM_MAILS_LEARNING);
        listOfSpamLearningFiles = folder.listFiles();

        folder = new File(HAM_MAILS_CALIBRATION);
        listOfHamCalibrationFiles = folder.listFiles();

        folder = new File(SPAM_MAILS_CALIBRATION);
        listOfSpamCalibrationFiles = folder.listFiles();

        folder = new File(HAM_MAILS_TEST);
        listOfHamtestFiles = folder.listFiles();

        folder = new File(SPAM_MAILS_TEST);
        listOfSpamtestFiles = folder.listFiles();
    }

    public static int learnWordsFromAFolder(File[] folder, Map<String, Integer> map){
        int Count = 0;
        for (int i = 0; i < folder.length; i++) {
            if (folder[i].isFile()) {
                String s = readFromTextFile(folder[i]);
                if(s != null) {
                    Set<String> set = split(s);
                    for (String element : set) {
                        if (map.containsKey(element)) {
                            map.put(element, map.get(element) + 1);
                        } else {
                            map.put(element, 1);
                        }
                    }
                    Count++;
                }
            }
        }
        return Count;
    }

    public static Set<String> split(String str){
        return Stream.of(str.split(" |\n"))
            .filter(elem -> elem.length() < 15)
            .map (elem -> new String(elem))
            .collect(Collectors.toSet());
    }

    public static String readFromTextFile(File file){
        try {
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
