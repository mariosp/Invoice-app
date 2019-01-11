package contacts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

import util.MySqlConn;


public class ContactsD extends NewContact{
	
	private int id;
    private String cname;
    private String afm;
	private String oc;
	private String doy;
	private String street;
	private String mob;
	private String town;
	private String email;
	private String tel;
	private String zip;
	private String dcreated;
	private String dmodified;
	private String status;
    
	
	private String ctitle;
	private String tel2;
	private byte[] logo;
	private String fax;
	public byte[] buffer = new byte[1024];
	private String spor;
	private String sser;
	private String pas;
	private String em;
	
    
    public ContactsD(int Id,String CName,String Afm)
    {
        this.id = Id;
        this.cname = CName;
        this.afm = Afm;
       
    }
    
    public ContactsD(int Id,String CName,String Oc,String Afm,String Doy,String Street,String Town,String Zip,String Email,String Tel,String Mob,String Status,String Dcreated,String Dmodified)
    {
        this.id = Id;
        this.cname = CName;
        this.oc = Oc;
        this.afm = Afm;
        this.doy = Doy;
        this.street = Street;
        this.town = Town;
        this.zip = Zip;
        this.email = Email;
        this.tel = Tel;
        this.mob = Mob;
        this.status=Status;
        this.dcreated = Dcreated;
        this.dmodified = Dmodified;
       
    }
    
    public ContactsD() {
		// TODO Auto-generated constructor stub
	}

	public int getId()
    {
        return id;
    }
    
    public String getCname()
    {
        return cname;
    }
    
    public String getOc()
    {
		return oc;
    }
    
    
    public String getAfm()
    {
        return afm;
    }

    public String getDoy()
    {
		return doy;
    }
    
    public String getStreet()
    {
		return street;
    }
    public String getTown()
    {
		return town;
    }

    public String getZip()
    {
		return zip;
    }
    public String getEmail()
    {
		return email;
    }
    public String getTel()
    {
		return tel;
    }
    public String getMob()
    {
		return mob;
    }
    public String getStatus()
    {
		return status;
    }
    public String getDcreated()
    {
		return dcreated;
    }
    
    public String getDmodified()
    {
		return dmodified;
    }
    
    public String getTel2()
    {
		return tel2;
    }

    public String getCtitle()
    {
		return ctitle;
    }
    
    public byte[] getLogo()
    {
		return logo;
    }
    
    public String getFax()
    {
		return fax;
    }
    
    
    public String getEm()
    {
		return em;
    }
    
    public String getPas()
    {
		return pas;
    }
    
    public String getSser()
    {
		return sser;
    }
    
    public String getSpor()
    {
		return spor;
    }
    


	
	
	public static void addEntry(JTextField tcn, JTextField toc, JTextField tafm, JTextField tdoy, JTextField tstreet, JTextField ttown, JTextField ttk, JTextField temail, JTextField ttel, JTextField tmob) {
		
			
		
		try {
			Statement stmt = MySqlConn.dbConnector().createStatement();
			
			   String sql = "INSERT INTO contacts(ID,CompanyName,Occupation,VatNumber,TaxOffice,StreetAddress,Town,Zip,Email,Tel,Mobile,Status) VALUES ("
	            		+ "NULL" +",'"+
	            		tcn.getText() + "', '" +
	                    toc.getText() + "', '" +
	                    tafm.getText() + "', '" +
	                    tdoy.getText() + "', '" +
	                    tstreet.getText() + "', '" +
	                    ttown.getText() + "', '" +
	                    ttk.getText() + "', '" +
	                    temail.getText() + "', '" +
	                    ttel.getText() + "', '" +
	                    tmob.getText() + "','ACTIVE')";
			   
			   			stmt.executeUpdate(sql);
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	

public static void updateEntry(int id,JTextField tcn, JTextField toc, JTextField tafm, JTextField tdoy, JTextField tstreet, JTextField ttown, JTextField ttk, JTextField temail, JTextField ttel, JTextField tmob) {
		
			
		
		try {
			Statement stmt = MySqlConn.dbConnector().createStatement();
			
			   String sql = "UPDATE contacts SET CompanyName='" 
	            		+ tcn.getText() + "', Occupation='" 
	            		+ toc.getText() + "', VatNumber='" 
	            		+ tafm.getText() + "', TaxOffice='" 
	                    + tdoy.getText() + "', StreetAddress='"
	                    + tstreet.getText() + "', Town='"
	                    + ttown.getText() + "', Zip='"
	                    + ttk.getText() + "', Email='"
	                    + temail.getText() + "', Tel='"
	                    + ttel.getText() + "', Mobile='"
	                    + tmob.getText() + "' WHERE ID="+ id ;
			   
			   			stmt.executeUpdate(sql);
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	



public static ArrayList<ContactsD> ListUsers(String ValToSearch)
{
    ArrayList<ContactsD> usersList = new ArrayList<ContactsD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `contacts` WHERE (`CompanyName` LIKE '%"+ValToSearch+"%' OR `VatNumber`LIKE '%"+ValToSearch+"%' )";
        rs = stmt.executeQuery(searchQuery);
        
        ContactsD user;
        
        while(rs.next())
        {
            user = new ContactsD(
                             rs.getInt("ID"),
                             rs.getString("CompanyName"),
                             rs.getString("VatNumber")
                            );
            usersList.add(user);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return usersList;
}


public static ArrayList<ContactsD> FindContact(String ValToSearch)
{
    ArrayList<ContactsD> usersList = new ArrayList<ContactsD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `contacts` WHERE (`ID`= '"+ValToSearch+"' )";
        rs = stmt.executeQuery(searchQuery);
        
        ContactsD user;
        
        while(rs.next())
        {
            user = new ContactsD(
                             rs.getInt("ID"),
                             rs.getString("CompanyName"),
                             rs.getString("Occupation"),
                             rs.getString("VatNumber"),
                             rs.getString("TaxOffice"),
                             rs.getString("StreetAddress"),
                             rs.getString("Town"),
                             rs.getString("Zip"),
                             rs.getString("Email"),
                             rs.getString("Tel"),
                             rs.getString("Mobile"),
                             rs.getString("Status"),
                             rs.getString("Dcreated"),
                             rs.getString("Dmodified")
                             
                            );
            usersList.add(user);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return usersList;
}


public static void updateMyCompany(JTextField tcn, JTextField tct, JTextField toc, JTextField tafm, JTextField tdoy, JTextField tstreet, JTextField ttown, JTextField ttk, JTextField temail, JTextField ttel, JTextField ttel2, JTextField tmob,JTextField tfax, File selectedFile) throws FileNotFoundException {

	
	
	FileInputStream fis=new FileInputStream(selectedFile);
	
	try {
		Statement stmt = MySqlConn.dbConnector().createStatement();
		
		PreparedStatement statement = null;
		
		   String sql = "UPDATE mycompany SET Name='" 
            		+ tcn.getText() + "', CTitle='"
            		+ tct.getText() + "', Occupation='"
            		+ toc.getText() + "', Vat='" 
            		+ tafm.getText() + "', Doy='" 
                    + tdoy.getText() + "', Street='"
                    + tstreet.getText() + "', Town='"
                    + ttown.getText() + "', ZipCode='"
                    + ttk.getText() + "', Email='"
                    + temail.getText() + "', Tel1='"
                    + ttel.getText() + "', Tel2='"
                    + ttel2.getText() + "', Mobile='"
                    + tmob.getText() + "', fax='"
                    + tfax.getText() +"' WHERE ID=1" ;
		   
		   			stmt.executeUpdate(sql);
		   			statement = (PreparedStatement) MySqlConn.dbConnector().prepareStatement("UPDATE mycompany SET logo= ? WHERE ID=1");     
		   			
		   		
		   			statement.setBinaryStream(1,(InputStream)fis,(int)(selectedFile.length()));
		   			statement.executeUpdate();
		   					
		   					
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 


}



public void getMyCompany() {
	
	  ResultSet rs;
	    
	    try{
	    	Statement stmt = MySqlConn.dbConnector().createStatement();
	        String searchQuery = "SELECT * FROM `mycompany` WHERE `ID`=1";
	        rs = stmt.executeQuery(searchQuery);
	        
	        //Blob blob;
	        
	        while(rs.next())
	        {
	                             this.id=rs.getInt("ID");
	                             this.cname=rs.getString("Name");
	                             this.ctitle=rs.getString("CTitle");
	                             this.oc=rs.getString("Occupation");
	                             this.afm=rs.getString("Vat");
	                             this.doy=rs.getString("Doy");
	                             this.street=rs.getString("Street");
	                             this.town= rs.getString("Town");
	                             this.zip=rs.getString("ZipCode");
	                             this.tel=rs.getString("tel1");
	                             this.tel2=rs.getString("tel2");
	                             this.fax=rs.getString("fax");
	                             this.mob=rs.getString("mobile");
	                             this.email=rs.getString("email");
	                             this.logo=rs.getBytes("logo");
	                             
	                             System.out.println(this.logo);
	                             
	        }
	        
	    }catch(Exception ex){
	        System.out.println(ex.getMessage());
	    }
	
}


public void updateEmail(JTextField textField_13, JTextField textField_14, JTextField textField_15, JTextField textField_16) {
	
	
	
	try {
		Statement stmt = MySqlConn.dbConnector().createStatement();
		
		
		
		   String sql = "UPDATE email SET Email='" 
            		+ textField_13.getText() + "', Pass='"
            		+ textField_14.getText() + "', Smtpserver='"
            		+ textField_15.getText() + "', Smtpport='" 
            		+ textField_16.getText() + "' WHERE ID=1" ;
		   
		   			stmt.executeUpdate(sql);
		   			
		   					
		   					
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 


}


public void getSEmail() {
	
	  ResultSet rs;
	    
	    try{
	    	Statement stmt = MySqlConn.dbConnector().createStatement();
	        String searchQuery = "SELECT * FROM `email` WHERE `ID`=1";
	        rs = stmt.executeQuery(searchQuery);
	      
	        
	        while(rs.next())
	        {
	                             this.em=rs.getString("Email");
	                             this.pas=rs.getString("Pass");
	                             this.sser=rs.getString("Smtpserver");
	                             this.spor=rs.getString("Smtpport");
	                       
	        }
	        
	    }catch(Exception ex){
	        System.out.println(ex.getMessage());
	    }
	
}


} //telos



















