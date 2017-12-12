package com.botscrew.facebook.model.outgoing.generic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {
	@JsonProperty("template_type")
	private String templateType;
	private List<MessageElement> elements;
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public List<MessageElement> getElements() {
		return elements;
	}
	public void setElements(List<MessageElement> elements) {
		this.elements = elements;
	}
	@Override
	public String toString() {
		return "Payload [templateType=" + templateType +  ", elements=" + elements + "]";
	}
	
}
