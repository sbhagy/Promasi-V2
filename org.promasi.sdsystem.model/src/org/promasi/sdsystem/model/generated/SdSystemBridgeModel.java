//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.15 at 03:20:26 PM CEST 
//


package org.promasi.sdsystem.model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sdSystemBridgeModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sdSystemBridgeModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputSdObject" type="{}sdObjectModel" minOccurs="0"/>
 *         &lt;element name="outputSdObject" type="{}sdObjectModel" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sdSystemBridgeModel", propOrder = {
    "inputSdObject",
    "outputSdObject"
})
public class SdSystemBridgeModel {

    protected SdObjectModel inputSdObject;
    protected SdObjectModel outputSdObject;

    /**
     * Gets the value of the inputSdObject property.
     * 
     * @return
     *     possible object is
     *     {@link SdObjectModel }
     *     
     */
    public SdObjectModel getInputSdObject() {
        return inputSdObject;
    }

    /**
     * Sets the value of the inputSdObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link SdObjectModel }
     *     
     */
    public void setInputSdObject(SdObjectModel value) {
        this.inputSdObject = value;
    }

    /**
     * Gets the value of the outputSdObject property.
     * 
     * @return
     *     possible object is
     *     {@link SdObjectModel }
     *     
     */
    public SdObjectModel getOutputSdObject() {
        return outputSdObject;
    }

    /**
     * Sets the value of the outputSdObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link SdObjectModel }
     *     
     */
    public void setOutputSdObject(SdObjectModel value) {
        this.outputSdObject = value;
    }

}