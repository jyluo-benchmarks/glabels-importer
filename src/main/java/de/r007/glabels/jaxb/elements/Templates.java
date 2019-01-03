package de.r007.glabels.jaxb.elements;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Glabels-templates")
public class Templates {
	@XmlElement(name = "Template")
	public List<TemplateEntry> templates;
}
