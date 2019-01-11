package invoice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

import contacts.ContactsD;
import products.ProductsD;
import util.MySqlConn;

public class InvoiceD {
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	private  int lastid;
	private Date dateinserted;
	private int clientid;
	private int id;
	private String invoicedate;
	private String status;
	private Double total;
	private boolean paidstatus;
	private String pname;
	private int pid;
	private Double pquantity;
	private Double pprice;
	private Double ptax;
	private byte[] pdf;
	
	
	 public InvoiceD(int ID,int ClientID,String InvoiceDate,String Status,double Total,boolean PaidStatus)
	    {
	        this.id = ID;
	        this.clientid = ClientID;
	        this.invoicedate =InvoiceDate ;
	        this.status = Status;
	        this.total = Total;
	        this.paidstatus = PaidStatus;
	       
	        
	       
	    }
	
	 
	 public InvoiceD(int pID,String pName,double pQuantity,double pPrice,double pTax)
	    {
	        this.pid = pID;
	        this.pname = pName;
	        this.pquantity =pQuantity ;
	        this.pprice = pPrice;
	        this.ptax = pTax;
	       
	       
	        
	       
	    }
	 
	 
	public InvoiceD() {
		
	}
	
	




	int lastInsertedId() {
		
		return lastid;
		
	}
	
	int idToInsert() {
		return lastid+1;
	}
	
	
	
	 public int getId()
	    {
	        return id;
	    }
	    
	    public int getClientId()
	    {
	        return clientid;
	    }
	    
	    public String getInvoiceDate()
	    {
			return invoicedate;
	    }
	    
	    public String getStatus()
	    {
			return status;
	    }
	    
	    public Double getTotal()
	    {
			return total;
	    }
	
	    public boolean getPaidStatus()
	    {
			return paidstatus;
	    }
	
	    
	    
	    //items
	
	    public int getpId()
	    {
	        return pid;
	    }
	    
	   
	    public String getpName()
	    {
			return pname;
	    
	    }
	    
	    public Double getpQuantity()
	    {
			return pquantity;
	    }
	    
	    public Double getpPrice()
	    {
			return pprice;
	    }
	    
	    public Double getpTax()
	    {
			return ptax;
	    }
	    
	    public byte[] getPdf()
	    {
			return pdf;
	    }
	    
	    
	
	
	public void last_inserted_id() {
		 ResultSet rs;
			
		try {
			Statement stmt = MySqlConn.dbConnector().createStatement();
			
			 String sql = "SELECT id FROM invoices ORDER BY id DESC LIMIT 1";
			   
			 rs = stmt.executeQuery(sql);

		        while(rs.next())
		        {
		        	 
		                      lastid =  rs.getInt("ID");
		                    
		                     
		        }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	
	
	
	public  void addInvoiceToDB(int contactid, Date date, double f) {
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateinserted =date;
		try {
			Statement stmt = MySqlConn.dbConnector().createStatement();
			
			   String sql = "INSERT INTO invoices(ID,ClientID,InvoiceDate,Status,Total,PaidStatus) VALUES ("
	            		+ "NULL" +",'"+
	            		contactid + "', '" +
	            		dateFormat.format(date) + "', '" +
	                    "issued" + "', '" +
	                     f + "', '" +
	                    "N"+ "')";
			   
			   			stmt.executeUpdate(sql);
			   			
			   			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			
		}
	
	
public  void addProductsToDB(Object[][] tablecell) {
		
	
	int id =lastid+1;
	
	
		try {
			
			Statement stmt = MySqlConn.dbConnector().createStatement();
			
			for (int i=0;tablecell[i][0]!=null;i++) {
			
			   String sql = "INSERT INTO invoiceitems(InvoiceID,ProductID,ProductName,Quantity,Price,Tax,DateInserted) VALUES ("
	            		+ id +",'"+
	            		tablecell[i][5] + "', '" +
	                    tablecell[i][0] + "', '" +
	                    tablecell[i][1] + "', '" +
	                    tablecell[i][2] + "', '" +
	                    tablecell[i][3] + "', '" +
	                    dateFormat.format(dateinserted) + "')";
			   
			   			stmt.executeUpdate(sql);
			}
			   			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			
		}




public static ArrayList<InvoiceD> ListInvoices()
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` ORDER BY `ID` DESC LIMIT 20";
        rs = stmt.executeQuery(searchQuery);
        
      InvoiceD invoice;
	
        
        while(rs.next())
        {
            invoice = new InvoiceD(
                             rs.getInt("ID"),
                             rs.getInt("ClientID"),
                             rs.getString("InvoiceDate"),
                             rs.getString("Status"),
                             rs.getDouble("Total"),
                             rs.getBoolean("PaidStatus")
                            );
            invoiceList.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceList;
}


public static ArrayList<InvoiceD> FindInvoice(String ValToSearch) throws SQLException
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs=null;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` INNER JOIN `contacts` ON invoices.ClientID = contacts.ID WHERE (CompanyName LIKE '%"+ValToSearch+"%' OR VatNumber LIKE '%"+ValToSearch+"%')";
        rs = stmt.executeQuery(searchQuery);
      InvoiceD invoice;
	
        
        while(rs.next())
        {
            invoice = new InvoiceD(
                             rs.getInt("invoices.ID"),
                             rs.getInt("ClientID"),
                             rs.getString("InvoiceDate"),
                             rs.getString("Status"),
                             rs.getDouble("Total"),
                             rs.getBoolean("PaidStatus")
                            );
            invoiceList.add(invoice);
           
        }
        
        
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    
    return invoiceList;
}



public static ArrayList<InvoiceD> SelectInvoice(String ValToSearch)
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` WHERE (`ID`= '"+ValToSearch+"' )";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("invoices.ID"),
                     rs.getInt("ClientID"),
                     rs.getString("InvoiceDate"),
                     rs.getString("Status"),
                     rs.getDouble("Total"),
                     rs.getBoolean("PaidStatus")
                    );
     invoiceList.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceList;
}
	
	
public static ArrayList<InvoiceD> InvoiceItems(String ValToSearch)
{
    ArrayList<InvoiceD> invoiceItems = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoiceitems` WHERE (`InvoiceID`= '"+ValToSearch+"' )";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("ProductID"),
                     rs.getString("ProductName"),
                     rs.getDouble("Quantity"),
                     rs.getDouble("Price"),
                     rs.getDouble("Tax")
                    
                    );
     invoiceItems.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceItems;
}
	
	
public  static void addPdfToDb(int i, ByteArrayOutputStream baos) {
		
	
	InputStream is = new ByteArrayInputStream(baos.toByteArray());
	
	PreparedStatement stmt = null;
		try {
			
			//Statement stmt = MySqlConn.dbConnector().createStatement();
			
			 stmt = (PreparedStatement) MySqlConn.dbConnector().prepareStatement("INSERT INTO invoicepdf VALUES(?,?)"); 
			
			
		    stmt.setInt(1,i);
			 stmt.setBinaryStream(2,is);
   			stmt.executeUpdate();
			
			
			   			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			
		}

	
public void getPdfFromDb(int value) {
		
	
	  ResultSet rs;
	    
	    try{
	    	Statement stmt = MySqlConn.dbConnector().createStatement();
	        String searchQuery = "SELECT * FROM `invoicepdf` WHERE `InvoiceID`="+value;
	        rs = stmt.executeQuery(searchQuery);
	        
	        //Blob blob;
	        
	        while(rs.next())
	        {
	                             this.id=rs.getInt("InvoiceID");
	                            
	                             this.pdf=rs.getBytes("Pdf");
	                             
	                             System.out.println(this.pdf);
	                             
	        }
			   			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			
		}
	
	
public  static void changePayment(String value, String b) {
		
	
	
	PreparedStatement stmt = null;
		try {
			
			//Statement stmt = MySqlConn.dbConnector().createStatement();
			
			 stmt = (PreparedStatement) MySqlConn.dbConnector().prepareStatement("UPDATE invoices SET PaidStatus=? WHERE ID="+value); 
			
		
				
			
		    stmt.setString(1,b);
   			stmt.executeUpdate();
			
			
			   			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			
		}
	
	
public static ArrayList<InvoiceD> selectUnpaidInvoices(int id2)
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` WHERE (`ClientID`= '"+id2+"' AND `PaidStatus`='N' )";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("invoices.ID"),
                     rs.getInt("ClientID"),
                     rs.getString("InvoiceDate"),
                     rs.getString("Status"),
                     rs.getDouble("Total"),
                     rs.getBoolean("PaidStatus")
                    );
     invoiceList.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceList;
}


public static  ArrayList<InvoiceD> contactsPaymDue(Date from,Date to)
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` WHERE (`PaidStatus`='N' AND `InvoiceDate` BETWEEN CAST('"+dateFormat2.format(from)+"' AS DATE) AND CAST('"+dateFormat2.format(to)+"' AS DATE))";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("invoices.ID"),
                     rs.getInt("ClientID"),
                     rs.getString("InvoiceDate"),
                     rs.getString("Status"),
                     rs.getDouble("Total"),
                     rs.getBoolean("PaidStatus")
                    );
     invoiceList.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceList;
}



public static  ArrayList<InvoiceD> contactsBA(Date from,Date to)
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` WHERE `InvoiceDate` BETWEEN CAST('"+dateFormat2.format(from)+"' AS DATE) AND CAST('"+dateFormat2.format(to)+"' AS DATE) ";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("invoices.ID"),
                     rs.getInt("ClientID"),
                     rs.getString("InvoiceDate"),
                     rs.getString("Status"),
                     rs.getDouble("Total"),
                     rs.getBoolean("PaidStatus")
                    );
     invoiceList.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceList;
}

public static  ArrayList<InvoiceD> salesPPI(Date from,Date to)
{
    ArrayList<InvoiceD> invoiceList = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoices` WHERE `InvoiceDate` BETWEEN CAST('"+dateFormat2.format(from)+"' AS DATE) AND CAST('"+dateFormat2.format(to)+"' AS DATE) ";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("invoices.ID"),
                     rs.getInt("ClientID"),
                     rs.getString("InvoiceDate"),
                     rs.getString("Status"),
                     rs.getDouble("Total"),
                     rs.getBoolean("PaidStatus")
                    );
     invoiceList.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceList;
}

public static  ArrayList<InvoiceD> prodSS(Date from,Date to)
{
    ArrayList<InvoiceD> invoiceitems = new ArrayList<InvoiceD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `invoiceitems` WHERE `DateInserted` BETWEEN CAST('"+dateFormat2.format(from)+"' AS DATE) AND CAST('"+dateFormat2.format(to)+"' AS DATE) ";
        rs = stmt.executeQuery(searchQuery);
        
        InvoiceD invoice;
        
        while(rs.next())
        {
        	 invoice = new InvoiceD(
                     rs.getInt("ProductID"),
                     rs.getString("ProductName"),
                     rs.getDouble("Quantity"),
                     rs.getDouble("Price"),
                     rs.getDouble("Tax")
                    
                    );

     invoiceitems.add(invoice);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return invoiceitems;
}


	
	
	
	
	
} //end InvoiceD
