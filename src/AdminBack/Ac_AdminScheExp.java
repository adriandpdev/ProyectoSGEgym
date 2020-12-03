package AdminBack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPageEventHelper;

import AdminFront.V_AdminScheList;

public class Ac_AdminScheExp extends PdfPageEventHelper implements ActionListener {

	private static com.itextpdf.text.Font Titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 30, BaseColor.BLUE);
	private static com.itextpdf.text.Font redFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.RED);
	private static com.itextpdf.text.Font dias = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);
	private static com.itextpdf.text.Font actividades = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
	private static V_AdminScheList v;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser saveFile = new JFileChooser();
		saveFile.setFileFilter(new FileNameExtensionFilter(".pdf", ".PDF"));
		int result = saveFile.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			saveFile.setSelectedFile(new File(saveFile.getSelectedFile() + ".pdf"));
			File FILE = saveFile.getSelectedFile();
			try {
				v = new V_AdminScheList();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Document document = new Document();
				document.setPageSize(PageSize.A4.rotate());
				PdfWriter.getInstance(document, new FileOutputStream(FILE));
				document.open();
				addMetaData(document);
				addContent(document);
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Se ha exportado el horario correctamente");
		} else if (result == JFileChooser.CANCEL_OPTION) {
		    
		}
	}

	private static void addMetaData(Document document) {
		document.addTitle("Horario gimnasio");
	}

	private static void addContent(Document document) throws DocumentException {
		Paragraph preface = new Paragraph("Horario de la semana", Titulo);
		preface.setAlignment(Element.ALIGN_CENTER);
		Chapter catPart = new Chapter(1);
		catPart.add(preface);
		createTable(catPart);
		addEmptyLine(preface, 1);
		document.add(catPart);
	}

	private static void createTable(Chapter CatPart) throws BadElementException {
		PdfPTable t = new PdfPTable(8);
		for (int i = 0; i < v.getDiasemana().length; i++) {
			Phrase p = new Phrase(v.getDiasemana()[i], dias);
			// p.setFont(catFont);
			PdfPCell c = new PdfPCell(p);
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			c.setFixedHeight(35);
			t.addCell(c);
		}
		t.setHeaderRows(1);
		for (int i = 0; i < v.getActividades().length; i++) {
			for (int j = 0; j < v.getActividades()[i].length; j++) {
				Phrase p = new Phrase(v.getActividades()[i][j], actividades);
				PdfPCell c = new PdfPCell(p);
				c.setFixedHeight(60);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				t.addCell(c);
			}
		}
		t.setWidthPercentage(100);
		t.setSpacingAfter(5f);
		t.setSpacingBefore(5f);
		CatPart.add(t);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}