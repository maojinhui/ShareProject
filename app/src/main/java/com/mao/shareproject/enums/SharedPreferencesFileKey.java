package com.mao.shareproject.enums;

public enum SharedPreferencesFileKey {

    FIRST_ENTRY("first_entry");

    private String fileName;

    SharedPreferencesFileKey(String fileName) {
        this.fileName=fileName;
    }

    public String getFileName() {
        return fileName;
    }


}
