// Model XML
package com.placki.companyresources.utilities;

import com.placki.companyresources.model.Resource;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "invoice")
public class Invoice {

    private List<Resource> resources;

    @XmlElement(name = "resource")
    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}