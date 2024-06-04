package Ui.exceptions;

public class WebElementException extends RuntimeException{
    public WebElementException(Exception exception) {

        super(exception);
    }

    public WebElementException(String msg,Object obj) {

        super(msg);
    }

}
