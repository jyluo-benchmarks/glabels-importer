package de.r007.glabels;

import de.r007.glabels.jaxb.elements.TemplateEntry;
import de.r007.glabels.jaxb.elements.Templates;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class LabelRegistry {
	private Set<TemplateEntry> templates = new HashSet<>();
	private Map<String, TemplateEntry> baseTemplates = new HashMap<>();

	public void addLabelsFromStream(InputStream input) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Templates.class);
		Templates t = (Templates) context.createUnmarshaller().unmarshal(input);
		for (TemplateEntry entry: t.templates) {
			addLabelFromTemplate(entry);
		}
	}

	private void addLabelFromTemplate(TemplateEntry entry) {
		if (!isAlias(entry)) {
			if (entry.label == null) return; // unsupported label type
			baseTemplates.put(getTemplateKey(entry), entry);
			templates.add(entry);
		} else {
			if (baseTemplates.containsKey(getTemplateKey(entry))) {
				templates.add(completeFromBase(entry));
			}
		}
	}

	private TemplateEntry completeFromBase(TemplateEntry entry) {
		TemplateEntry base = baseTemplates.get(getTemplateKey(entry));
		entry.description = base.description;
		entry.label = base.label;
		entry.sheetSize = base.sheetSize;
		return entry;
	}

	private boolean isAlias(TemplateEntry entry) {
		return entry.aliasForPart != null;
	}

	private String getTemplateKey(TemplateEntry entry) {
		String basePart = entry.part;
		if (isAlias(entry)) {
			basePart = entry.aliasForPart;
		}
		return entry.brand + "/" + basePart;
	}

	public Set<TemplateEntry> getAll() {
		return Collections.unmodifiableSet(templates);
	}

	public Set<TemplateEntry> getEntriesByManufacturer(String manufacturer) {
		return templates.stream()
				.filter(entry -> manufacturer.equals(entry.brand))
				.collect(Collectors.toSet());
	}

	public Set<TemplateEntry> getEntriesByProduct(String productName) {
		return templates.stream()
				.filter(entry -> productName.equals(entry.part))
				.collect(Collectors.toSet());
	}

	public Set<TemplateEntry> getEntriesWithLayout(int labelRows, int labelColumns) {
		return templates.stream()
				.filter(entry -> entry.label.layout.nx == labelColumns && entry.label.layout.ny == labelRows)
				.collect(Collectors.toSet());
	}

	public Set<TemplateEntry> getEntriesBySheetSize(String sheetSize) {
		return templates.stream()
				.filter(entry -> sheetSize.equals(entry.sheetSize))
				.collect(Collectors.toSet());
	}
}
