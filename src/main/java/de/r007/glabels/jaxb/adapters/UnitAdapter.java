package de.r007.glabels.jaxb.adapters;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.quantity.Length;
import javax.measure.spi.QuantityFactory;
import javax.measure.spi.ServiceProvider;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.r007.glabels.units.DTPUnits.POINT;

public class UnitAdapter extends XmlAdapter<String, Quantity<Length>> {
	QuantityFactory<Length> factory = ServiceProvider.current().getQuantityFactory(Length.class);
	UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat();
	Pattern pattern = Pattern.compile("^(?<value>\\d+(\\.\\d*)?)?\\s*(?<unit>\\S*)$");

	public Quantity<Length> unmarshal(String v) throws Exception {
		Matcher parsed = pattern.matcher(v);
		if (!parsed.matches() || parsed.group("value") == null) {
			throw new JAXBException(v + " is not a correctly formatted length");
		}
		double value = Double.parseDouble(parsed.group("value"));
		Unit<Length> unit = POINT;
		if (parsed.group("unit") != null) {
			Unit<?> parsedUnit = unitFormat.parse(parsed.group("unit"));
			if (parsedUnit.isCompatible(POINT)) {
				unit = (Unit<Length>)parsedUnit;
			}
		}
		return factory.create(value, unit);
	}

	public String marshal(Quantity<Length> v) throws Exception {
		return v.getValue().toString() + unitFormat.format(v.getUnit());
	}
}
