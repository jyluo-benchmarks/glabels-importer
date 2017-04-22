package de.r007.glabels.jaxb;

import de.r007.glabels.jaxb.elements.Templates;
import de.r007.glabels.jaxb.elements.labels.Rectangle;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;

import static de.r007.glabels.units.DTPUnits.POINT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TemplateTest {
	@Test
	public void parseExampleFile() throws JAXBException {
		InputStream stream = getClass().getResourceAsStream("templates.xml");
		JAXBContext context = JAXBContext.newInstance(Templates.class);
		Templates t = (Templates) context.createUnmarshaller().unmarshal(stream);
		assertThat(t.templates, is(not(empty())));
		assertThat(t.templates.get(0), is(notNullValue()));
		assertThat(t.templates.get(0).brand, is("Test"));
		assertThat(t.templates.get(0).description, is("Ütf8"));
		assertThat(t.templates.get(0).label, is(instanceOf(Rectangle.class)));
		Rectangle r = (Rectangle) t.templates.get(0).label;
		assertThat(r.height.getUnit(), is(POINT));
		assertThat(r.height.getValue().intValue(), is(45));
		assertThat(r.layout, is(notNullValue()));
		assertThat(r.layout.ny, is(7));
		assertThat(r.layout.dy.getValue().doubleValue(), is(49.5));
	}
}
