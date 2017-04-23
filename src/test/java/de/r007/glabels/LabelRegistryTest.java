package de.r007.glabels;

import de.r007.glabels.jaxb.elements.TemplateEntry;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LabelRegistryTest {
	@Test
	public void loadWithAliases() throws JAXBException {
		LabelRegistry registry = new LabelRegistry();
		registry.addLabelsFromStream(getClass().getResourceAsStream("avery-iso-templates.xml"));
		for (TemplateEntry entry : registry.getAll()) {
			System.out.print(entry.brand + " " + entry.part);
			if (entry.aliasForPart != null) {
				System.out.print(" (equivalent to " + entry.aliasForPart + ")");
			}
			System.out.println();
		}
		assertThat(registry.getAll().size(), is(61)); // only rectangles, counting aliases, manually from file
	}

	@Test
	public void filterByProduct() throws JAXBException {
		LabelRegistry registry = new LabelRegistry();
		registry.addLabelsFromStream(getClass().getResourceAsStream("avery-iso-templates.xml"));
		Set<TemplateEntry> entries = registry.getEntriesByProduct("38931");
		assertThat(entries.size(), is(1));
	}

	@Test
	public void filterByLayout() throws JAXBException {
		LabelRegistry registry = new LabelRegistry();
		registry.addLabelsFromStream(getClass().getResourceAsStream("avery-iso-templates.xml"));
		Set<TemplateEntry> entries = registry.getEntriesWithLayout(10, 4);
		assertThat(entries.size(), is(5)); // L4770 family
	}

}