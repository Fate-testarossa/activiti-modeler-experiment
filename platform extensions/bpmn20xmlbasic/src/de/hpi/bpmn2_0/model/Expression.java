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
package de.hpi.bpmn2_0.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.hpi.bpmn2_0.util.EscapingStringAdapter;
import de.hpi.diagram.SignavioUUID;


/**
 * <p>Java class for tExpression complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tExpression">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/bpmn20}tBaseElementWithMixedContent">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(/*name = "conditionExpression"*/)
@XmlSeeAlso({
    FormalExpression.class
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tExpression", propOrder = {
    "content"
})
public class Expression
    extends BaseElement
{    
    /**
     * Default no-arg constructor
     */
    public Expression() {
        super();
    }
    
    public Expression(String text) {
        super();
        this.setId(SignavioUUID.generate());
        this.getContent().add(text);
    }
    
    @XmlMixed
    @XmlAnyElement(lax = true)
    @XmlJavaTypeAdapter(EscapingStringAdapter.class)
    protected List<String> content;
    
    /**
     * Is used for exporting the expression.
     * 
     * @return Returns a string, or null if there is no expression.
     */
    public String toExportString(){
        return this.getContent().size() == 0? null : this.getContent().get(0);
    }
    
    public List<String> getContent() {
        if (content == null) {
            content = new ArrayList<String>();
        }
        return this.content;
    }

}
