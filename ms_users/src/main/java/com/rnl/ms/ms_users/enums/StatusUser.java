package com.rnl.ms.ms_users.enums;

/**
 * Enum for status of user.
 * @author RNL
 * @link https://www.baeldung.com/jpa-persisting-enums-in-jpa
 */
public enum StatusUser {
    PENDING("PND"), 
    APPROVED("APR"), 
    DISABLED("DIS"), 
    BLACKLIST("BLK");

    private String code;

    private StatusUser(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
