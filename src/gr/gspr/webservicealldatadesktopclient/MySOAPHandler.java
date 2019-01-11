/*
 * Apache License, Version 2.0
 */

package gr.gspr.webservicealldatadesktopclient;

import java.util.Set;
import java.util.TreeSet;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author j.vlachos@gsis.gr
 */

//used to put username and password in the message headers
public class MySOAPHandler implements SOAPHandler<SOAPMessageContext> {

    private String UserName;
    
    private String PassWord;

    /**
     * Get the value of PassWord
     *
     * @return the value of PassWord
     */
    public String getPassWord() {
        return PassWord;
    }

    /**
     * Set the value of PassWord
     *
     * @param PassWord new value of PassWord
     */
    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    /**
     * Get the value of UserName
     *
     * @return the value of UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * Set the value of UserName
     *
     * @param UserName new value of UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    
    public Set<QName> getHeaders() {
        return new TreeSet();
    }

    public boolean handleMessage(SOAPMessageContext context) {
        
        
        String prefixUri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-";
        String uri = prefixUri + "wssecurity-secext-1.0.xsd";
        String uta = prefixUri + "wssecurity-utility-1.0.xsd";
        String ta = prefixUri + "username-token-profile-1.0#PasswordText";
        Boolean outboundProperty =
            (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            try {
                SOAPEnvelope envelope = context.getMessage()
                        .getSOAPPart().getEnvelope();
                SOAPFactory factory = SOAPFactory.newInstance();
                String prefix = "wsse";
                SOAPElement securityElem =
                        factory.createElement("Security",prefix,uri);
                SOAPElement tokenElem =
                        factory.createElement("UsernameToken",prefix,uri);
                tokenElem.addAttribute(QName.valueOf("wsu:Id"),"UsernameToken-2");
                tokenElem.addAttribute(QName.valueOf("xmlns:wsu"), uta);
                SOAPElement userElem =
                        factory.createElement("Username",prefix,uri);
                userElem.addTextNode(UserName);
                SOAPElement pwdElem =
                        factory.createElement("Password",prefix,uri);
                pwdElem.addTextNode(PassWord);
                pwdElem.addAttribute(QName.valueOf("Type"), ta);
                tokenElem.addChildElement(userElem);
                tokenElem.addChildElement(pwdElem);
                securityElem.addChildElement(tokenElem);
                
                if (envelope.getHeader() != null) {
                    envelope.getHeader().detachNode();
                 }
                
                SOAPHeader header = envelope.addHeader();
                header.addChildElement(securityElem);

            } catch (Exception e) {
                System.out.println("Exception in handler: " + e);
            }
        }
        
        return true;
        
        
    }

    public boolean handleFault(SOAPMessageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close(MessageContext context) {
        //
    }
}
