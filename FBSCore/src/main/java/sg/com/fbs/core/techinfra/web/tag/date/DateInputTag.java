package sg.com.fbs.core.techinfra.web.tag.date;

import javax.servlet.jsp.JspException;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import sg.com.fbs.core.techinfra.util.DateUtil;

/**
 * @Author Frank Xu $
 * @Created 3:03:05 pm 1 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * for databinding-aware JSP tags that render HTML form input element. 
 */
public class DateInputTag extends AbstractHtmlInputElementTag{

	private static final long serialVersionUID = 1434905840667389696L;

	public static final String SIZE_ATTRIBUTE = "size";
	
	public static final String MAXLENGHT_ATTRIBUTE = "maxlength";
	
	public static final String ALT_ATTRIBUTE = "alt";
	
	public static final String ONSELECT_ATTRIBUTE = "onselect";
	
	public static final String READONLY_ATTRIBUTE = "readonly";
	
	public static final String AUTOCOMPLETE_ATTRIBUTE = "autocomplete";
	
	public static final String TYPE = "type";
	
	@Setter
	@Getter
	private String size;

	@Setter
	@Getter
	private String maxlength;

	@Setter
	@Getter
	private String alt;

	@Setter
	@Getter
	private String onselect;

	@Setter
	@Getter
	private String autocomplete;
	
	/**
	 * to perform tag content rendering.
	 * @return valid tag render instruction as per {@link javax.servlet.jsp.tagext.Tag#doStartTag()}.
	 */
	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		tagWriter.startTag("input");
		
		writeDefaultAttributes(tagWriter);
		
		if(!hasDynamicTypeAttribute()){
			tagWriter.writeAttribute(TYPE, getType());
		}
		
		writeValue(tagWriter);
		
		//custom optional attribute
		writeOptionalAttribute(tagWriter, SIZE_ATTRIBUTE, getSize());
		writeOptionalAttribute(tagWriter, MAXLENGHT_ATTRIBUTE, getMaxlength());
		writeOptionalAttribute(tagWriter, ALT_ATTRIBUTE, getAlt());
		writeOptionalAttribute(tagWriter, ONSELECT_ATTRIBUTE, getOnselect());
		writeOptionalAttribute(tagWriter, AUTOCOMPLETE_ATTRIBUTE, getAutocomplete());

		tagWriter.endTag();
		
		// release resource
		size = null;
		maxlength = null;
		alt = null;
		onselect = null;
		autocomplete = null;
		
		return SKIP_BODY;
	}

	
	private boolean hasDynamicTypeAttribute(){
		return getDynamicAttributes()!=null && getDynamicAttributes().containsKey(TYPE);
	}
	
	/**
	 * Writes the '<code>value</code>' attribute to the supplied {@link TagWriter}.
	 * Subclasses may choose to override this implementation to control exactly
	 * when the value is written.
	 * @throws JspException 
	 */
	protected void writeValue(TagWriter tagWriter) throws JspException {
		String value ="";
		
		if(getBoundValue() instanceof DateTime){
			value = getDisplayString(DateUtil.convertDateToDateString((DateTime)getBoundValue()), getPropertyEditor());
		}else {
			if(getBoundValue()!=null){
				value = (String)getBoundValue();
				//prevent XSS attack
				if(value!=null){
					if(StringUtils.isNumeric(maxlength)){
						value = StringUtils.left(value, Integer.valueOf(maxlength));
					}
					value = StringEscapeUtils.escapeHtml(value);
				}
			}
		}
		String type = hasDynamicTypeAttribute()?(String)getDynamicAttributes().get(TYPE):getType();
		tagWriter.writeAttribute("value", processFieldValue(getName(), value, type));
	}
	
	@Override
	protected boolean isValidDynamicAttribute(String localName, Object value) {
		if(TYPE.equals(localName)&&("checkbox".equals(value)||"radio".equals(value))){
			return false;
		}
		return true;
	}
	
	/**
	 * Get the value of the '<code>type</code>' attribute. Subclasses
	 * can override this to change the type of '<code>input</code>' element
	 * rendered. Default value is '<code>text</code>'.
	 */
	protected String getType() {
		return "text";
	}
}























