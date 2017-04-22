package de.r007.glabels.jaxb.elements;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Glabels-templates")
public class Templates {
	@XmlElement(name = "Template")
	public List<TemplateEntry> templates;
}
