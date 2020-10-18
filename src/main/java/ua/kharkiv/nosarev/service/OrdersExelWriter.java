package ua.kharkiv.nosarev.service;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import ua.kharkiv.nosarev.entitie.Order;

import java.io.FileOutputStream;
import java.io.IOException;
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
            Cell cell1 = row.createCell(0);
            Cell cell2 = row.createCell(1);
            Cell cell3 = row.createCell(2);
            Cell cell4 = row.createCell(3);
            Cell cell5 = row.createCell(4);
            Cell cell6 = row.createCell(5);
            Cell cell7 = row.createCell(6);
            Cell cell8 = row.createCell(7);
            Cell cell9 = row.createCell(8);
            cell1.setCellValue("Id");
            cell2.setCellValue("Device");
            cell3.setCellValue("Comment");
            cell4.setCellValue("Services");
            cell5.setCellValue("Price");
            cell6.setCellValue("Status");
            cell7.setCellValue("Customer");
            cell8.setCellValue("Master");
            cell9.setCellValue("Creating time");
            cell1.setCellStyle(style);
            cell2.setCellStyle(style);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style);
            cell5.setCellStyle(style);
            cell6.setCellStyle(style);
            cell7.setCellStyle(style);
            cell8.setCellStyle(style);
            cell9.setCellStyle(style);

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
