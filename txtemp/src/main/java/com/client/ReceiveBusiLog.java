
package com.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for receiveBusiLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="receiveBusiLog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="busiLog" type="{http://webservice.bpmn.hq.com/}actBusiLogDomain" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveBusiLog", propOrder = {
    "busiLog"
})
public class ReceiveBusiLog {

    protected List<ActBusiLogDomain> busiLog;

    /**
     * Gets the value of the busiLog property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busiLog property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusiLog().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActBusiLogDomain }
     * 
     * 
     */
    public List<ActBusiLogDomain> getBusiLog() {
        if (busiLog == null) {
            busiLog = new ArrayList<ActBusiLogDomain>();
        }
        return this.busiLog;
    }

}
