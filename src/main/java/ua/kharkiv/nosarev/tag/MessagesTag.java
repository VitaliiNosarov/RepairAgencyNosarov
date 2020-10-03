package ua.kharkiv.nosarev.tag;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.MessageType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MessagesTag extends SimpleTagSupport {

    private static final Logger LOGGER = Logger.getLogger(MessagesTag.class);
    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void doTag() throws JspException, IOException {
        if (message != null) {
            JspWriter jspWriter = getJspContext().getOut();
            jspWriter.println(infoMessage(message));
        }
    }

    private String infoMessage(String messageType) {
        MessageType type = MessageType.valueOf(messageType);
        switch (type) {

            case REGISTRATION:
                message = "<fmt:message key=\"success.registration\" />";
                break;
            case UPDATING_ORDER:
                message = "<fmt:message key=\"success.updating_order\" />";
                break;
            case UPDATING_ACCOUNT:
                message = "<fmt:message key=\"success.updating_account\" />";
                break;
            case CREATING_ORDER:
                message = "<fmt:message key=\"success.creating_order\" />";
                break;
            default:
                LOGGER.warn("Wrong argument in " + MessagesTag.class);
        }

        return message;
    }
}
