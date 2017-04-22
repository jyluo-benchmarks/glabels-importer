package de.r007.glabels.jaxb.elements;

import de.r007.glabels.jaxb.adapters.UnitAdapter;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Layout {
	@XmlAttribute
	public int nx;

	@XmlAttribute
	public int ny;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> x0;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> y0;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> dx;

	@XmlAttribute
	@XmlJavaTypeAdapter(UnitAdapter.class)
	public Quantity<Length> dy;
}
