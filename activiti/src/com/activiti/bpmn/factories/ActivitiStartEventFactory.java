package com.activiti.bpmn.factories;

import javax.xml.namespace.QName;

import org.oryxeditor.server.diagram.generic.GenericShape;

import com.activiti.bpmn.elements.ActivitiStartEvent;

import de.hpi.bpmn2_0.annotations.StencilId;
import de.hpi.bpmn2_0.factory.node.StartEventFactory;

@StencilId({
    "StartNoneEvent",
    "StartTimerEvent",
    "StartEscalationEvent",
    "StartConditionalEvent",
    "StartErrorEvent",
    "StartCompensationEvent",
    "StartSignalEvent",
    "StartMultipleEvent",
    "StartParallelMultipleEvent",
    "StartMessageEvent"
})
public class ActivitiStartEventFactory extends StartEventFactory {


    @Override
    @StencilId("StartNoneEvent")
    public ActivitiStartEvent createStartNoneEvent(GenericShape shape) {
        ActivitiStartEvent event = new ActivitiStartEvent();
        
        String activitFormProperty = shape.getProperty("activiti:formproperty");
        if (activitFormProperty!=null && !activitFormProperty.isEmpty() ) {
             FormPropertiesUtil.processFormProperties(event, activitFormProperty);    
        }   

        String activitInitiatorProperty = shape.getProperty("activiti:initiator");
        if (activitInitiatorProperty!=null && !activitInitiatorProperty.isEmpty() ) {
            event.setActivitiInitiator(new QName(activitInitiatorProperty));
       }   

        return event;
    }
}
