/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.services;

import excelWork.ExcelFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.Categoria;
import mvc.model.Producto;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jesus Dicent
 */
public class ProductosService {

    private static int _count = 0;
    private static int _lastId = 0;

    public ProductosService() {
        try {
            ExcelFile.getInstance().CheckIfExist();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Producto> GetProductos() {
        XSSFWorkbook libro;
        try {
            FileInputStream f = new FileInputStream(ExcelFile._filePath);

            libro = new XSSFWorkbook(f);
            XSSFSheet hoja = libro.getSheet("Producto");
            Iterator<Row> filas = hoja.iterator();
            Row row;
            ArrayList<Producto> c = new ArrayList<>();
            this._count = 0;
            this._lastId = 0;
            while (filas.hasNext()) {
                row = filas.next();
                int index = row.getRowNum();
                ProductosService._count += 1;
                if (index == 0) {
                    continue;
                }

                int id = (int) row.getCell(0).getNumericCellValue();
                ProductosService._lastId = id;
                int catID = (int) row.getCell(1).getNumericCellValue();
                String nombre = row.getCell(2).getStringCellValue();
                double precio = row.getCell(3).getNumericCellValue();
                int stock = (int) row.getCell(4).getNumericCellValue();
                c.add(new Producto(id, index, catID, nombre, precio, stock));

            }
            libro.close();

            return c;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | org.apache.poi.xssf.XLSBUnsupportedException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Producto> GetProductosById(int Id) {
        ArrayList<Producto> lista = new ArrayList<>();
        List<Producto> l = this.GetProductos();
        Producto c = new Producto(0, "", 0, 0);
        for (Producto pro : l) {
            if (pro.getIdProducto() == Id) {
                c = pro;
                break;
            }
        }
        lista.add(c);
        return lista;
    }

    public void SetProducto(Producto producto) {
        int id = producto.getIdCategoria();

        if (id == 0) {
            producto.setIdProducto(ProductosService._lastId + 1);
            System.out.println("Nuevo");
            this.NewEntry(producto);
        } else {
            System.out.println("editando");
            try {
                FileInputStream inputStream = new FileInputStream(ExcelFile._filePath);
                Workbook workbook = new XSSFWorkbook(inputStream);

                Sheet sheet = workbook.getSheet("Producto");

                Row r = sheet.getRow(producto.getRowIndex());
                r.createCell(0).setCellValue(producto.getIdProducto());
                r.createCell(1).setCellValue(producto.getIdCategoria());
                r.createCell(2).setCellValue(producto.getNombre());
                r.createCell(3).setCellValue(producto.getPrecioVenta());
                r.createCell(4).setCellValue(producto.getStock());

                SetCellStyle(sheet, r);
                
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
    
    public void DeleteProducto(int Id) {
        List<Producto> l = this.GetProductos();
        Producto c = new Producto(0, "", 0, 0);
        for (Producto pro : l) {
            if (pro.getIdProducto() == Id) {
                c = pro;
                break;
            }
        }
        int rowIndex = c.getRowIndex();

        try {
            FileInputStream inputStream = new FileInputStream(ExcelFile._filePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("Producto");
            ExcelFile.removeRow(sheet, rowIndex);

            inputStream.close();
            try (FileOutputStream outputStream = new FileOutputStream(ExcelFile._filePath)) {
                workbook.write(outputStream);
                workbook.close();
            }

        } catch (Exception ex) {

                System.out.println(ex.getMessage());
        }
    }

    private void NewEntry(Producto producto) {
        try {
            FileInputStream inputStream = new FileInputStream(ExcelFile._filePath);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheet("Producto");

            int rowCount = sheet.getLastRowNum() + 1;
            Row r = sheet.createRow(rowCount);
            r.createCell(0).setCellValue(producto.getIdProducto());
            r.createCell(1).setCellValue(producto.getIdCategoria());
            r.createCell(2).setCellValue(producto.getNombre());
            r.createCell(3).setCellValue(producto.getPrecioVenta());
            r.createCell(4).setCellValue(producto.getStock());

            SetCellStyle(sheet, r);

            inputStream.close();
            try (FileOutputStream outputStream = new FileOutputStream(ExcelFile._filePath)) {
                workbook.write(outputStream);
                workbook.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void SetCellStyle(Sheet sheet, Row r) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.DOUBLE);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font font = sheet.getWorkbook().createFont();
        font.setBold(false);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Calibri");
        cellStyle.setFont(font);
        r.getCell(0).setCellStyle(cellStyle);
        r.getCell(1).setCellStyle(cellStyle);
        r.getCell(2).setCellStyle(cellStyle);
        r.getCell(3).setCellStyle(cellStyle);
        r.getCell(4).setCellStyle(cellStyle);
    }
}
