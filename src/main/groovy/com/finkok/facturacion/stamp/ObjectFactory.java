
package com.finkok.facturacion.stamp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import views.core.soap.services.apps.AcuseRecepcionCFDI;
import views.core.soap.services.apps.QueryPendingResult;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.finkok.facturacion.stamp package. 
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

    private final static QName _StampedResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampedResponse");
    private final static QName _StampResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampResponse");
    private final static QName _QuickStampResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "quick_stampResponse");
    private final static QName _Stamp_QNAME = new QName("http://facturacion.finkok.com/stamp", "stamp");
    private final static QName _Stamped_QNAME = new QName("http://facturacion.finkok.com/stamp", "stamped");
    private final static QName _QuickStamp_QNAME = new QName("http://facturacion.finkok.com/stamp", "quick_stamp");
    private final static QName _QueryPending_QNAME = new QName("http://facturacion.finkok.com/stamp", "query_pending");
    private final static QName _QueryPendingResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "query_pendingResponse");
    private final static QName _StampedResponseStampedResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampedResult");
    private final static QName _QueryPendingResponseQueryPendingResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "query_pendingResult");
    private final static QName _StampUsername_QNAME = new QName("http://facturacion.finkok.com/stamp", "username");
    private final static QName _StampXml_QNAME = new QName("http://facturacion.finkok.com/stamp", "xml");
    private final static QName _StampPassword_QNAME = new QName("http://facturacion.finkok.com/stamp", "password");
    private final static QName _StampResponseStampResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampResult");
    private final static QName _QueryPendingUuid_QNAME = new QName("http://facturacion.finkok.com/stamp", "uuid");
    private final static QName _QuickStampResponseQuickStampResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "quick_stampResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.finkok.facturacion.stamp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QuickStampResponse }
     * 
     */
    public QuickStampResponse createQuickStampResponse() {
        return new QuickStampResponse();
    }

    /**
     * Create an instance of {@link Stamp }
     * 
     */
    public Stamp createStamp() {
        return new Stamp();
    }

    /**
     * Create an instance of {@link StampResponse }
     * 
     */
    public StampResponse createStampResponse() {
        return new StampResponse();
    }

    /**
     * Create an instance of {@link StampedResponse }
     * 
     */
    public StampedResponse createStampedResponse() {
        return new StampedResponse();
    }

    /**
     * Create an instance of {@link Stamped }
     * 
     */
    public Stamped createStamped() {
        return new Stamped();
    }

    /**
     * Create an instance of {@link QueryPendingResponse }
     * 
     */
    public QueryPendingResponse createQueryPendingResponse() {
        return new QueryPendingResponse();
    }

    /**
     * Create an instance of {@link QueryPending }
     * 
     */
    public QueryPending createQueryPending() {
        return new QueryPending();
    }

    /**
     * Create an instance of {@link QuickStamp }
     * 
     */
    public QuickStamp createQuickStamp() {
        return new QuickStamp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StampedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampedResponse")
    public JAXBElement<StampedResponse> createStampedResponse(StampedResponse value) {
        return new JAXBElement<StampedResponse>(_StampedResponse_QNAME, StampedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StampResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampResponse")
    public JAXBElement<StampResponse> createStampResponse(StampResponse value) {
        return new JAXBElement<StampResponse>(_StampResponse_QNAME, StampResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuickStampResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "quick_stampResponse")
    public JAXBElement<QuickStampResponse> createQuickStampResponse(QuickStampResponse value) {
        return new JAXBElement<QuickStampResponse>(_QuickStampResponse_QNAME, QuickStampResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stamp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stamp")
    public JAXBElement<Stamp> createStamp(Stamp value) {
        return new JAXBElement<Stamp>(_Stamp_QNAME, Stamp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stamped }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stamped")
    public JAXBElement<Stamped> createStamped(Stamped value) {
        return new JAXBElement<Stamped>(_Stamped_QNAME, Stamped.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuickStamp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "quick_stamp")
    public JAXBElement<QuickStamp> createQuickStamp(QuickStamp value) {
        return new JAXBElement<QuickStamp>(_QuickStamp_QNAME, QuickStamp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPending }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "query_pending")
    public JAXBElement<QueryPending> createQueryPending(QueryPending value) {
        return new JAXBElement<QueryPending>(_QueryPending_QNAME, QueryPending.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "query_pendingResponse")
    public JAXBElement<QueryPendingResponse> createQueryPendingResponse(QueryPendingResponse value) {
        return new JAXBElement<QueryPendingResponse>(_QueryPendingResponse_QNAME, QueryPendingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampedResult", scope = StampedResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createStampedResponseStampedResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_StampedResponseStampedResult_QNAME, AcuseRecepcionCFDI.class, StampedResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "query_pendingResult", scope = QueryPendingResponse.class)
    public JAXBElement<QueryPendingResult> createQueryPendingResponseQueryPendingResult(QueryPendingResult value) {
        return new JAXBElement<QueryPendingResult>(_QueryPendingResponseQueryPendingResult_QNAME, QueryPendingResult.class, QueryPendingResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = Stamp.class)
    public JAXBElement<String> createStampUsername(String value) {
        return new JAXBElement<String>(_StampUsername_QNAME, String.class, Stamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = Stamp.class)
    public JAXBElement<byte[]> createStampXml(byte[] value) {
        return new JAXBElement<byte[]>(_StampXml_QNAME, byte[].class, Stamp.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = Stamp.class)
    public JAXBElement<String> createStampPassword(String value) {
        return new JAXBElement<String>(_StampPassword_QNAME, String.class, Stamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampResult", scope = StampResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createStampResponseStampResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_StampResponseStampResult_QNAME, AcuseRecepcionCFDI.class, StampResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = Stamped.class)
    public JAXBElement<String> createStampedUsername(String value) {
        return new JAXBElement<String>(_StampUsername_QNAME, String.class, Stamped.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = Stamped.class)
    public JAXBElement<byte[]> createStampedXml(byte[] value) {
        return new JAXBElement<byte[]>(_StampXml_QNAME, byte[].class, Stamped.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = Stamped.class)
    public JAXBElement<String> createStampedPassword(String value) {
        return new JAXBElement<String>(_StampPassword_QNAME, String.class, Stamped.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = QueryPending.class)
    public JAXBElement<String> createQueryPendingUsername(String value) {
        return new JAXBElement<String>(_StampUsername_QNAME, String.class, QueryPending.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "uuid", scope = QueryPending.class)
    public JAXBElement<String> createQueryPendingUuid(String value) {
        return new JAXBElement<String>(_QueryPendingUuid_QNAME, String.class, QueryPending.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = QueryPending.class)
    public JAXBElement<String> createQueryPendingPassword(String value) {
        return new JAXBElement<String>(_StampPassword_QNAME, String.class, QueryPending.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = QuickStamp.class)
    public JAXBElement<String> createQuickStampUsername(String value) {
        return new JAXBElement<String>(_StampUsername_QNAME, String.class, QuickStamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = QuickStamp.class)
    public JAXBElement<byte[]> createQuickStampXml(byte[] value) {
        return new JAXBElement<byte[]>(_StampXml_QNAME, byte[].class, QuickStamp.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = QuickStamp.class)
    public JAXBElement<String> createQuickStampPassword(String value) {
        return new JAXBElement<String>(_StampPassword_QNAME, String.class, QuickStamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "quick_stampResult", scope = QuickStampResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createQuickStampResponseQuickStampResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_QuickStampResponseQuickStampResult_QNAME, AcuseRecepcionCFDI.class, QuickStampResponse.class, value);
    }

}
