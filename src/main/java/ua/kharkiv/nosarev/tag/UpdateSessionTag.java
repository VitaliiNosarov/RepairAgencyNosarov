package ua.kharkiv.nosarev.tag;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.MessageType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class UpdateSessionTag extends SimpleTagSupport {

    private static final Logger LOGGER = Logger.getLogger(UpdateSessionTag.class);
    private String attribute;
    private StringWriter sw = new StringWriter();



    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String msg) {
        this.attribute = msg;
    }

    public void doTag() throws JspException, IOException {
        if (attribute != null) {
            /* Use message from attribute */
            JspWriter out = getJspContext().getOut();
            out.println( attribute );
        } else {
            /* use message from the body */
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }


}
