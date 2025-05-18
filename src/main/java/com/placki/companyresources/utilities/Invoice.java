// Model XML
package com.placki.companyresources.utilities;

import com.placki.companyresources.model.Resource;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "faktura")
public class Invoice {
    private List<Resource> computers;

    @XmlElement(name = "komputer")
    public List<Resource> getComputers() {
        return computers;
    }

    public void setComputers(List<Resource> computers) {
        this.computers = computers;
    }
}