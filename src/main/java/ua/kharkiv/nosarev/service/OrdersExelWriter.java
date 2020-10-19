package ua.kharkiv.nosarev.service;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import ua.kharkiv.nosarev.entitie.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellStyle.BORDER_MEDIUM;

public class OrdersExelWriter {

    public void writeIntoExcel(String path, List<Order> orderList) throws IOException {

        try (Workbook wb = new HSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Orders");
            CellStyle style = wb.createCellStyle();
            style.setBorderBottom(BORDER_MEDIUM);
            style.setBorderRight(BORDER_MEDIUM);
            style.setBorderLeft(BORDER_MEDIUM);
            Row row = sheet.createRow(0);
            List<Cell> cellList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                cellList.add(cell);
            }
            cellList.get(0).setCellValue("Id");
            cellList.get(1).setCellValue("Device");
            cellList.get(2).setCellValue("Comment");
            cellList.get(3).setCellValue("Services");
            cellList.get(4).setCellValue("Price");
            cellList.get(5).setCellValue("Status");
            cellList.get(6).setCellValue("Customer");
            cellList.get(7).setCellValue("Master");
            cellList.get(8).setCellValue("Creating time");
            int rowNum = 1;
            for (Order order : orderList) {
                int cellNum = 0;
                Row newRow = sheet.createRow(rowNum);
                Cell newCell1 = newRow.createCell(cellNum++);
                Cell newCell2 = newRow.createCell(cellNum++);
                Cell newCell3 = newRow.createCell(cellNum++);
                Cell newCell4 = newRow.createCell(cellNum++);
                Cell newCell5 = newRow.createCell(cellNum++);
                Cell newCell6 = newRow.createCell(cellNum++);
                Cell newCell7 = newRow.createCell(cellNum++);
                Cell newCell8 = newRow.createCell(cellNum++);
                Cell newCell9 = newRow.createCell(cellNum);
                newCell1.setCellValue(String.valueOf(order.getId()));
                newCell2.setCellValue(String.valueOf(order.getDevice()));
                newCell3.setCellValue(String.valueOf(order.getComment()));
                newCell4.setCellValue(order.getServices().toString());
                newCell5.setCellValue(String.valueOf(order.getPrice()));
                newCell6.setCellValue(String.valueOf(order.getStatus()));
                newCell7.setCellValue(order.getCustomerName() + " " + order.getCustomerSurname());
                newCell8.setCellValue(order.getMasterName() + " " + order.getMasterSurname());
                newCell9.setCellValue(String.valueOf(order.getCreatingTime()));
                rowNum++;
            }
            setAutoSizeToColumn(sheet);
            wb.write(new FileOutputStream(path));
        }
    }

    private void setAutoSizeToColumn(Sheet sheet) {
        for (int i = 0; i < 9; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
