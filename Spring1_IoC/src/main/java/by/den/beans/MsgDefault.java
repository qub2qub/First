package by.den.beans;

public class MsgDefault implements IMessage {

    String message = "Default String Value";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
