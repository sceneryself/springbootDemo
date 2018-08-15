package com.self.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static final String RAW_LIST = "raw";
    public static final String COLUMN_LIST = "column";

    public static List<String[]> readExcelList(String path, int index, String format) {
        try {
            InputStream is = new FileInputStream(path);
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(index);
            // sheet.getRows()返回该页的总行数
            List<Cell[]> list = new ArrayList<Cell[]>();
            if (format.equals(RAW_LIST)) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell[] data = sheet.getRow(i);
                    list.add(data);
                }
            } else if (format.equals(COLUMN_LIST)) {
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell[] data = sheet.getColumn(i);
                    list.add(data);
                }
            }
            List<String[]> newList = Cell2String(list);
            return newList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String[]> readExcelListAllSheet(String path, String format) {
        try {
            InputStream is = new FileInputStream(path);
            Workbook wb = Workbook.getWorkbook(is);
            return readExcelDataToList(wb, format);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String[]> readExcelDataToList(Workbook wb, String format){
        List<String[]> retList = new ArrayList<>();
        // Excel的页签数量
        int sheet_size = wb.getNumberOfSheets();
        for (int x = 0; x < sheet_size; x++) {
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(x);
            // sheet.getRows()返回该页的总行数
            List<Cell[]> list = new ArrayList<>();
            if (format.equals(RAW_LIST)) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell[] data = sheet.getRow(i);
                    list.add(data);
                }
            } else if (format.equals(COLUMN_LIST)) {
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell[] data = sheet.getColumn(i);
                    list.add(data);
                }
            }
            List<String[]> newList = Cell2String(list);
            retList.addAll(newList);
        }
        return retList;
    }

    private static List<String[]> Cell2String(List<Cell[]> cellList) {
        List<String[]> list = new ArrayList<>();
        for (int x = 0; x < cellList.size(); x++) {
            Cell[] cell = cellList.get(x);
            String[] arr = new String[cell.length];
            for (int y = 0; y < cell.length; y++) {
                String value = cell[y].getContents();
                arr[y] = value.trim().replace(" ", "");
            }
            list.add(arr);
        }
        return list;
    }

}
