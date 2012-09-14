package com.activiti.bpmn.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import de.hpi.bpmn2_0.model.activity.loop.MultiInstanceLoopCharacteristics;

@XmlRootElement(name="multiInstanceLoopCharacteristics", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL")
public class ActivitiMultiInstanceLoopCharacteristics extends MultiInstanceLoopCharacteristics {

    @XmlAttribute(name="collection",namespace="http://activiti.org/bpmn")
    protected String activitiCollection;
    
    @XmlAttribute(name="elementVariable",namespace="http://activiti.org/bpmn")
    protected String activitiElementVariable;

    public String getActivitiCollection() {
        return activitiCollection;
    }

    public void setActivitiCollection(String activitiCollection) {
        this.activitiCollection = activitiCollection;
    }

    public String getActivitiElementVariable() {
        return activitiElementVariable;
    }

    public void setActivitiElementVariable(String activitiElementVariable) {
        this.activitiElementVariable = activitiElementVariable;
    }

    
}
