package ua.kharkiv.nosarev.tag;

import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class TranslateEnumTag extends TagSupport {

    private ResourceBundle bundle;
    private Locale locale;
    private String value;


    public void setValue(String value) {
        this.value = value;
    }

    public void setLocale(String locale) {
        this.locale = new Locale(UserLocale.valueOf(locale).toString());

    }

    @Override
    public int doStartTag() throws JspException {
        try {
            bundle = ResourceBundle.getBundle("messages", locale);
            String translatedStatus = bundle.getString(value);
            pageContext.getOut().write(translatedStatus);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}

