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
package de.hpi.bpmn2_0.model.extension;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;

/**
 * <p>
 * Java class for tExtensionElements complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="tExtensionElements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tExtensionElements"/*, propOrder = { "any" }*/)
public class ExtensionElements {

//    @XmlAnyElement(lax = true)
    @XmlElementRef(type = AbstractExtensionElement.class)
    protected List<AbstractExtensionElement> any;

    @XmlAnyElement
    protected List<Element> anyExternal;

    /**
     * Returns the first element of type {@code elementType}.
     *
     * @param elementType
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractExtensionElement> T getFirstExtensionElementOfType(
            Class<T> elementType) {
        for (AbstractExtensionElement element : getAny()) {
            if (element.getClass().equals(elementType)) {
                return (T) element;
            }
        }

        return null;
    }

    /**
     * Returns the first element of type {@code elementType}.
     *
     * @param elementType
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractExtensionElement> List<T> getAllElementOfType(
            Class<T> elementType) {
        List<T> elements = new ArrayList<T>();
        for (AbstractExtensionElement element : getAny()) {
            if (element.getClass().equals(elementType)) {
                elements.add((T) element);
            }
        }

        return elements;
    }

    /**
     * Add an {@link AbstractExtensionElement} to the extension elements list.
     *
     * @param extensionElement
     */
    public void add(AbstractExtensionElement extensionElement) {
        getAny().add(extensionElement);
    }

    /**
     * Gets the value of the any property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the any property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getAny().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Element }
     * {@link AbstractExtensionElement }
     *
     *
     */
    public List<AbstractExtensionElement> getAny() {
        if (any == null) {
            any = new ArrayList<AbstractExtensionElement>();
        }
        return this.any;
    }

    public List<Element> getAnyExternal() {
        if(anyExternal == null) {
            anyExternal = new ArrayList<Element>();
        }

        return anyExternal;
    }



}
