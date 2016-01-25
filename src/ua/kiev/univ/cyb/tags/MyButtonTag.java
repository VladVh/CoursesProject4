package ua.kiev.univ.cyb.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Custom tag class. Represents form with a single button. Gives opportunity
 * to set button class, id as a usual button, servlet to communicate with
 * and what command to execute on click.
 */
public class MyButtonTag extends BodyTagSupport {
    /**
     * Represents html class attribute
     */
    private String styleClass;

    /**
     * Represents html id attribute
     */
    private String styleId;

    /**
     * Represents servlet mapping name.
     */
    private String action;

    /**
     * Represents name of command to execute.
     */
    private String command;

    /**
     * Represents id of a particular object
     */
    private Integer objId;

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        StringBuilder stringBuilder = new StringBuilder("<form action=\"");
        stringBuilder.append(action).append("\" method=\"post\">\n")
                .append("\t<input type=\"hidden\" name=\"command\" value=\"").append(command).append("\">\n")
                .append("<input type=\"hidden\" name=\"id\" value=\"").append(objId).append("\">\n")
                .append("<button type=\"submit\" class=\"").append(styleClass).append("\" id=\"").append(styleId).append("\">\n");

        try {
            out.write(stringBuilder.toString());
        } catch (IOException e) {
            Logger.getLogger(getClass()).error(e);
        }
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        BodyContent bodyContent = getBodyContent();
        String bodyString = bodyContent.getString();
        JspWriter out = bodyContent.getEnclosingWriter();

        try {
            out.print(bodyString);
            out.print("</button>\n</form>");
        } catch (IOException e) {
            Logger.getLogger(getClass()).error(e);
        }
        return SKIP_BODY;
    }
}
