/*
 * Apache License, Version 2.0
 */

package gr.gspr.webservicealldatadesktopclient;

import gr.gspr.webservicealldatadesktopclient.client.GenWsErrorRtUser;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublic;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublicBasicRtUser;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublicFirmActRtUser;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublicFirmActRtUserArray;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublicInputRtUser;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublic_Service;
import java.math.BigDecimal;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;



/**
 *
 * @author j.vlachos@gsis.gr
 */



public class ServiceClient {
    //call to get the version of the web service
	
	
	
	public Holder<RgWsPublicBasicRtUser> basicRtUser;
	
	
	
    public String getVersion(){
        String version="not found";
        
        RgWsPublic_Service service=new RgWsPublic_Service();
        
        RgWsPublic rg= service.getRgWsPublicPort();
        version=rg.rgWsPublicVersionInfo();
        
        return version;
    }
    
  
    
    //call to get the data for a tin 
    public String getData(String un, String pass,String afm, String afmDelegator){
        String data="not found";
        
        
        RgWsPublic_Service service=new RgWsPublic_Service();
        //put username and password in call
        final MySOAPHandler mySoapHandler=new MySOAPHandler();
        mySoapHandler.setPassWord((String) pass);
        mySoapHandler.setUserName((String) un);
        
        service.setHandlerResolver(new HandlerResolver() {
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlerList = new ArrayList<Handler>();
                
                handlerList.add(mySoapHandler);
                return handlerList;
            }
        });
        
        RgWsPublic rg= service.getRgWsPublicPort();
        
        RgWsPublicInputRtUser inputRtUser=new RgWsPublicInputRtUser();
        
        inputRtUser.setAfmCalledBy(afmDelegator);
        inputRtUser.setAfmCalledFor(afm);
        
        Holder<RgWsPublicBasicRtUser> basicRtUser=new Holder<RgWsPublicBasicRtUser>(new RgWsPublicBasicRtUser());
                
        this.basicRtUser=basicRtUser;
        Holder<RgWsPublicFirmActRtUserArray> rtUserArray=new Holder<RgWsPublicFirmActRtUserArray>(new RgWsPublicFirmActRtUserArray());
        
        BigDecimal pCSIO=new BigDecimal(0);
        Holder<BigDecimal> pCallSeqIdOut=new Holder<BigDecimal>(pCSIO);
        
        GenWsErrorRtUser pERO=new GenWsErrorRtUser();
        
        
        Holder<GenWsErrorRtUser> pErrorRecOut=new Holder<GenWsErrorRtUser>(pERO);
        
        rg.rgWsPublicAfmMethod(inputRtUser, basicRtUser, rtUserArray, pCallSeqIdOut, pErrorRecOut);
        GenWsErrorRtUser errorreturned=pErrorRecOut.value;
        String err=errorreturned.getErrorDescr();
        
        //if there are not any errors, show the data
        if ((err==null) || (err.isEmpty())){
            
            String returndata="";
            returndata=returndata+"Ονοματεπώνυμο/Επωνυμία: "+basicRtUser.value.getOnomasia()+"\r\n";
            returndata=returndata+"ΑΦΜ: "+basicRtUser.value.getAfm()+"\r\n";
            if ((basicRtUser.value.getCommerTitle()!=null) && (!basicRtUser.value.getCommerTitle().trim().isEmpty()))
                returndata=returndata+"Τίτλος επιχείρησης: "+basicRtUser.value.getCommerTitle()+"\r\n";
            returndata=returndata+"Φυσικό/Μη φυσικό πρόσωπο: "+basicRtUser.value.getINiFlagDescr()+"\r\n";
            if ((basicRtUser.value.getLegalStatusDescr()!=null) && (!basicRtUser.value.getLegalStatusDescr().trim().isEmpty())){
                returndata=returndata+"Μορφή Μη ΦΠ: "+basicRtUser.value.getLegalStatusDescr()+"\r\n";
            }
            returndata=returndata+"ΔΟΥ: "+basicRtUser.value.getDoyDescr()+" ("+basicRtUser.value.getDoy()+")\r\n";
           
            returndata=returndata+basicRtUser.value.getDeactivationFlagDescr()+"\r\n";
            
            returndata=returndata+basicRtUser.value.getFirmFlagDescr()+"\r\n";
            
            try{
                returndata=returndata+"Διεύθυνση: "+basicRtUser.value.getPostalAddress().trim()+" "+basicRtUser.value.getPostalAddressNo().trim()+", "+basicRtUser.value.getPostalAreaDescription().trim()+", "+basicRtUser.value.getPostalZipCode().trim()+"\r\n";
            }catch(Exception e){
                //No address
            }
            
            //Dates:
            String regDate="";
            try{
                regDate=basicRtUser.value.getRegistDate().getDay()+"-"+basicRtUser.value.getRegistDate().getMonth()+"-"+basicRtUser.value.getRegistDate().getYear();
             }catch(Exception e){
                //No regDate
            }      
            
            if (!regDate.isEmpty()){
                returndata=returndata+"Ημ/νία Έναρξης: "+regDate+"\r\n";
            }
            
            String endDate="";
            try{
                endDate=basicRtUser.value.getStopDate().getDay()+"-"+basicRtUser.value.getStopDate().getMonth()+"-"+basicRtUser.value.getStopDate().getYear();
             }catch(Exception e){
                //No regDate
            }      
            
            if (!endDate.isEmpty()){
                returndata=returndata+"Ημ/νία Διακοπής: "+endDate+"\r\n";
            }
            
            //ΔΡΑΣΤΗΡΙΟΤΗΤΕΣ ΕΠΙΧΕΙΡΗΣΗΣ
            if ((rtUserArray.value.getRgWsPublicFirmActRtUser()!=null) && (!rtUserArray.value.getRgWsPublicFirmActRtUser().isEmpty())) {
                List<RgWsPublicFirmActRtUser> userArrayList=rtUserArray.value.getRgWsPublicFirmActRtUser();
                returndata=returndata+"\r\n"+"ΔΡΑΣΤΗΡΙΟΤΗΤΕΣ ΕΠΙΧΕΙΡΗΣΗΣ:"+"\r\n";
                for (RgWsPublicFirmActRtUser rtUser:userArrayList){
                    returndata=returndata+rtUser.getFirmActCode()+"-"+rtUser.getFirmActDescr() +" ("+rtUser.getFirmActKindDescr()+")\r\n";
                }
            }
            
            
            return returndata;
        }else{
            return err;
        }
        
        
       
    }
}
