package com.lagou.mapping;

public enum SqlCommandType {
    UNKNOWN,
    INSERT,
    UPDATE,
    DELETE,
    SELECT,
    FLUSH;

    private SqlCommandType() {
    }
}