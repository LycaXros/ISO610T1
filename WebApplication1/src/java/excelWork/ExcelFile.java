/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.services.CategoriaService;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jesus Dicent
 */
public class ExcelFile {

    private static ExcelFile _instance = null;
    private boolean Created;
    public static final String _filePath = "D:\\My Documents\\Personal\\Documentos\\ISO610T1.xlsx";

    public boolean wasCreated() {
        return Created;
    }

    private ExcelFile() {
        Created = false;
    }

    public static ExcelFile getInstance() {
        if (_instance == null) {
            _instance = new ExcelFile();
        }
        return _instance;
    }


    public void CheckIfExist() throws FileNotFoundException, IOException {
       
        File file = new File(_filePath);
        // Retrieve the workbook for the main report
        XSSFWorkbook workbook;
        // Check file existence 
        if (file.exists() == true) {
            try {
                FileInputStream is = new FileInputStream(file);
                workbook = new XSSFWorkbook(is);

                if (!CheckIfSheetExists(workbook, "Categoria")) {
                    this.CreateSheetCategoria(workbook);
                }
                if (!CheckIfSheetExists(workbook, "Producto")) {
                    this.CreateSheetProducto(workbook);
                }

                is.close();
                try (FileOutputStream outputStream = new FileOutputStream(_filePath)) {
                    workbook.write(outputStream);
                    workbook.close();
                }

                this.Created = true;

            } catch (IOException ex) {
                Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } catch (Exception ex) {

                Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        } else {
            workbook = new XSSFWorkbook();
            CreateSheetCategoria(workbook);
            CreateSheetProducto(workbook);
            
            try (FileOutputStream outputStream = new FileOutputStream(_filePath)) {
                workbook.write(outputStream);

                this.Created = true;
            }
            workbook.close();

        }

    }

    private boolean CheckIfSheetExists(XSSFWorkbook workbook, String hoja) {

        // Check if the workbook is empty or not
        if (workbook.getNumberOfSheets() != 0) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if(workbook.getSheetName(i).equals(hoja)) return true;
            }
        }
        return false;
    }

    private void CreateSheetCategoria(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Categoria");
        Row headers = sheet.createRow(0);

        headers.createCell(0).setCellValue("IdCategoria");
        headers.createCell(1).setCellValue("Nombre");
        headers.createCell(2).setCellValue("Descripcion");
        headers.createCell(3).setCellValue("Estado");
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        cellStyle.setBorderBottom(BorderStyle.DOUBLE);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setFontName("Bell MT");
        cellStyle.setFont(font);
        headers.getCell(0).setCellStyle(cellStyle);
        headers.getCell(1).setCellStyle(cellStyle);
        headers.getCell(2).setCellStyle(cellStyle);
        headers.getCell(3).setCellStyle(cellStyle);
    }
    
    
    private void CreateSheetProducto(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Producto");
        Row headers = sheet.createRow(0);
				
        headers.createCell(0).setCellValue("IdProducto");
        headers.createCell(1).setCellValue("IdCategoria");
        headers.createCell(2).setCellValue("Nombre");
        headers.createCell(3).setCellValue("PrecioVenta");
        headers.createCell(4).setCellValue("Stock");
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setBorderBottom(BorderStyle.DOUBLE);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setFontName("Courier New");
        
        cellStyle.setFont(font);
        headers.getCell(0).setCellStyle(cellStyle);
        headers.getCell(1).setCellStyle(cellStyle);
        headers.getCell(2).setCellStyle(cellStyle);
        headers.getCell(3).setCellStyle(cellStyle);
        headers.getCell(4).setCellStyle(cellStyle);
    }
    
    
    public static void removeRow(Sheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
        if (rowIndex == lastRowNum) {
            Row removingRow = sheet.getRow(rowIndex);
            if (removingRow != null) {
                sheet.removeRow(removingRow);
            }
        }
    }
}
