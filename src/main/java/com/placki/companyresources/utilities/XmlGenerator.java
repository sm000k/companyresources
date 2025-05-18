package com.placki.companyresources.utilities;

import com.placki.companyresources.model.Resource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.List;

public class XmlGenerator {

    private XmlGenerator() {
        // Utility class, no instantiation allowed
    }

    /**
     * Generates an XML file for the given list of resources.
     *
     * @param resources List of resources to include in the XML.
     * @param filePath  Path where the XML file will be saved.
     * @throws Exception If an error occurs during XML generation.
     */
    public static void generateXmlFile(List<Resource> resources, String filePath) throws Exception {
        Invoice invoice = new Invoice();
        invoice.setResources(resources);

        JAXBContext context = JAXBContext.newInstance(Invoice.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write the XML to the specified file
        marshaller.marshal(invoice, new File(filePath));
    }
}

