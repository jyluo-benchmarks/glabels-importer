package de.r007.glabels.jaxb;

import de.r007.glabels.jaxb.adapters.UnitAdapter;
import org.testng.annotations.Test;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.xml.bind.JAXBException;

import static de.r007.glabels.units.DTPUnits.PICA;
import static de.r007.glabels.units.DTPUnits.POINT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static systems.uom.common.Imperial.INCH;
import static tec.uom.se.unit.MetricPrefix.CENTI;
import static tec.uom.se.unit.MetricPrefix.MILLI;
import static tec.uom.se.unit.Units.METRE;

public class UnitAdapterTest {
	@Test
	public void testNumberOnly() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(POINT));
	}
	
	@Test
	public void testNoUnit() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(POINT));
	}

	@Test
	public void testPoint() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0pt");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(POINT));
	}

	@Test
	public void testPica() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0pc");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(PICA));
	}

	@Test
	public void testMillimeter() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0mm");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(MILLI(METRE)));
	}

	@Test
	public void testCentimeter() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0cm");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(CENTI(METRE)));
	}

	@Test
	public void testInch() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0in");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(INCH));
	}

	@Test
	public void testSpacedInch() throws Exception {
		Quantity<Length> parsed = new UnitAdapter().unmarshal("1.0 in");
		assertThat(parsed.getValue().doubleValue(), is(closeTo(1D,1e-7)));
		assertThat(parsed.getUnit(), is(INCH));
	}

	@Test(expectedExceptions = JAXBException.class)
	public void testFailEmpty() throws Exception {
		new UnitAdapter().unmarshal("");
	}

	@Test(expectedExceptions = JAXBException.class)
	public void testFailGarbage() throws Exception {
		new UnitAdapter().unmarshal("garbage");
	}
}
