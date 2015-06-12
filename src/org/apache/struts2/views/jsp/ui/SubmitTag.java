package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Submit;

import com.cjlu.newspublish.utils.ValidateUtils;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Submit
 */
public class SubmitTag extends AbstractClosingTag {

	private static final long serialVersionUID = 1L;
	
	protected String action;
    protected String method;
    protected String align;
    protected String type;
    protected String src;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Submit(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Submit submit = ((Submit) component);
        submit.setAction(action);
        submit.setMethod(method);
        submit.setAlign(align);
        submit.setType(type);
        submit.setSrc(src);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int doStartTag() throws JspException {
    	return hasRight()?super.doStartTag() : SKIP_BODY;
    }
    
	public int doEndTag() throws JspException {
		return hasRight()?super.doEndTag() : SKIP_BODY;
	}
	
	private boolean hasRight(){
		String ns = getFormNamespace();
		String actionName = getValidActionName();
		return ValidateUtils.hasRight(ns, actionName, (HttpServletRequest)pageContext.getRequest(), null);
	}
	
	/**
	 * 获得所在表单的名字空间
	 */
	private String getFormNamespace(){
		//获得上级标签
		Tag ptag = this.getParent();
		while(ptag != null){
			if(ptag instanceof FormTag){
				return ((FormTag)ptag).namespace ;
			}
			else{
				ptag = ptag.getParent();
			}
		}
		return null ;
	}
	
	/**
	 * 获得所在表单的名字空间
	 */
	private String getValidActionName(){
		if(ValidateUtils.isValid(action)){
			return action ;
		}
		//获得上级标签
		Tag ptag = this.getParent();
		while(ptag != null){
			if(ptag instanceof FormTag){
				return ((FormTag)ptag).action ;
			}
			else{
				ptag = ptag.getParent();
			}
		}
		return null ;
	}
}
