package dist;

import java.util.HashMap;
import java.util.Map;

public class Spamfilter {

    private final static String     FILE_ROOT_PATH          = "src/main/files/";
    private final static String     HAM_MAILS_LEARNING      = "ham-anlern";
    private final static String     SPAM_MAILS_LEARNING     = "spam-anlern";
    private final static String     HAM_MAILS_CALIBRATION   = "ham-kallibrierung";
    private final static String     SPAM_MAILS_CALIBRATION  = "spam-kallibrierung";
    private final static String     HAM_MAILS_TEST          = "ham-test";
    private final static String     SPAM_MAILS_TEST         = "spam-test";

    private Map<String, Integer>    spamWords               = new HashMap<>();
    private Map<String, Integer>    hamWords                = new HashMap<>();
    private int                     spamCount               = 0;
    private int                     hamCount                = 0;

    public static void main(String[] args) {
        System.out.println("Hej Michelle, nice you're here!");
    }

}
