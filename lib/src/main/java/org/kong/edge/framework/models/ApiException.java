package org.kong.edge.framework.models;

public class ApiException extends Exception {
	public ApiException(String message) {
        super(message);
    }
	
	public ApiException() {
        super();
    }
}
