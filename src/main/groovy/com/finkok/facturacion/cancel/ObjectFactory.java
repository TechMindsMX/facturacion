
package com.finkok.facturacion.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import views.core.soap.services.apps.CancelaCFDResult;
import views.core.soap.services.apps.QueryPendingResult;
import views.core.soap.services.apps.ReceiptResult;
import views.core.soap.services.apps.UUIDS;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.finkok.facturacion.cancel package. 
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

    private final static QName _CancelSignatureResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel_signatureResponse");
    private final static QName _GetReceipt_QNAME = new QName("http://facturacion.finkok.com/cancel", "get_receipt");
    private final static QName _QueryPendingCancellation_QNAME = new QName("http://facturacion.finkok.com/cancel", "query_pending_cancellation");
    private final static QName _CancelResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancelResponse");
    private final static QName _OutCancel_QNAME = new QName("http://facturacion.finkok.com/cancel", "out_cancel");
    private final static QName _Cancel_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel");
    private final static QName _QueryPendingCancellationResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "query_pending_cancellationResponse");
    private final static QName _OutCancelResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "out_cancelResponse");
    private final static QName _GetReceiptResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "get_receiptResponse");
    private final static QName _CancelSignature_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel_signature");
    private final static QName _CancelKey_QNAME = new QName("http://facturacion.finkok.com/cancel", "key");
    private final static QName _CancelUUIDS_QNAME = new QName("http://facturacion.finkok.com/cancel", "UUIDS");
    private final static QName _CancelStorePending_QNAME = new QName("http://facturacion.finkok.com/cancel", "store_pending");
    private final static QName _CancelCer_QNAME = new QName("http://facturacion.finkok.com/cancel", "cer");
    private final static QName _CancelPassword_QNAME = new QName("http://facturacion.finkok.com/cancel", "password");
    private final static QName _CancelUsername_QNAME = new QName("http://facturacion.finkok.com/cancel", "username");
    private final static QName _CancelTaxpayerId_QNAME = new QName("http://facturacion.finkok.com/cancel", "taxpayer_id");
    private final static QName _QueryPendingCancellationResponseQueryPendingCancellationResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "query_pending_cancellationResult");
    private final static QName _OutCancelXml_QNAME = new QName("http://facturacion.finkok.com/cancel", "xml");
    private final static QName _GetReceiptResponseGetReceiptResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "get_receiptResult");
    private final static QName _CancelSignatureResponseCancelSignatureResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel_signatureResult");
    private final static QName _QueryPendingCancellationUuid_QNAME = new QName("http://facturacion.finkok.com/cancel", "uuid");
    private final static QName _GetReceiptType_QNAME = new QName("http://facturacion.finkok.com/cancel", "type");
    private final static QName _OutCancelResponseOutCancelResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "out_cancelResult");
    private final static QName _CancelResponseCancelResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancelResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.finkok.facturacion.cancel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelResponse }
     * 
     */
    public CancelResponse createCancelResponse() {
        return new CancelResponse();
    }

    /**
     * Create an instance of {@link QueryPendingCancellation }
     * 
     */
    public QueryPendingCancellation createQueryPendingCancellation() {
        return new QueryPendingCancellation();
    }

    /**
     * Create an instance of {@link GetReceiptResponse }
     * 
     */
    public GetReceiptResponse createGetReceiptResponse() {
        return new GetReceiptResponse();
    }

    /**
     * Create an instance of {@link CancelSignature }
     * 
     */
    public CancelSignature createCancelSignature() {
        return new CancelSignature();
    }

    /**
     * Create an instance of {@link OutCancel }
     * 
     */
    public OutCancel createOutCancel() {
        return new OutCancel();
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link QueryPendingCancellationResponse }
     * 
     */
    public QueryPendingCancellationResponse createQueryPendingCancellationResponse() {
        return new QueryPendingCancellationResponse();
    }

    /**
     * Create an instance of {@link CancelSignatureResponse }
     * 
     */
    public CancelSignatureResponse createCancelSignatureResponse() {
        return new CancelSignatureResponse();
    }

    /**
     * Create an instance of {@link OutCancelResponse }
     * 
     */
    public OutCancelResponse createOutCancelResponse() {
        return new OutCancelResponse();
    }

    /**
     * Create an instance of {@link GetReceipt }
     * 
     */
    public GetReceipt createGetReceipt() {
        return new GetReceipt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelSignatureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel_signatureResponse")
    public JAXBElement<CancelSignatureResponse> createCancelSignatureResponse(CancelSignatureResponse value) {
        return new JAXBElement<CancelSignatureResponse>(_CancelSignatureResponse_QNAME, CancelSignatureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReceipt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "get_receipt")
    public JAXBElement<GetReceipt> createGetReceipt(GetReceipt value) {
        return new JAXBElement<GetReceipt>(_GetReceipt_QNAME, GetReceipt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingCancellation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "query_pending_cancellation")
    public JAXBElement<QueryPendingCancellation> createQueryPendingCancellation(QueryPendingCancellation value) {
        return new JAXBElement<QueryPendingCancellation>(_QueryPendingCancellation_QNAME, QueryPendingCancellation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancelResponse")
    public JAXBElement<CancelResponse> createCancelResponse(CancelResponse value) {
        return new JAXBElement<CancelResponse>(_CancelResponse_QNAME, CancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutCancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "out_cancel")
    public JAXBElement<OutCancel> createOutCancel(OutCancel value) {
        return new JAXBElement<OutCancel>(_OutCancel_QNAME, OutCancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel")
    public JAXBElement<Cancel> createCancel(Cancel value) {
        return new JAXBElement<Cancel>(_Cancel_QNAME, Cancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingCancellationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "query_pending_cancellationResponse")
    public JAXBElement<QueryPendingCancellationResponse> createQueryPendingCancellationResponse(QueryPendingCancellationResponse value) {
        return new JAXBElement<QueryPendingCancellationResponse>(_QueryPendingCancellationResponse_QNAME, QueryPendingCancellationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutCancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "out_cancelResponse")
    public JAXBElement<OutCancelResponse> createOutCancelResponse(OutCancelResponse value) {
        return new JAXBElement<OutCancelResponse>(_OutCancelResponse_QNAME, OutCancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReceiptResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "get_receiptResponse")
    public JAXBElement<GetReceiptResponse> createGetReceiptResponse(GetReceiptResponse value) {
        return new JAXBElement<GetReceiptResponse>(_GetReceiptResponse_QNAME, GetReceiptResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelSignature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel_signature")
    public JAXBElement<CancelSignature> createCancelSignature(CancelSignature value) {
        return new JAXBElement<CancelSignature>(_CancelSignature_QNAME, CancelSignature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "key", scope = Cancel.class)
    public JAXBElement<byte[]> createCancelKey(byte[] value) {
        return new JAXBElement<byte[]>(_CancelKey_QNAME, byte[].class, Cancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UUIDS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "UUIDS", scope = Cancel.class)
    public JAXBElement<UUIDS> createCancelUUIDS(UUIDS value) {
        return new JAXBElement<UUIDS>(_CancelUUIDS_QNAME, UUIDS.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = Cancel.class)
    public JAXBElement<Boolean> createCancelStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_CancelStorePending_QNAME, Boolean.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cer", scope = Cancel.class)
    public JAXBElement<byte[]> createCancelCer(byte[] value) {
        return new JAXBElement<byte[]>(_CancelCer_QNAME, byte[].class, Cancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = Cancel.class)
    public JAXBElement<String> createCancelPassword(String value) {
        return new JAXBElement<String>(_CancelPassword_QNAME, String.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = Cancel.class)
    public JAXBElement<String> createCancelUsername(String value) {
        return new JAXBElement<String>(_CancelUsername_QNAME, String.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = Cancel.class)
    public JAXBElement<String> createCancelTaxpayerId(String value) {
        return new JAXBElement<String>(_CancelTaxpayerId_QNAME, String.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "query_pending_cancellationResult", scope = QueryPendingCancellationResponse.class)
    public JAXBElement<QueryPendingResult> createQueryPendingCancellationResponseQueryPendingCancellationResult(QueryPendingResult value) {
        return new JAXBElement<QueryPendingResult>(_QueryPendingCancellationResponseQueryPendingCancellationResult_QNAME, QueryPendingResult.class, QueryPendingCancellationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "xml", scope = OutCancel.class)
    public JAXBElement<byte[]> createOutCancelXml(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelXml_QNAME, byte[].class, OutCancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "key", scope = OutCancel.class)
    public JAXBElement<byte[]> createOutCancelKey(byte[] value) {
        return new JAXBElement<byte[]>(_CancelKey_QNAME, byte[].class, OutCancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = OutCancel.class)
    public JAXBElement<Boolean> createOutCancelStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_CancelStorePending_QNAME, Boolean.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cer", scope = OutCancel.class)
    public JAXBElement<byte[]> createOutCancelCer(byte[] value) {
        return new JAXBElement<byte[]>(_CancelCer_QNAME, byte[].class, OutCancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = OutCancel.class)
    public JAXBElement<String> createOutCancelPassword(String value) {
        return new JAXBElement<String>(_CancelPassword_QNAME, String.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = OutCancel.class)
    public JAXBElement<String> createOutCancelUsername(String value) {
        return new JAXBElement<String>(_CancelUsername_QNAME, String.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = OutCancel.class)
    public JAXBElement<String> createOutCancelTaxpayerId(String value) {
        return new JAXBElement<String>(_CancelTaxpayerId_QNAME, String.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiptResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "get_receiptResult", scope = GetReceiptResponse.class)
    public JAXBElement<ReceiptResult> createGetReceiptResponseGetReceiptResult(ReceiptResult value) {
        return new JAXBElement<ReceiptResult>(_GetReceiptResponseGetReceiptResult_QNAME, ReceiptResult.class, GetReceiptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel_signatureResult", scope = CancelSignatureResponse.class)
    public JAXBElement<CancelaCFDResult> createCancelSignatureResponseCancelSignatureResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_CancelSignatureResponseCancelSignatureResult_QNAME, CancelaCFDResult.class, CancelSignatureResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "uuid", scope = QueryPendingCancellation.class)
    public JAXBElement<String> createQueryPendingCancellationUuid(String value) {
        return new JAXBElement<String>(_QueryPendingCancellationUuid_QNAME, String.class, QueryPendingCancellation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = QueryPendingCancellation.class)
    public JAXBElement<String> createQueryPendingCancellationPassword(String value) {
        return new JAXBElement<String>(_CancelPassword_QNAME, String.class, QueryPendingCancellation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = QueryPendingCancellation.class)
    public JAXBElement<String> createQueryPendingCancellationUsername(String value) {
        return new JAXBElement<String>(_CancelUsername_QNAME, String.class, QueryPendingCancellation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "uuid", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptUuid(String value) {
        return new JAXBElement<String>(_QueryPendingCancellationUuid_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "type", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptType(String value) {
        return new JAXBElement<String>(_GetReceiptType_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptPassword(String value) {
        return new JAXBElement<String>(_CancelPassword_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptUsername(String value) {
        return new JAXBElement<String>(_CancelUsername_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptTaxpayerId(String value) {
        return new JAXBElement<String>(_CancelTaxpayerId_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "xml", scope = CancelSignature.class)
    public JAXBElement<byte[]> createCancelSignatureXml(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelXml_QNAME, byte[].class, CancelSignature.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = CancelSignature.class)
    public JAXBElement<Boolean> createCancelSignatureStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_CancelStorePending_QNAME, Boolean.class, CancelSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = CancelSignature.class)
    public JAXBElement<String> createCancelSignaturePassword(String value) {
        return new JAXBElement<String>(_CancelPassword_QNAME, String.class, CancelSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = CancelSignature.class)
    public JAXBElement<String> createCancelSignatureUsername(String value) {
        return new JAXBElement<String>(_CancelUsername_QNAME, String.class, CancelSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "out_cancelResult", scope = OutCancelResponse.class)
    public JAXBElement<CancelaCFDResult> createOutCancelResponseOutCancelResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_OutCancelResponseOutCancelResult_QNAME, CancelaCFDResult.class, OutCancelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancelResult", scope = CancelResponse.class)
    public JAXBElement<CancelaCFDResult> createCancelResponseCancelResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_CancelResponseCancelResult_QNAME, CancelaCFDResult.class, CancelResponse.class, value);
    }

}
