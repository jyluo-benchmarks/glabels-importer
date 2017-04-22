package de.r007.glabels.units;


import tec.uom.se.format.SimpleUnitFormat;

import javax.measure.Unit;
import javax.measure.quantity.Length;

import static systems.uom.common.Imperial.INCH;

public final class DTPUnits {
	public static final Unit<Length> POINT = INCH.divide(72);
	public static final Unit<Length> PICA = INCH.divide(6);

	static {
		SimpleUnitFormat.getInstance().label(POINT, "pt");
		SimpleUnitFormat.getInstance().label(PICA, "pc");
	}
}
