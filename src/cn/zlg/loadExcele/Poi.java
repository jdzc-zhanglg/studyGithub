package cn.zlg.loadExcele;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Poi {
	private Sheet sheet;    //�����ʵ��  
    LinkedList[] result;    //����ÿ����Ԫ������� ��ʹ�õ���һ����������Ľṹ  
  
    //��ȡexcel�ļ����������ʵ��  
    private void loadExcel(String filePath) {  
        FileInputStream inStream = null;  
        try {  
            inStream = new FileInputStream(new File(filePath));
            //Workbook------������
            Workbook workBook = WorkbookFactory.create(inStream);  
             //Sheet------------���Workbookʵ����getSheetAt(int num)������ȡ��
            sheet = workBook.getSheetAt(0);           
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                if(inStream!=null){  
                    inStream.close();  
                }                  
            } catch (IOException e) {                  
                e.printStackTrace();  
            }  
        }  
    }  
    //��ȡ��Ԫ���ֵ    Row--------------�У�Sheetʵ����getRow(int num)������ȡ��Cell--------------��Ԫ��Rowʵ����getCell(int num)������ȡ
    private String getCellValue(Cell cell) {  
        String cellValue = "";  
        DataFormatter formatter = new DataFormatter();  
        if (cell != null) {  
            //�жϵ�Ԫ�����ݵ����ͣ���ͬ���͵��ò�ͬ�ķ���  
            switch (cell.getCellType()) {  
                //��ֵ����  
                case Cell.CELL_TYPE_NUMERIC:  
                    //��һ���ж� ����Ԫ���ʽ�����ڸ�ʽ   
                    if (DateUtil.isCellDateFormatted(cell)) {  
                        cellValue = formatter.formatCellValue(cell);  
                    } else {  
                        //��ֵ  
                        double value = cell.getNumericCellValue();  
                        int intValue = (int) value;  
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);  
                    }  
                    break;  
                case Cell.CELL_TYPE_STRING:  
                    cellValue = cell.getStringCellValue();  
                    break;  
                case Cell.CELL_TYPE_BOOLEAN:  
                    cellValue = String.valueOf(cell.getBooleanCellValue());  
                    break;  
                    //�жϵ�Ԫ���ǹ�ʽ��ʽ����Ҫ��һ�����⴦�����õ���Ӧ��ֵ  
                case Cell.CELL_TYPE_FORMULA:{  
                    try{  
                        cellValue = String.valueOf(cell.getNumericCellValue());  
                    }catch(IllegalStateException e){  
                        cellValue = String.valueOf(cell.getRichStringCellValue());  
                    }  
                      
                }  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    cellValue = "";  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    cellValue = "";  
                    break;  
                default:  
                    cellValue = cell.toString().trim();  
                    break;  
            }  
        }  
        return cellValue.trim();  
    }  
  
  
  
    //��ʼ������е�ÿһ�У����õ�ÿһ����Ԫ���ֵ  
    public  void init(){  
        int rowNum = sheet.getLastRowNum() + 1;  
        result = new LinkedList[rowNum];  
        for(int i=0;i<rowNum;i++){  
            Row row = sheet.getRow(i);  
            //ÿ���µ�һ�У�����һ���µ�LinkedList����  
            result[i] = new LinkedList();  
            for(int j=0;j<row.getLastCellNum();j++){  
                Cell cell = row.getCell(j);  
                //��ȡ��Ԫ���ֵ  
                String str = getCellValue(cell);  
                //���õ���ֵ����������  
                result[i].add(str);  
            }  
        }  
    }  
    //����̨��ӡ����ı������  
    public void show(){  
        for(int i=0;i<result.length;i++){  
            for(int j=0;j<result[i].size();j++){  
                System.out.print(result[i].get(j) + "\t");  
            }  
            System.out.println();  
        }  
    }  
    public static void main(String[] args) {  
        Poi poi = new Poi();  
        poi.loadExcel("C:\\Users\\hpuser\\Desktop\\cc.xls");  
        poi.init();  
        poi.show();  
    }  
}
