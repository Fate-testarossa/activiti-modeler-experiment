package com.activiti.bpmn.elements;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import de.hpi.bpmn2_0.model.extension.AbstractExtensionElement;

@XmlRootElement(name="formProperty",namespace="http://activiti.org/bpmn")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivitiExtensionFormPropertyElement extends AbstractExtensionElement{

    @XmlAnyAttribute
    private Map<QName, String> extensionAttributes;
    
    /*
     * Constructors 
     */
    public ActivitiExtensionFormPropertyElement() {
        super();
    }
    
    /* Getter & Setter */
    public Map<QName, String> getExtensionAttributes() {
        if(extensionAttributes == null) {
            extensionAttributes = new HashMap<QName, String>();
        }
        return extensionAttributes;
    }

    
}


