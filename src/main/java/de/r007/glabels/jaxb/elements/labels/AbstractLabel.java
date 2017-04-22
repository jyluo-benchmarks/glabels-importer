package de.r007.glabels.jaxb.elements.labels;

import de.r007.glabels.jaxb.elements.Layout;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public abstract class AbstractLabel {
	@XmlAttribute
	public int id;

	@XmlElement(name = "Layout")
	public Layout layout;
}
