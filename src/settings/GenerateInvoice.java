package settings;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;

import contacts.ContactsD;
import invoice.InvoiceD;


public class GenerateInvoice {
	
	private double f;
	private double ff;
	private double f24;
	private double f13;
	private double f6;
	private ArrayList<InvoiceD> invoiceitems;
	private ArrayList<ContactsD> contact;
	public ArrayList<InvoiceD> invoice;
	private Font font;
	private ContactsD mycompany;
	
	public static final String FONT = "./src/fonts/arialuni.ttf";
	public static final String DEST = "C:\\Users\\mario\\Desktop\\back up\\m.pdf\\";
	
	
	public GenerateInvoice(double f, double ff, double f24, double f13, double f6, ArrayList<InvoiceD> invoiceitems,
			ArrayList<ContactsD> contact, ArrayList<InvoiceD> invoice) {
		
		this.f=f;
		this.ff=ff;
		this.f24=f24;
		this.f13=f13;
		this.f6=f6;
		this.invoiceitems=invoiceitems;
		this.contact=contact;
		this.invoice = invoice;
		
		
	}



	public void genpdf() {

			
	
	try {
	/*
		File file = new File(DEST);
        file.getParentFile().mkdirs();
		   Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(DEST));
	        document.open();
		
		*/
		   Document document = new Document();
		   
		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
           PdfWriter.getInstance(document, baos);
		   
	      //  PdfWriter.getInstance(document, new FileOutputStream(DEST));
	        document.open();
		
		
		
		font = FontFactory.getFont(FONT,BaseFont.IDENTITY_H , true);
		
		
		mycompany =new ContactsD();
		mycompany.getMyCompany();
		 
		
		 addTitle(document);
		 addCompClient(document);
		 addTable(document);
		 addSum(document);
		 
		 
		 document.close();
		 InvoiceD.addPdfToDb(invoice.get(0).getId(),baos);
		
		 
		
	} catch (IOException | DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	
	public void addTitle(Document layoutDocument) throws DocumentException, IOException
	{	
		 Image img=Image.getInstance(mycompany.getLogo());
		img.scaleAbsolute(150f, 100f);
		
		PdfPTable table = new PdfPTable(3);
		 
		table.setWidthPercentage(100);
		
		 PdfPCell cell = new PdfPCell(img);
		 cell.setFixedHeight(100);
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 
		 
		 cell = new PdfPCell(new Phrase(""));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 Paragraph par =new Paragraph("ΤΙΜΟΛΟΓΙΟ",font);
		 par.add("\n\n");
		 
		 par.add("Αρ.Παραστατικου : " + invoice.get(0).getId());
		 par.add("\n\n");
		 par.add("Ημερομ.Εκδοσης : " + invoice.get(0).getInvoiceDate());
		 cell = new PdfPCell(par);
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 layoutDocument.add(table);
		//table.addCell(getCell("Text in the middle", PdfPCell.ALIGN_CENTER));
		//table.addCell(getCell("Text to the right", PdfPCell.ALIGN_RIGHT));
		
	    
	   
	    

	 
	    
	    
	}
	
	
	public void addCompClient(Document layoutDocument) throws DocumentException
	{
		
		PdfPCell cell;
		PdfPTable table = new PdfPTable(2);
		 
		table.setWidthPercentage(100);
		
		
		 cell = new PdfPCell(new Paragraph("ΑΠΟ",font));
			cell.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell);
			 
			 cell = new PdfPCell(new Paragraph("ΠΕΛΑΤΗΣ",font));
				cell.setBorder(Rectangle.NO_BORDER);
				 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				 table.addCell(cell);

		
		 layoutDocument.add(table);
					
		
		
		
		Paragraph n =new Paragraph(mycompany.getCtitle(),font);
		
		layoutDocument.add(n);
		
		
		table = new PdfPTable(2);
		 
		table.setWidthPercentage(100);
		
		
	

		 
		//row1
		 cell = new PdfPCell(new Paragraph(mycompany.getCname(),font));
		cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		
		 cell = new PdfPCell(new Paragraph(contact.get(0).getCname(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		 //row2
		 cell = new PdfPCell(new Paragraph(mycompany.getOc(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		
		 cell = new PdfPCell(new Paragraph(contact.get(0).getOc(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		 //row3
		 cell = new PdfPCell(new Paragraph("ΑΦΜ: "+ mycompany.getAfm()+" ΔΟΥ:  " + mycompany.getDoy(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 cell = new PdfPCell(new Paragraph("ΑΦΜ: "+ contact.get(0).getAfm() +" ΔΟΥ:  "+ contact.get(0).getDoy(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		 //row4
		 cell = new PdfPCell(new Paragraph(mycompany.getStreet(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 cell = new PdfPCell(new Paragraph(contact.get(0).getStreet(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		//row6
		 cell = new PdfPCell(new Paragraph(mycompany.getTown()+" Τ.Κ: "+mycompany.getZip(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 cell = new PdfPCell(new Paragraph(contact.get(0).getTown()+" Τ.Κ: "+mycompany.getZip(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
			//row7
		 cell = new PdfPCell(new Paragraph("Τηλ1 "+mycompany.getTel() +" Τηλ2 "+mycompany.getTel2(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 cell = new PdfPCell(new Paragraph("" ,font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		//row8
		 cell = new PdfPCell(new Paragraph("Κιν "+mycompany.getMob() +" fax "+mycompany.getFax(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 cell = new PdfPCell(new Paragraph("" ,font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		//row9
		 cell = new PdfPCell(new Paragraph("email "+mycompany.getEmail(),font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 table.addCell(cell);
		 
		 cell = new PdfPCell(new Paragraph("" ,font));
		 cell.setBorder(Rectangle.NO_BORDER);
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(cell);
		 
		 
		 layoutDocument.add(table);
		
			
			
	   
	}
	
	
	public void addTable(Document layoutDocument) throws DocumentException
	{
		
		Paragraph n =new Paragraph("\n");
		layoutDocument.add(n);
		
		
		PdfPTable table = new PdfPTable(new float[] { 3,1,1,1,1 });
		 
		table.setWidthPercentage(100);

	    // headers
	    table.addCell(new Paragraph("ΠΡΟΙΟΝ",font));
	    table.addCell(new Paragraph("ΠΟΣΟΤΗΤΑ",font));
	    table.addCell(new Paragraph("TIMH",font));
	    table.addCell(new Paragraph("ΦΠΑ",font));
	    table.addCell(new Paragraph("ΣΥΝΟΛΟ",font));

	    // items
	    for(int i = 0; i < invoiceitems.size(); i++)
        {
	        table.addCell(new Paragraph(invoiceitems.get(i).getpName(),font));
	        table.addCell(new Paragraph(String.valueOf(invoiceitems.get(i).getpQuantity())));
	        table.addCell(new Paragraph(String.valueOf(invoiceitems.get(i).getpPrice())));
	        table.addCell(new Paragraph(String.valueOf(invoiceitems.get(i).getpTax())));
	        table.addCell(new Paragraph(String.valueOf(invoiceitems.get(i).getpQuantity() * invoiceitems.get(i).getpPrice())));
	    }

	    layoutDocument.add(table);
	}
	
	
	
	public void addSum(Document layoutDocument) throws DocumentException
	{
		
		Paragraph n =new Paragraph("\n");
		layoutDocument.add(n);
		
		
		PdfPTable table = new PdfPTable(new float[] {4,2 });
		 
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);
	
			if( f24!= 0) {
				 table.addCell(new Paragraph("ΦΠΑ 24",font));
				 table.addCell(String.valueOf(f24));
			}
			if( f13!= 0) {
				 table.addCell(new Paragraph("ΦΠΑ 13",font));
				 table.addCell(String.valueOf(f13));
			}
			if( f6!= 0) {
				 table.addCell(new Paragraph("ΦΠΑ 6",font));
				 table.addCell(String.valueOf(f6));
			}
			
			
			 table.addCell(new Paragraph("ΣΥΝΟΛΟ ΦΠΑ",font));
			 table.addCell(String.valueOf(f24+f13+f6));
			 
			 table.addCell(new Paragraph("ΣΥΝΟΛΟ ΠΡΟΙΟΝΤΩΝ",font));
			 table.addCell(String.valueOf(ff));
			 
			 table.addCell(new Paragraph("ΤΕΛΙΚΟ ΣΥΝΟΛΟ",font));
			 table.addCell(String.valueOf(f));
			
		   
		    
		layoutDocument.add(table);
	
}
	
}
