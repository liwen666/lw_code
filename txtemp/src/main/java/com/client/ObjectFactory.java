
package com.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InitBpmnTemplateDef_QNAME = new QName("http://webservice.bpmn.hq.com/", "initBpmnTemplateDef");
    private final static QName _UserIdResponse_QNAME = new QName("http://webservice.bpmn.hq.com/", "userIdResponse");
    private final static QName _ReceiveBusiLog_QNAME = new QName("http://webservice.bpmn.hq.com/", "receiveBusiLog");
    private final static QName _InitBpmnTemplateDefResponse_QNAME = new QName("http://webservice.bpmn.hq.com/", "initBpmnTemplateDefResponse");
    private final static QName _ReceiveBusiLogResponse_QNAME = new QName("http://webservice.bpmn.hq.com/", "receiveBusiLogResponse");
    private final static QName _UserId_QNAME = new QName("http://webservice.bpmn.hq.com/", "userId");
    private final static QName _Model_QNAME = new QName("http://webservice.bpmn.hq.com/", "Model");
    private final static QName _InitBpmnData_QNAME = new QName("http://webservice.bpmn.hq.com/", "initBpmnData");
    private final static QName _InitBpmnDataResponse_QNAME = new QName("http://webservice.bpmn.hq.com/", "initBpmnDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InitBpmnTemplateDefResponse }
     * 
     */
    public InitBpmnTemplateDefResponse createInitBpmnTemplateDefResponse() {
        return new InitBpmnTemplateDefResponse();
    }

    /**
     * Create an instance of {@link InitBpmnDataResponse }
     * 
     */
    public InitBpmnDataResponse createInitBpmnDataResponse() {
        return new InitBpmnDataResponse();
    }

    /**
     * Create an instance of {@link BpmnTemplateDef }
     * 
     */
    public BpmnTemplateDef createBpmnTemplateDef() {
        return new BpmnTemplateDef();
    }

    /**
     * Create an instance of {@link ReceiveBusiLogResponse }
     * 
     */
    public ReceiveBusiLogResponse createReceiveBusiLogResponse() {
        return new ReceiveBusiLogResponse();
    }

    /**
     * Create an instance of {@link UserIdResponse }
     * 
     */
    public UserIdResponse createUserIdResponse() {
        return new UserIdResponse();
    }

    /**
     * Create an instance of {@link InitBpmnData }
     * 
     */
    public InitBpmnData createInitBpmnData() {
        return new InitBpmnData();
    }

    /**
     * Create an instance of {@link ActBusiLogDomain }
     * 
     */
    public ActBusiLogDomain createActBusiLogDomain() {
        return new ActBusiLogDomain();
    }

    /**
     * Create an instance of {@link ReceiveBusiLog }
     * 
     */
    public ReceiveBusiLog createReceiveBusiLog() {
        return new ReceiveBusiLog();
    }

    /**
     * Create an instance of {@link UserId }
     * 
     */
    public UserId createUserId() {
        return new UserId();
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

    /**
     * Create an instance of {@link InitBpmnTemplateDef }
     * 
     */
    public InitBpmnTemplateDef createInitBpmnTemplateDef() {
        return new InitBpmnTemplateDef();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitBpmnTemplateDef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "initBpmnTemplateDef")
    public JAXBElement<InitBpmnTemplateDef> createInitBpmnTemplateDef(InitBpmnTemplateDef value) {
        return new JAXBElement<InitBpmnTemplateDef>(_InitBpmnTemplateDef_QNAME, InitBpmnTemplateDef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "userIdResponse")
    public JAXBElement<UserIdResponse> createUserIdResponse(UserIdResponse value) {
        return new JAXBElement<UserIdResponse>(_UserIdResponse_QNAME, UserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveBusiLog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "receiveBusiLog")
    public JAXBElement<ReceiveBusiLog> createReceiveBusiLog(ReceiveBusiLog value) {
        return new JAXBElement<ReceiveBusiLog>(_ReceiveBusiLog_QNAME, ReceiveBusiLog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitBpmnTemplateDefResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "initBpmnTemplateDefResponse")
    public JAXBElement<InitBpmnTemplateDefResponse> createInitBpmnTemplateDefResponse(InitBpmnTemplateDefResponse value) {
        return new JAXBElement<InitBpmnTemplateDefResponse>(_InitBpmnTemplateDefResponse_QNAME, InitBpmnTemplateDefResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveBusiLogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "receiveBusiLogResponse")
    public JAXBElement<ReceiveBusiLogResponse> createReceiveBusiLogResponse(ReceiveBusiLogResponse value) {
        return new JAXBElement<ReceiveBusiLogResponse>(_ReceiveBusiLogResponse_QNAME, ReceiveBusiLogResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "userId")
    public JAXBElement<UserId> createUserId(UserId value) {
        return new JAXBElement<UserId>(_UserId_QNAME, UserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Model }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "Model")
    public JAXBElement<Model> createModel(Model value) {
        return new JAXBElement<Model>(_Model_QNAME, Model.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitBpmnData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "initBpmnData")
    public JAXBElement<InitBpmnData> createInitBpmnData(InitBpmnData value) {
        return new JAXBElement<InitBpmnData>(_InitBpmnData_QNAME, InitBpmnData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitBpmnDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bpmn.hq.com/", name = "initBpmnDataResponse")
    public JAXBElement<InitBpmnDataResponse> createInitBpmnDataResponse(InitBpmnDataResponse value) {
        return new JAXBElement<InitBpmnDataResponse>(_InitBpmnDataResponse_QNAME, InitBpmnDataResponse.class, null, value);
    }

}
