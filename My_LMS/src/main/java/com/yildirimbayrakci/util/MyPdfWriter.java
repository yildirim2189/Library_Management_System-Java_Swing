/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.util;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.yildirimbayrakci.lms.CustomDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author YILDIRIM
 */
public class MyPdfWriter {

    public void write(JTable jtable) {

        JFileChooser fileChooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if(f.exists() && getDialogType() == SAVE_DIALOG){
                    int result = CustomDialog.showDialog(null, "<html>\"" + f.getName() + "\""
                    + "<br/>Dosya ismi mevcut. Üzerine yazmak istiyor musunuz?</html>");
                    
                    if(result == JOptionPane.YES_OPTION){
                        super.approveSelection();
                        return;
                    }else
                        return;
                    
                }
                super.approveSelection(); //To change body of generated methods, choose Tools | Templates.
            }

        };

        
        // Title of dialog.
        fileChooser.setDialogTitle("Dosyayı Kaydet");

        // Filtering extensions
        FileFilter filter = new FileNameExtensionFilter("PDF Dosyası", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        // Selection Mode: FILES
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Setting default file name
        Date currentDate = new Date();
        String date = DateUtils.formatDateHyphen(currentDate);
        String name;
        if (jtable.getName() != null) {
            name = jtable.getName();
        } else {
            name = "Report";
        }
        fileChooser.setSelectedFile(new File(name + " " + date + ".pdf"));

        // Show dialog
        int userSelection = fileChooser.showSaveDialog(null);

        // If approved write and save
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            PdfWriter writer;
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                writer = new PdfWriter(filePath);

                PdfDocument pdfDocument = new PdfDocument(writer);
                Document document = new Document(pdfDocument, PageSize.A4);

                int columnCount = jtable.getColumnCount();

                // float[] pointColumnWidth = {5f, 10f, 20f, 20f, 10f, 7f, 7f};
                Table table = new Table(columnCount, false).useAllAvailableWidth();

                // Font Settings
                PdfFont font = PdfFontFactory.createRegisteredFont("times-roman", "Cp1254", true);
                table.setFont(font);
                table.setFontSize(8f);

                // Add table column titles
                for (int i = 0; i < columnCount; i++) {
                    Cell cell = new Cell().add(new Paragraph(jtable.getColumnName(i)))
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold();

                    table.addHeaderCell(cell);
                }

                // Add table data
                for (int rows = 0; rows < jtable.getRowCount(); rows++) {
                    for (int cols = 0; cols < jtable.getColumnCount(); cols++) {

                        try {
                            table.addCell(jtable.getValueAt(rows, cols).toString());
                        } catch (NullPointerException exc) {
                            table.addCell("");
                        }
                    }
                }

                // Add table to document
                document.add(table);

                document.close();
                writer.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PdfWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PdfWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
