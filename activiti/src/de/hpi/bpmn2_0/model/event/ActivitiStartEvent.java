package de.hpi.bpmn2_0.model.event;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

@XmlRootElement(name="startEvent", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL")
public class ActivitiStartEvent extends StartEvent{

    @XmlAttribute(name="initiator", namespace="http://activiti.org/bpmn")
    protected QName activitiInitiator;

    public QName getActivitiInitiator() {
        return activitiInitiator;
    }

    public void setActivitiInitiator(QName activitiInitiator) {
        this.activitiInitiator = activitiInitiator;
    }
    
    
}


