/*
 *Apache License, Version 2.0
 */
package gr.gspr.webservicealldatadesktopclient.util;

import java.math.BigInteger;

/**
 *
 * @author j.vlachos
 */
public class ProxySettings implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigInteger getPort() {
        return port;
    }

    public void setPort(BigInteger port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    BigInteger port;
    String username;
    String password;
    
    public ProxySettings(String u,BigInteger p, String un, String pass) {
        url = u;
        port = p;
        username=un;
        password=pass;
    }
    
}
