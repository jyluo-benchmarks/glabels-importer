package de.r007.glabels.units;

import static systems.uom.common.Imperial.INCH;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.quantity.Length;
import javax.measure.spi.ServiceProvider;

public final class DTPUnits {
	public static final Unit<Length> POINT = INCH.divide(72);
	public static final Unit<Length> PICA = INCH.divide(6);

	static {
		UnitFormat format = ServiceProvider.current().getUnitFormatService().getUnitFormat();
		format.label(POINT, "pt");
		format.label(PICA, "pc");
	}
}
