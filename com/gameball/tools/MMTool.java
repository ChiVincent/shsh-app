package com.gameball.tools;

public class MMTool {
    public static String slashToHtml(String string) {
        return string.replace("\r\n", "<br />").replace("\n\r", "<br />").replace("\r", "<br />").replace("\n", "<br />");
    }
}
