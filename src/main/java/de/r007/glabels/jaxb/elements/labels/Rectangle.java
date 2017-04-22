package de.r007.glabels.jaxb.elements.labels;


import de.r007.glabels.jaxb.elements.Layout;
import de.r007.glabels.jaxb.adapters.UnitAdapter;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Rectangle extends AbstractLabel {
	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> width;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> height;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> round;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> waste;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> x_waste;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> y_waste;

	@XmlElement(name = "Layout")
	public Layout layout;
}
