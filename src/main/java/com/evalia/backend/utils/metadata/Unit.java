package com.evalia.backend.utils.metadata;

public enum Unit implements Enumeration {

    TND("TND"),
    STARS("Stars"),
    JOBS("Number of jobs");

    private final String alias;

    private Unit(String alias) {
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

}
