package de.r007.glabels;

import de.r007.glabels.jaxb.elements.TemplateEntry;
import de.r007.glabels.jaxb.elements.labels.Rectangle;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

public class RectangularLabelSheet {
	private TemplateEntry template;
	private Rectangle label;

	RectangularLabelSheet(TemplateEntry parsedTemplate) {
		if (!(parsedTemplate.label instanceof Rectangle)) {
			throw new IllegalArgumentException("Cannot make a " + getClass().getName() + " with nonßrectangular labels");
		}
		template = parsedTemplate;
		label = (Rectangle) template.label;
	}

	public int getNumberOfLabels() {
		return getNumberOfRows() * getNumberOfColumns();
	}

	public int getNumberOfRows() {
		return label.layout.nx;
	}

	public int getNumberOfColumns() {
		return label.layout.ny;
	}

	// left-to-right, top-to-bottom
	public Quantity<Length> getTopOfLabel(int labelNumber) {
		return label.layout.y0.add(label.layout.dy.multiply(rowForLabel(labelNumber)));
	}

	private int columnForLabel(int label) {
		return (label % getNumberOfLabels()) % getNumberOfColumns();
	}

	private int rowForLabel(int label) {
		return (label % getNumberOfLabels()) / getNumberOfColumns();
	}

	// left-to-right, top-to-bottom
	public Quantity<Length> getLeftOfLabel(int labelNumber) {
		return label.layout.x0.add(label.layout.dx.multiply(columnForLabel(labelNumber)));
	}

	public Quantity<Length> getLabelWidth() {
		return label.width;
	}

	public Quantity<Length> getLabelHeight() {
		return label.height;
	}
}
