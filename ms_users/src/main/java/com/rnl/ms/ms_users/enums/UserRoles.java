package com.rnl.ms.ms_users.enums;

/**
 * Enum for User roles
 */
public enum UserRoles {
    USER("USR"), 
    MODERATOR("MOD"),
    ADMIN("ADM");
    
    private String code;

    private UserRoles(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
