package de.hpi.bpmn2_0.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.activiti.bpmn.elements.ActivitiExtensionFieldElement;
import com.activiti.bpmn.elements.ActivitiExtensionFormPropertyElement;

import de.hpi.bpmn2_0.model.extension.signavio.SignavioLabel;
import de.hpi.bpmn2_0.model.extension.signavio.SignavioMessageName;
import de.hpi.bpmn2_0.model.extension.signavio.SignavioMetaData;
import de.hpi.bpmn2_0.model.extension.signavio.SignavioType;

/**
 * Abstract BPMN 2.0 extension element
 * 
 * @author Sven Wagner-Boysen
 * @author dryabkov d.v.ryabkov@gmail.com
 * 
 */
@XmlSeeAlso({
	SignavioMetaData.class,
	SignavioType.class,
	SignavioLabel.class,
	SignavioMessageName.class,
    ActivitiExtensionFieldElement.class,
	ActivitiExtensionFormPropertyElement.class
})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractExtensionElement {
	
}
