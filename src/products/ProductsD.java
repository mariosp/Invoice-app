package products;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import util.MySqlConn;

public class ProductsD {
	
	
	
	private int id;
	private String name;
	private double price;
	private String prodcode;
	private String description;
	private int tax;
	private boolean sales;
	private boolean stock;
	private String datecreated;
	private String dateedited;
	public static double total;
	public static String dateeditedinventory;

	


	public ProductsD(int Id,String Name,double Price)
    {
        this.id = Id;
        this.name = Name;
        this.price = Price;
       
    }
	
	
	 public ProductsD(int Id,String Name,String ProdCode,String Description,double Price,int Tax,boolean Sales,boolean Stock,String DateCreated,String DateEdited)
	    {
	        this.id = Id;
	        this.name = Name;
	        this.prodcode =ProdCode ;
	        this.description = Description;
	        this.price = Price;
	        this.tax = Tax;
	        this.sales = Sales;
	        this.stock = Stock;
	        this.datecreated = DateCreated;
	        this.dateedited = DateEdited;
	        
	       
	    }

	
	 public int getId()
	    {
	        return id;
	    }
	    
	    public String getName()
	    {
	        return name;
	    }
	    
	    public String getProdCode()
	    {
			return prodcode;
	    }
	    
	    public String getDescription()
	    {
			return description;
	    }
	    
	    
	    public double getPrice()
	    {
	        return price;
	    }

	    public int getTax()
	    {
			return tax;
	    }
	    
	    public boolean getSales()
	    {
			return sales;
	    }
	    public boolean getStock()
	    {
			return stock;
	    }

	   
	    public String getDatecreated()
	    {
			return datecreated;
	    }
	    
	    public String getDateEdited()
	    {
			return dateedited;
	    }
	 
	    public double getTotal() {
	    	return total;
	    }
	    
	    public String getDateEditedInventory()
	    {
			return dateeditedinventory;
	    }
	 
	
	
public static void addEntry(JTextField tname, JTextField tpcode, JTextField tdes, JTextField tprice, JComboBox comboBox, JCheckBox tstock) {
		
	char s;
			String cmb = (String) comboBox.getSelectedItem();
			if (tstock.isSelected()){
			 s = 'T';
			}else {
				 s='F';
			}
			
		try {
			Statement stmt = MySqlConn.dbConnector().createStatement();
			
			   String sql = "INSERT INTO products(ID,Name,ProdCode,Description,Price,Tax,Sales,Stock) VALUES ("
	            		+ "NULL" +",'"+
	            		tname.getText() + "', '" +
	                    tpcode.getText() + "', '" +
	                    tdes.getText() + "', '" +
	                    tprice.getText() + "', '" +
	                    cmb + "', '" +
	                    "T" + "', '" +
	                    s+ "')";
			   
			   			stmt.executeUpdate(sql ,Statement.RETURN_GENERATED_KEYS);
			   			
			   			
			   			
		
			   		 ResultSet rs = stmt.getGeneratedKeys();
		                if(rs.next())
		                {
		                    int last_inserted_id = rs.getInt(1);
		                    addInventory(last_inserted_id,0);
		                }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}


public static void updateEntry(int id ,JTextField tname, JTextField tpcode, JTextField tdes, JTextField tprice, JComboBox comboBox, JCheckBox tstock) {
	
	char s;
	String cmb = (String) comboBox.getSelectedItem();
	if (tstock.isSelected()){
	 s = 'T';
	}else {
		 s='F';
	}
	
try {
	Statement stmt = MySqlConn.dbConnector().createStatement();
	
	   String sql = "UPDATE products SET Name='" 
			   	+ tname.getText() + "', ProdCode='" 
			   	+ tpcode.getText() + "', Description='" 
			   	+ tdes.getText() + "', Price='" 
               + tprice.getText() + "', Tax='"
               + cmb + "', Stock='"
               + s +  "' WHERE ID="+ id ;
	   
	   			stmt.executeUpdate(sql);

	   		 
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 

	
	
}


public static void updateSS(char t,String r,int id) {
	
	try {
		Statement stmt = MySqlConn.dbConnector().createStatement();
	 String sql = "UPDATE products SET "+ r +"='" + t +"' WHERE ID="+ id ;
	   
	   			stmt.executeUpdate(sql);
	   			
	   			
	}
	
	   		 catch (SQLException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		} 
	
}


public static void addInventory(int id,double z) {
	
	
	try {
		Statement stmt = MySqlConn.dbConnector().createStatement();
		
		   String sql = "INSERT INTO inventory(ID,ID_Inventory,Total) VALUES ("
            		+ "NULL" +",'"+
            		id + "', '" +
                   z +"')";
		   
		   			stmt.executeUpdate(sql);
	
		   			
		   			
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	
}


public static void updateInventory(double t,int id) {
	
	System.out.println(t);
	try {
		Statement stmt = MySqlConn.dbConnector().createStatement();
	 String sql = "UPDATE inventory SET Total='" + t +"' WHERE ID_Inventory="+ id ;
	   			stmt.executeUpdate(sql);
	   			total=t;
	   			
	}
	
	   		 catch (SQLException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		} 
	
}

public static double showInventory(int id) {
	 ResultSet rs;
	
	try {
		Statement stmt = MySqlConn.dbConnector().createStatement();
		
		String searchQuery = "SELECT * FROM `inventory` WHERE (`ID_Inventory`= '"+id+"' )";
        rs = stmt.executeQuery(searchQuery);

        while(rs.next())
        {
        	 
                        total=  rs.getDouble("Total");
                        dateeditedinventory = rs.getString("DateEdited");
                       
                       
        }
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return total;
}











public static ArrayList<ProductsD> ListProducts(String ValToSearch)
{
    ArrayList<ProductsD> productsList = new ArrayList<ProductsD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `products` WHERE (`Name` LIKE '%"+ValToSearch+"%' OR `ProdCode`LIKE '%"+ValToSearch+"%' )";
        rs = stmt.executeQuery(searchQuery);
        
        ProductsD product;
        
        while(rs.next())
        {
            product = new ProductsD(
                             rs.getInt("ID"),
                             rs.getString("Name"),
                             rs.getDouble("Price")
                            );
            productsList.add(product);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return productsList;
}

public static ArrayList<ProductsD> FindProductsD(String ValToSearch)
{
    ArrayList<ProductsD> productsList = new ArrayList<ProductsD>();
    
    ResultSet rs;
    
    try{
    	Statement stmt = MySqlConn.dbConnector().createStatement();
        String searchQuery = "SELECT * FROM `products` WHERE (`ID`= '"+ValToSearch+"' )";
        rs = stmt.executeQuery(searchQuery);
        
        ProductsD product;
        
        while(rs.next())
        {
            product = new ProductsD(
                             rs.getInt("ID"),
                             rs.getString("Name"),
                             rs.getString("ProdCode"),
                             rs.getString("Description"),
                             rs.getDouble("Price"),
                             rs.getInt("Tax"),
                             rs.getBoolean("Sales"),
                             rs.getBoolean("Stock"),
                             rs.getString("DateCreated"),
                             rs.getString("DateEdited")
                             
                             
                            );
            productsList.add(product);
        }
        
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return productsList;
}






}

