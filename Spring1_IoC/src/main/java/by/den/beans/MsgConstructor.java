package by.den.beans;

public class MsgConstructor implements IMessage {

    String message;

    public MsgConstructor(String message, String second) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
