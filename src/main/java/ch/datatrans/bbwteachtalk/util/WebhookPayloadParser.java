package ch.datatrans.bbwteachtalk.util;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * @author dominik.mengelt@gmail.com
 */
public class WebhookPayloadParser {


    public static boolean paymentWasSuccessful(String payload) throws Exception {
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = newDocumentBuilder.parse(new ByteArrayInputStream(payload.getBytes()));
        NamedNodeMap transactionAttributes = doc.getElementsByTagName("transaction").item(0).getAttributes();
        Node status = transactionAttributes.getNamedItem("status");
        String responseCode = doc.getElementsByTagName("responseCode").item(0).getTextContent();
        return "success".equals(status.getNodeValue()) && "01".equals(responseCode);
    }

    public static Payment getPaymentDetails(String payload) throws Exception {
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = newDocumentBuilder.parse(new ByteArrayInputStream(payload.getBytes()));
        String transactionId = doc.getElementsByTagName("uppTransactionId").item(0).getTextContent();
        String paymentMethod = doc.getElementsByTagName("pmethod").item(0).getTextContent();
        NamedNodeMap transactionAttributes = doc.getElementsByTagName("transaction").item(0).getAttributes();
        String refno = transactionAttributes.getNamedItem("refno").getNodeValue();
        return new Payment(transactionId, refno, paymentMethod);
    }


    /*

    Sample POST URL Payload:

    <?xml version="1.0" encoding="UTF-8"?>
    <uppTransactionService version="1">
      <body merchantId="1100017010" testOnly="yes">
        <transaction refno="asdf" status="success">
          <uppTransactionId>181202165243134016</uppTransactionId>
          <amount>5</amount>
          <currency>CHF</currency>
          <pmethod>VIS</pmethod>
          <reqtype>CAA</reqtype>
          <success>
            <authorizationCode>254484028</authorizationCode>
            <acqAuthorizationCode>165254</acqAuthorizationCode>
            <responseMessage>Authorized</responseMessage>
            <responseCode>01</responseCode>
          </success>
          <userParameters>
            <parameter name="cardno">424242xxxxxx4242</parameter>
            <parameter name="responseCode">01</parameter>
            <parameter name="expy">18</parameter>
            <parameter name="returnCustomerCountry">GBR</parameter>
            <parameter name="theme">DT2015</parameter>
            <parameter name="expm">12</parameter>
          </userParameters>
        </transaction>
      </body>
    </uppTransactionService>

    */

}
