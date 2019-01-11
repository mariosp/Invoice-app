/*
 *Apache License, Version 2.0
 */

package gr.gspr.webservicealldatadesktopclient.util;

import java.text.ParseException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.ImageIcon;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 *
 * @author j.vlachos@gsis.gr, d.vafiadis@gsis.gr
 */

public class Utils {
    public final static String xml = "xml";
    public final static String pdf = "pdf";
    public final static String jpg = "jpg";
    public final static String jpeg = "jpeg";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String zip = "zip";
    public final static String xls = "xls";
    public final static String xlsx = "xlsx";
    public final static String csv = "csv";
    private static final String charset = "0123456789";
    private static final String charset2 = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";

    public static String getRandomString(int length,String charSet) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charSet.length());
            sb.append(charSet.charAt(pos));
        }
        return sb.toString();
    }


    public static String makeZeros(int zeronum) {
		String zeros = "";
		for (int i = 1; i <= zeronum; i++) {
			zeros += "0";
		}
		return zeros;
	}


    public static Element createElement(Node f) {
        Element element=(Element) f;
        return element;
    }


    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    public static String substituteString(String texttbp, String stbefore,
                    String stafter) {

            String tbptext = texttbp;
            String part1, part2;
            int i = 0, j = 0;
            while (tbptext.indexOf(stbefore) != -1) {
                    // System.out.println(i);
                    i = tbptext.indexOf(stbefore);
                    part1 = tbptext.substring(0, i);
                    part2 = tbptext.substring(part1.length() + stbefore.length(),
                                    tbptext.length());
                    tbptext = part1 + stafter + part2;
                    // System.out.println(tbptext);
            }
            return tbptext;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    
    public static void createFiles(File f){
        try {
            if(!f.getParentFile().exists()){
                createParents(f.getParentFile());
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    private static void createParents(File file) {
        if(!file.exists()){
            createParents(file.getParentFile());
            file.mkdir();
        }
    }

    public static boolean isEmailValid(String email){
        boolean isValid = false;

        //Initialize reg ex for email.
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        //Make the comparison case-insensitive.

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;


    }

      public static boolean unzip(final ZipInputStream zis, final String dir_for_output,Enumeration entries) {

                   try {

                       ZipEntry entry = zis.getNextEntry();

                       if (entry == null) {
                           zis.close();

                           return true;
                       }

                       ZipEntry ent = ((ZipEntry)entries.nextElement());
                       final int size = (int) ent.getSize();

                       String filename = dir_for_output + File.separatorChar +entry.getName();

                       createFiles(new File(filename));

                       FileOutputStream fos = new FileOutputStream(filename);

                       
                       byte[] buffer = new byte[0xFFFF];

                       for (int len; (len = zis.read(buffer)) != -1; ) {

                           fos.write(buffer, 0, len);

                       }

                       fos.flush();
                       fos.close();



                       boolean resultOfMoreCalls=unzip(zis, dir_for_output,entries);
                       if (!resultOfMoreCalls){
                           return false;
                       }
                   } catch (Exception x) {
                       x.printStackTrace();
                       
                       return false;
                   }

                   
                   return true;
   }




	public static boolean inDateRange(Calendar dateToBeChecked, Calendar dateFrom, Calendar dateTo){

		if(dateToBeChecked==null)
                {
                    
			return false;
            }
		return dateToBeChecked.compareTo(dateFrom) >=0 && dateToBeChecked.compareTo(dateTo) <=0;

	}

        public static boolean checkAFM(String afm) {
		if (afm == null) {
			return false;
		}

                if (afm.equals("000000000")) {
			return false;
		}

		String chkafm = "afm accepted";
		int i, sum;
		int afmdigits[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		boolean afmchk = true;
		boolean afmchkletter = true;
		boolean afmchkdigit = true;
		boolean result;

		sum = 0;

		for (i = 0; i < afm.length(); i++) {
			if (!Character.isDigit(afm.charAt(i)))
				afmchkletter = false;
		}

		if (afmchkletter == true) {
			if ((afm.length() != 9) || (afm.equals(""))) {
				afmchk = false;
				afmchkdigit = false;
			} else {
				for (i = 0; i < 8; i++) {
					afmdigits[i] = Integer.valueOf(afm.substring(i, i + 1))
							.intValue();
					;
					sum += afmdigits[i] * (int) java.lang.Math.pow(2, 8 - i);
				}
				afmdigits[8] = Integer.valueOf(afm.substring(8, 9)).intValue();
				int ypol = sum % 11;

				if ((ypol == 10 && afmdigits[8] != 0)
						|| ((ypol < 10) && (ypol != afmdigits[8])))
					afmchk = false;
			}
		}

		result = afmchk && afmchkletter && afmchkdigit;

		
		return result;
	}

	public static boolean checkDate(String dateString) {
		Date dat1;
		try {
			
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			dat1 = sdf.parse(dateString);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean checkInt(String intString) {
		
		try {
			
			Integer.parseInt(intString);
			return true;
		} catch (NumberFormatException e) {
			
			return false;
		}
	}

	public static Date toMidnight(Date incomingDate) throws Exception {

		Calendar cal = Calendar.getInstance();
		cal.setTime(incomingDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return (cal.getTime());
	}

	public static String convertCharsets(String fromCharset, String toCharset,
			String instring) {
		String outstring = "";

		if (instring == null) {
			return instring;
		}

		byte[] bs = null;

		try {
			bs = instring.getBytes(fromCharset);
			outstring = new String(bs, toCharset);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}

		return outstring;
	}

	

	public static boolean isBeforeDeadline(Date deadline) {

		Calendar deadlineCal = Calendar.getInstance();
		deadlineCal.setTime(deadline);

		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(new Date());

		if (currentDate.after(deadlineCal)) {

			return false;

		} else {
			return true;
		}

	}


    /*

     * Method that checks if an amka is syntactically correct (e.g. 12310237149 is a correct one)

     *

     */
    public static boolean checkAMKA(String amka) {

        if (amka == null) {
			return false;
		}

		int i, sum;
		int amkadigits[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int checkDigit;
		boolean amkachk = true;
		boolean amkachkletter = true;
		boolean amkachkday = true;
		boolean amkachkmonth = true;
		boolean result;

		sum = 0;

		for (i = 0; i < amka.length(); i++) {
			//System.out.println("Char ["+i+"] is "+amka.charAt(i));
			if (!Character.isDigit(amka.charAt(i))){
				//System.out.println("No Char ["+i+"] is "+amka.charAt(i));
				amkachkletter = false;
			}
		}

		if (amkachkletter == true) {
			if ((amka.length() != 11) || (amka.equals(""))) {
				amkachk = false;
				amkachkletter = false;
			} else {
				for (i = 0; i < 10; i++) {
					amkadigits[i] = Integer.valueOf(amka.substring(i, i + 1))
							.intValue();
					;
					if (i % 2 == 1)
					{
						if (amkadigits[i] < 5 ) sum += amkadigits[i]*2;
						else {
							
							sum = sum + 1 + ((amkadigits[i]*2) % 10);
						}
					}
					else sum += amkadigits[i];
					
				}

				
				checkDigit = Integer.valueOf(amka.substring(10, 11)).intValue();
				int ypol = 10 - sum % 10;
				
				if ((ypol == 10 && checkDigit != 0)
						|| ((ypol !=10) && (ypol != checkDigit)))
				{
					
					amkachk = false;
				}
			}

			// Check if first 2 digits are between 00 and 31
			// and next two digits are between 01 and 12
			if (amkachk && amkachkletter){
				int day = Integer.valueOf(amka.substring(0, 2));
				if (day > 31 || day < 0 ) amkachkday = false;
				int month = Integer.valueOf(amka.substring(2, 4));
				if (month > 12 || month < 1) amkachkmonth = false;
				
			}
		}

		result = amkachk && amkachkletter && amkachkday && amkachkmonth;
		
		return result;

    }
	

	public static String removeOneAccent(String lexi, String gramma,
			String neogramma) {

		while (lexi.indexOf(gramma) >= 0) {
			lexi = lexi.substring(0, lexi.indexOf(gramma)) + neogramma
					+ lexi.substring(lexi.indexOf(gramma) + 1, lexi.length());
		}

		return lexi;

	}



}
