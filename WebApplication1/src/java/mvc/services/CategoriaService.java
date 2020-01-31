/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.services;

import excelWork.ExcelFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.Categoria;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jesus Dicent
 */
public class CategoriaService {

    private static int _count = 0;
    private static int _lastId = 0;

    public CategoriaService() {
        try {
            ExcelFile.getInstance().CheckIfExist();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Categoria> GetCategorias() {
        XSSFWorkbook libro;
        try {
            FileInputStream f = new FileInputStream(ExcelFile._filePath);

            libro = new XSSFWorkbook(f);
            XSSFSheet hoja = libro.getSheet("Categoria");
            Iterator<Row> filas = hoja.iterator();
            Row row;
            ArrayList<Categoria> c = new ArrayList<>();
            this._count = 0;
            this._lastId = 0;
            while (filas.hasNext()) {
                row = filas.next();
                int index = row.getRowNum();
                CategoriaService._count += 1;
                if (index == 0) {
                    continue;
                }

                int id = (int) row.getCell(0).getNumericCellValue();
                CategoriaService._lastId = id;
                String nombre = row.getCell(1).getStringCellValue();
                String desc = row.getCell(2).getStringCellValue();
                String estado = row.getCell(3).getStringCellValue();
                c.add(new Categoria(id, index, nombre, desc, estado));

            }
            libro.close();

            return c;
        } catch (FileNotFoundException ex) {
            System.out.println("HOLA");
            System.out.println(ex.getMessage());
        } catch (IOException | org.apache.poi.xssf.XLSBUnsupportedException ex) {
            System.out.println("HOLA");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param Id
     * @return
     */
    public List<Categoria> GetCategoriasById(int Id) {
        ArrayList<Categoria> lista = new ArrayList<>();
        List<Categoria> l = this.GetCategorias();
        Categoria c = new Categoria("", "", "");
        for (Categoria categoria : l) {
            if (categoria.getIdCategoria() == Id) {
                c = categoria;
                break;
            }
        }
        lista.add(c);
        return lista;
    }

    public void DeleteCategoria(int Id) {
        List<Categoria> l = this.GetCategorias();
        Categoria c = new Categoria("", "", "");
        for (Categoria categoria : l) {
            if (categoria.getIdCategoria() == Id) {
                c = categoria;
                break;
            }
        }
        int rowIndex = c.getRowIndex();

        try {
            FileInputStream inputStream = new FileInputStream(ExcelFile._filePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("Categoria");
            ExcelFile.removeRow(sheet, rowIndex);

            inputStream.close();
            try (FileOutputStream outputStream = new FileOutputStream(ExcelFile._filePath)) {
                workbook.write(outputStream);
                workbook.close();
            }

        } catch (Exception ex) {

        }
    }

    public void SetCategoria(Categoria categoria) {
        int id = categoria.getIdCategoria();

        if (id == 0) {
            categoria.setIdCategoria(CategoriaService._lastId + 1);
            System.out.println("Nuevo");
            this.NewEntry(categoria);
        } else {
            System.out.println("editando");
            try {
                FileInputStream inputStream = new FileInputStream(ExcelFile._filePath);
                Workbook workbook = new XSSFWorkbook(inputStream);

                Sheet sheet = workbook.getSheet("Categoria");

                Row r = sheet.getRow(categoria.getRowIndex());
                r.getCell(0).setCellValue(categoria.getIdCategoria());
                r.getCell(1).setCellValue(categoria.getNombre());
                r.getCell(2).setCellValue(categoria.getDescripcion());
                r.getCell(3).setCellValue(categoria.getEstado());

                inputStream.close();
                try (FileOutputStream outputStream = new FileOutputStream(ExcelFile._filePath)) {
                    workbook.write(outputStream);
                    workbook.close();
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    private void NewEntry(Categoria categoria) {
        try {
            FileInputStream inputStream = new FileInputStream(ExcelFile._filePath);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheet("Categoria");

            int rowCount = sheet.getLastRowNum() + 1;
            Row r = sheet.createRow(rowCount);
            r.createCell(0).setCellValue(categoria.getIdCategoria());
            r.createCell(1).setCellValue(categoria.getNombre());
            r.createCell(2).setCellValue(categoria.getDescripcion());
            r.createCell(3).setCellValue(categoria.getEstado());

            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBold(false);
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Calibri");
            cellStyle.setFont(font);
            r.getCell(0).setCellStyle(cellStyle);
            r.getCell(1).setCellStyle(cellStyle);
            r.getCell(2).setCellStyle(cellStyle);
            r.getCell(3).setCellStyle(cellStyle);

            inputStream.close();
            try (FileOutputStream outputStream = new FileOutputStream(ExcelFile._filePath)) {
                workbook.write(outputStream);
                workbook.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
