package com.placki.companyresources.utilities;

import com.placki.companyresources.model.Resource;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class XmlGenerator {
    public static void generateXmlFile(List<Resource> resources, String fileName) throws Exception {
        Invoice invoice = new Invoice();
        invoice.setComputers(resources);

        JAXBContext context = JAXBContext.newInstance(Invoice.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Zapis do pliku XML
        marshaller.marshal(invoice, new File(fileName));
    }
}

