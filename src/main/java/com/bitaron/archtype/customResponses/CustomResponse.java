package com.bitaron.archtype.customResponses;


public  class CustomResponse {
    CustomResponseCode status;
    String message;

    public CustomResponse() {

    }

    public CustomResponse(CustomResponseCode status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomResponseCode getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(CustomResponseCode status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

