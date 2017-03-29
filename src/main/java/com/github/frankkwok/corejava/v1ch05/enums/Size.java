package com.github.frankkwok.corejava.v1ch05.enums;

/**
 * @author Frank Kwok on 2017/3/29.
 */
enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    private String abbr;

    Size(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }
}
