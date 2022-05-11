package model;

public class SuccessfulRegResponse {
    public boolean success;
    public DetailsOfReg details;
    public String message;


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
