/*******************************************************************************
 * Signavio Core Components
 * Copyright (C) 2012  Signavio GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package de.hpi.bpmn2_0.model.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.hpi.bpmn2_0.transformation.Visitor;

/**
 * <p>
 * Java class for tImplicitThrowEvent complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="tImplicitThrowEvent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/bpmn20}tThrowEvent">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tImplicitThrowEvent")
public class ImplicitThrowEvent extends ThrowEvent {
    /**
     * Default constructor
     */
    public ImplicitThrowEvent() {

    }

    /**
     *
     *
     * @param eventIdentifier
     *            String to identify the appropriate {@link EventDefinition}
     */
    public ImplicitThrowEvent(String eventIdentifier) {
        this.getEventDefinition().add(
                EventDefinition.createEventDefinition(eventIdentifier));
    }

    public void acceptVisitor(Visitor v){
        v.visitImplicitThrowEvent(this);
    }


}
