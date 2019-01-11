/*
 *Apache License, Version 2.0
 */
package gr.gspr.webservicealldatadesktopclient.util;

/**
 *
 * @author j.vlachos
 */
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.*;
import java.util.*;
 
public class MyProxySelector extends ProxySelector {
    private String Url;
    private BigInteger Port;
    
    public MyProxySelector(String url,BigInteger port) {
        this.Port=port;
        this.Url=url;
    }
 
    @Override
    public List<Proxy> select(URI uri) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Url, Port.intValue() ));
        
        ArrayList list = new ArrayList();
        list.add(proxy);
        return list;
    }
    
    /*public List<Proxy> select(URI uri,String url,int port) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url, port));
        ArrayList list = new ArrayList();
        list.add(proxy);
        return list;
    }*/
 
    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        System.err.println("Connection to " + uri + " failed.");
    }
 
}