package de.r007.glabels.jaxb.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import de.r007.glabels.jaxb.elements.labels.AbstractLabel;
import de.r007.glabels.jaxb.elements.labels.Rectangle;

public class TemplateEntry {
	@XmlAttribute
	public String brand;
	@XmlAttribute
	public String part;
	@XmlAttribute(name = "size")
	public String sheetSize;
	@XmlAttribute(name = "_description")
	public String description;
	@XmlElements({
			@XmlElement(name = "Label-rectangle", type = Rectangle.class),
//			@XmlElement(name = "Label-round", type = Round.class),
//			@XmlElement(name = "Label-ellipse", type = Ellipse.class),
//			@XmlElement(name = "Label-cd", type = Cd.class)
	})
	public AbstractLabel label;
}
