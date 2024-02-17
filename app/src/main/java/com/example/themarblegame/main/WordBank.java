package com.example.themarblegame.main;

import java.util.Locale;

public class WordBank {
    private static String usersLanguage = "";

    //----------------ALL TEXT ATTRIBUTES----------//

    //mail slot 1 attributes
    public static int mailSlot1Lines = 0;
    public static String mailSlot1Line1 = "";
    public static String mailSlot1Line2 = "";
    public static String mailSlot1Line3 = "";
    public static String mailSlot1Line4 = "";
    public static String mailSlot1Line5 = "";
    public static String mailSlot1Line6 = "";
    public static String mailSlot1Line7 = "";






    //-----------------SET ALL TEXT HERE IN ON CREATE METHOD---------------//

    //init and manual set language
    public static void setUsersLanguage() {
        if (usersLanguage.equals("")) {
            usersLanguage = Locale.getDefault().getLanguage();
        }
    }
    public static void manuallySetUserLanguage(String usersLanguage) {
        WordBank.usersLanguage = usersLanguage;
    }

    //set mail slot 1
    public static void setMailSlot1Lines() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Lines = 1;
                break;
        }
    }
    public static void setMailSlot1Line1() {
        switch (usersLanguage) {
            case "en":
                mailSlot1Line1 = "-Empty-";
                break;
            case "fr":
                mailSlot1Line1 = "-Vider-";
                break;
        }
    }
    public static void setMailSlot1Line2() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Line2 = "";
                break;
        }
    }
    public static void setMailSlot1Line3() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Line2 = "";
                break;
        }
    }
    public static void setMailSlot1Line4() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Line2 = "";
                break;
        }
    }
    public static void setMailSlot1Line5() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Line2 = "";
                break;
        }
    }
    public static void setMailSlot1Line6() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Line2 = "";
                break;
        }
    }
    public static void setMailSlot1Line7() {
        switch (usersLanguage) {
            case "en":
            case "fr":
                mailSlot1Line2 = "";
                break;
        }
    }


    //--------------------OTHER MISCELLANEOUS METHODS GO HERE------------------//
    //for on create and language swaps. will prob req a load screen.
    public static void loadAllText() {
        setUsersLanguage();
        setMailSlot1Lines();
        setMailSlot1Line1();
        setMailSlot1Line2();
        setMailSlot1Line3();
        setMailSlot1Line4();
        setMailSlot1Line5();
        setMailSlot1Line6();
        setMailSlot1Line7();
    }

    //this will prob get del.
    public static String getUsersLanguage() {
        return usersLanguage;
    }
}
