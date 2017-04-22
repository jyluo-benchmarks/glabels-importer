package de.r007.glabels.units;

import org.testng.annotations.Test;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.spi.QuantityFactory;
import javax.measure.spi.ServiceProvider;

import static de.r007.glabels.units.DTPUnits.PICA;
import static de.r007.glabels.units.DTPUnits.POINT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static systems.uom.common.Imperial.INCH;
import static tec.uom.se.unit.MetricPrefix.CENTI;
import static tec.uom.se.unit.Units.METRE;

public class DTPUnitTest {
	QuantityFactory<Length> factory = ServiceProvider.current().getQuantityFactory(Length.class);

	@Test
	public void inchDefinition() {
		// since the adoption of the international yard during the 1950s and 1960s
		// it has been based on the metric system and defined as exactly 2.54 cm.
		// -- https://en.wikipedia.org/wiki/Inch
		Quantity<Length> inch = factory.create(1L, INCH);
		double mm = inch.to(CENTI(METRE)).getValue().doubleValue();
		assertThat(mm, is(equalTo(2.54)));
	}

	@Test
	public void pointDefinition() {
		Quantity<Length> points = factory.create(72, POINT);
		double inches = points.to(INCH).getValue().doubleValue();
		assertThat(inches, is(closeTo(1D, 0.0000001D)));
	}

	@Test
	public void picaDefinition() {
		Quantity<Length> points = factory.create(6, PICA);
		double inches = points.to(INCH).getValue().doubleValue();
		assertThat(inches, is(closeTo(1D, 0.0000001D)));
	}
}
