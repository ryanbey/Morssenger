package com.goodfellows.morssenger;

public class Translator {

    // Takes a morse code message and translates it, then returns a string
    public String ConvertToEnglish(String morseMessage) {

        int size = morseMessage.length();       // find length for loop
        String message = "";                    // translated morse added here
        String morseChar = "";                  // to compare each morse character

        for(int i = 0; i < size; i++)
        {
            // Check for spaces and add the correct character to the message then clear morseChar
            if(morseMessage.charAt(i) == ' '){
                message += checkMorse(morseChar);
                morseChar = "";
            }
            // Add characters to morseChar from morseMessage
            else {
                morseChar += morseMessage.charAt(i);
            }
        }

        // Loop ends one cycle early so do one more check
        message += checkMorse(morseChar);
        return message;
    }

    // Compares the morseChar to the morse table and returns corresponding alpha character
    private String checkMorse(String morseChar) {
        switch (morseChar) {
            case ".-":
                return "a";
            case "-...":
                    return "b";
            case "-.-.":
                    return "c";
            case "-..":
                    return "d";
            case ".":
                    return "e";
            case "..-.":
                    return "f";
            case "--.":
                    return "g";
            case "....":
                    return "h";
            case "..":
                    return "i";
            case ".---":
                    return "j";
            case "-.-":
                    return "k";
            case ".-..":
                    return "l";
            case "--":
                    return "m";
            case "-.":
                    return "n";
            case "---":
                    return "o";
            case ".--.":
                    return "p";
            case "--.-":
                    return "q";
            case ".-.":
                    return "r";
            case "...":
                    return "s";
            case "-":
                    return "t";
            case "..-":
                    return "u";
            case "...-":
                    return "v";
            case ".--":
                    return "w";
            case "-..-":
                    return "x";
            case "-.--":
                    return "y";
            case "--..":
                    return "z";
            case "/":
                    return " ";
            default:
                    //throw new IllegalStateException("Unexpected value: " + morseChar);
                    return "?";
        }
    }


    // Takes an english message and converts to morse, then returns a string morse message
    public String ConvertToMorse(String english){

        String result = "";                 // the result of the code
        english= english.toLowerCase();     // make sure it is all in lowercase
        int length = english.length();      // get the length for the loop

        // loop through
        for(int i = 0; i < length; i++) {
            result += checkEnglish(english.charAt(i));
        }

        return result;
    }

    // Compares the englishChar to the morse table and returns corresponding alpha character
    private String checkEnglish(char englishChar) {
        switch (englishChar) {
            case 'a':
                return ".- ";
            case 'b':
                return "-... ";
            case 'c':
                return "-.-. ";
            case 'd':
                return "-.. ";
            case 'e':
                return ". ";
            case 'f':
                return "..-. ";
            case 'g':
                return "--. ";
            case 'h':
                return ".... ";
            case 'i':
                return ".. ";
            case 'j':
                return ".--- ";
            case 'k':
                return "-.- ";
            case 'l':
                return ".-.. ";
            case 'm':
                return "-- ";
            case 'n':
                return "-. ";
            case 'o':
                return "--- ";
            case 'p':
                return ".--. ";
            case 'q':
                return "--.- ";
            case 'r':
                return ".-. ";
            case 's':
                return "... ";
            case 't':
                return "- ";
            case 'u':
                return "..- ";
            case 'v':
                return "...- ";
            case 'w':
                return ".-- ";
            case 'x':
                return "-..- ";
            case 'y':
                return "-.-- ";
            case 'z':
                return "--.. ";
            case ' ':
                return "/ ";
            default:
                throw new IllegalStateException("Unexpected value: " + englishChar);
        }
    }
}
