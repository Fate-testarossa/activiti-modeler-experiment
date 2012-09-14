package com.activiti.bpmn.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import de.hpi.bpmn2_0.model.activity.type.ServiceTask;

@XmlRootElement(name="serviceTask", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL")
public class ActivitiServiceTask extends ServiceTask {

    @XmlAttribute(name="type", namespace="http://activiti.org/bpmn")
    protected QName activitiType;

    
    public QName getActivitiType() {
        return activitiType;
    }

    public void setActivitiType(QName activitiType) {
        this.activitiType = activitiType;
    }



}
