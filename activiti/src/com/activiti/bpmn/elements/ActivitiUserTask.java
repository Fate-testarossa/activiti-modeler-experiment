package com.activiti.bpmn.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import de.hpi.bpmn2_0.model.activity.type.UserTask;

@XmlRootElement(name="userTask", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL")
public class ActivitiUserTask extends UserTask{

    @XmlAttribute(name="assignee", namespace="http://activiti.org/bpmn")
    protected QName activitiAssignee;

    @XmlAttribute(name="candidategroups", namespace="http://activiti.org/bpmn")
    protected QName activitiCandidateGroups;

    
    public QName getActivitiAssignee() {
        return activitiAssignee;
    }

    public void setActivitiAssignee(QName activitiAssignee) {
        this.activitiAssignee = activitiAssignee;
    }

    public QName getActivitiCandidateGroups() {
        return activitiCandidateGroups;
    }

    public void setActivitiCandidateGroups(QName activitiCandidateGroups) {
        this.activitiCandidateGroups = activitiCandidateGroups;
    }
    
}
