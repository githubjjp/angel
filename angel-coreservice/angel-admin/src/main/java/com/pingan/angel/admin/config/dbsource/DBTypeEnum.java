package com.pingan.angel.admin.config.dbsource;

public enum DBTypeEnum {
		mysql("mysql"),
		mongodb("mongodb");
	    private String value;

	    DBTypeEnum(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	
}
