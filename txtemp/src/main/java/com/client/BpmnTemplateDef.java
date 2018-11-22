
package com.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bpmnTemplateDef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bpmnTemplateDef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canvasHeight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canvasWidth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="contentBytesStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createTimeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deployState" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="deployStateStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deploymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="initNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modifyBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="modifyTimeStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tableIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tableNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="versionState" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="versionStateStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bpmnTemplateDef", propOrder = {
    "canvasHeight",
    "canvasWidth",
    "category",
    "contentBytes",
    "contentBytesStr",
    "createBy",
    "createTime",
    "createTimeStr",
    "deployState",
    "deployStateStr",
    "deploymentId",
    "id",
    "initNum",
    "modifyBy",
    "modifyTime",
    "modifyTimeStr",
    "name",
    "tableIds",
    "tableNames",
    "version",
    "versionState",
    "versionStateStr"
})
public class BpmnTemplateDef {

    protected String canvasHeight;
    protected String canvasWidth;
    protected String category;
    protected byte[] contentBytes;
    protected String contentBytesStr;
    protected String createBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createTimeStr;
    protected Integer deployState;
    protected String deployStateStr;
    protected String deploymentId;
    protected String id;
    protected Integer initNum;
    protected String modifyBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyTime;
    protected String modifyTimeStr;
    protected String name;
    protected String tableIds;
    protected String tableNames;
    protected Integer version;
    protected Integer versionState;
    protected String versionStateStr;

    /**
     * Gets the value of the canvasHeight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanvasHeight() {
        return canvasHeight;
    }

    /**
     * Sets the value of the canvasHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanvasHeight(String value) {
        this.canvasHeight = value;
    }

    /**
     * Gets the value of the canvasWidth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanvasWidth() {
        return canvasWidth;
    }

    /**
     * Sets the value of the canvasWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanvasWidth(String value) {
        this.canvasWidth = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the contentBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContentBytes() {
        return contentBytes;
    }

    /**
     * Sets the value of the contentBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContentBytes(byte[] value) {
        this.contentBytes = ((byte[]) value);
    }

    /**
     * Gets the value of the contentBytesStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentBytesStr() {
        return contentBytesStr;
    }

    /**
     * Sets the value of the contentBytesStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentBytesStr(String value) {
        this.contentBytesStr = value;
    }

    /**
     * Gets the value of the createBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Sets the value of the createBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateBy(String value) {
        this.createBy = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the createTimeStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateTimeStr() {
        return createTimeStr;
    }

    /**
     * Sets the value of the createTimeStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateTimeStr(String value) {
        this.createTimeStr = value;
    }

    /**
     * Gets the value of the deployState property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeployState() {
        return deployState;
    }

    /**
     * Sets the value of the deployState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeployState(Integer value) {
        this.deployState = value;
    }

    /**
     * Gets the value of the deployStateStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployStateStr() {
        return deployStateStr;
    }

    /**
     * Sets the value of the deployStateStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployStateStr(String value) {
        this.deployStateStr = value;
    }

    /**
     * Gets the value of the deploymentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeploymentId() {
        return deploymentId;
    }

    /**
     * Sets the value of the deploymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeploymentId(String value) {
        this.deploymentId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the initNum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInitNum() {
        return initNum;
    }

    /**
     * Sets the value of the initNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInitNum(Integer value) {
        this.initNum = value;
    }

    /**
     * Gets the value of the modifyBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyBy() {
        return modifyBy;
    }

    /**
     * Sets the value of the modifyBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyBy(String value) {
        this.modifyBy = value;
    }

    /**
     * Gets the value of the modifyTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifyTime() {
        return modifyTime;
    }

    /**
     * Sets the value of the modifyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifyTime(XMLGregorianCalendar value) {
        this.modifyTime = value;
    }

    /**
     * Gets the value of the modifyTimeStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyTimeStr() {
        return modifyTimeStr;
    }

    /**
     * Sets the value of the modifyTimeStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyTimeStr(String value) {
        this.modifyTimeStr = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the tableIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableIds() {
        return tableIds;
    }

    /**
     * Sets the value of the tableIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableIds(String value) {
        this.tableIds = value;
    }

    /**
     * Gets the value of the tableNames property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableNames() {
        return tableNames;
    }

    /**
     * Sets the value of the tableNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableNames(String value) {
        this.tableNames = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the versionState property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersionState() {
        return versionState;
    }

    /**
     * Sets the value of the versionState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersionState(Integer value) {
        this.versionState = value;
    }

    /**
     * Gets the value of the versionStateStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionStateStr() {
        return versionStateStr;
    }

    /**
     * Sets the value of the versionStateStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionStateStr(String value) {
        this.versionStateStr = value;
    }

}
