package com.picc.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @author wangXi
 *
 */
public class ImportExcelUtil {

	private final static String excel2003L =".xls";    //2003- 
    private final static String excel2007U =".xlsx";   //2007+   
      
    /** 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
	public List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
		List<List<Object>> list = null;

		// excel工作薄创建
		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建工作波失败");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();
		/*for (int i = 0; i < work.getNumberOfSheets(); i++) {}*/
		//只读第一个sheet页
			sheet = work.getSheetAt(0);
			//行
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row.getFirstCellNum() == j) {
					continue;
				}
				// 列数据
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(this.getCellValue(cell));
				}
				list.add(li);
			}
		
		in.close();
		return list;
	}
      
    /** 
     *   
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("文件错误");
		}
		return wb;
	}
 
	/**
	 * 
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");// number String 
		DecimalFormat nf = new DecimalFormat("0.00");// 数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 时间
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:// String 字符
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:// 数字
				if ("@".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());
				} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
			          Long longVal = Math.round(cell.getNumericCellValue());
			          Double doubleVal = cell.getNumericCellValue();
			          if(Double.parseDouble(longVal + ".0") == doubleVal){   
			              value = longVal.toString();
			          }
			          else{
			              value =  nf.format(doubleVal);
			          }	
				} else {
					if (("yyyy/mm;@".equals(cell.getCellStyle().getDataFormatString()) || "m/d/yy".equals(cell.getCellStyle().getDataFormatString())
			        || "yy/m/d".equals(cell.getCellStyle().getDataFormatString()) || "mm/dd/yy".equals(cell.getCellStyle().getDataFormatString())
			        || "dd-mmm-yy".equals(cell.getCellStyle().getDataFormatString())|| "yyyy/m/d".equals(cell.getCellStyle().getDataFormatString())))
						{
							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
							break;
						}
					/*boolean equals = "m/d/yy".equals(cell.getCellStyle().getDataFormatString();*/
					short format = cell.getCellStyle().getDataFormat();
					if(format==21 || format==20 || format==32 || format==22 ) {
						value = sdt.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}else if(format==176){
						value=sdft.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}else  if(format==3 || format==177) {
						Long longVal = Math.round(cell.getNumericCellValue());
				          Double doubleVal = cell.getNumericCellValue();
				          if(Double.parseDouble(longVal + ".0") == doubleVal){   
				              value = longVal.toString();
				          }
				          else{
				              value =  nf.format(doubleVal);
				          }
					}else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:// Boolean类型
				value = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:// 空
				value = "";
				break;
			default:// default type
				value = cell.toString();
			}
		}
		return value;
	}

}
