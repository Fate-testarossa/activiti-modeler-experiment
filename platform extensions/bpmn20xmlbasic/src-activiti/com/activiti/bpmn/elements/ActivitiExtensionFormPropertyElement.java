package com.activiti.bpmn.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import de.hpi.bpmn2_0.model.extension.AbstractExtensionElement;

@XmlRootElement(name="formProperty",namespace="http://activiti.org/bpmn")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivitiExtensionFormPropertyElement extends AbstractExtensionElement implements Comparable<ActivitiExtensionFormPropertyElement>{

    @XmlAnyAttribute
    private Map<QName, String> extensionAttributes;
    
    @XmlElement(name="value",namespace="http://activiti.org/bpmn")
    private List<ActivitiExtensionFormPropertyValueElement> values;        
    
    @XmlTransient
    private String porder;

    @XmlAttribute(name="datePattern")
    private String datePattern;
    
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

    public List<ActivitiExtensionFormPropertyValueElement> getValues() {
        if (values==null) {
            values =  new ArrayList<ActivitiExtensionFormPropertyValueElement>(); 
        }
        return values;
    }

    public String getPorder() {
        return porder;
    }

    public void setPorder(String porder) {
        this.porder = porder;
    }

    @Override
    public int compareTo(ActivitiExtensionFormPropertyElement o) {
        if (porder==null || o.porder==null || o.porder.equals(porder)) {
            throw new RuntimeException("porder can't be empty or non uniq");
        }
        
        return porder.compareTo(o.porder);
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}


