package AdminBack;




import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;

import AdminFront.V_AdminScheList;

import java.io.FileOutputStream;
import java.util.Date;


public class Ac_AdminScheExp implements ActionListener{
	
	private static String FILE = "./horario.pdf";
    private static com.itextpdf.text.Font catFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
    private static com.itextpdf.text.Font redFont =FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.RED);
    private static com.itextpdf.text.Font subFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
    private static com.itextpdf.text.Font smallBold =FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
    private static V_AdminScheList v;
 

 

    @Override
    public void actionPerformed(ActionEvent arg0) {
    	try {
			v=new V_AdminScheList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        
    }

    

    private static void addContent(Document document) throws DocumentException {
    	 Paragraph preface = new Paragraph();
         // We add one empty line
        
         // Lets write a big header
         preface.add(new Paragraph("Horario de la semana"));
         preface.setFont(catFont);
         
         
        Chapter catPart = new Chapter(1);
       
        createTable(catPart);

        // now add all this to the document
        document.add(preface);
        document.add(catPart);

       

    }

    private static void createTable(Chapter CatPart)
            throws BadElementException {
        PdfPTable t = new PdfPTable(8);

        

        for (int i = 0; i < v.getDiasemana().length; i++) {
        	Phrase p=new Phrase(v.getDiasemana()[i]);
        	p.setFont(smallBold);
			PdfPCell c= new PdfPCell(p);
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			t.addCell(c);
		}
        
        
        t.setHeaderRows(1);

        for (int i = 0; i < v.getActividades().length; i++) {
			for (int j = 0; j < v.getActividades()[i].length; j++) {
				PdfPCell c= new PdfPCell(new Phrase(v.getActividades()[i][j]));
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				t.addCell(c);
			}
		}

        CatPart.add(t);

    }



    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}