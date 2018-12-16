package homework3.DataAcessObject;

import homework3.IDataAcess;
import homework3.StudentPO;
import homework3.StudentSet;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class DAO_EXCEL_file implements IDataAcess {
     private String filename="src/homework3/datafiles/excel_file.xls";
    private File file=null;

    public DAO_EXCEL_file(){super();}
    public DAO_EXCEL_file(String filename){
    	this.filename=filename;
	}

    @Override
    public File openOrCreate() {
	    if(file==null){
	        try{
	            file=new File(filename);
            }
            catch (Exception ee){
	            System.out.println("open excel file error"+ee);
            }
        }
        return file;
    }

    @Override
    public void closeOrNothingToDo() {
        ;
    }

    @Override
    public StudentSet transDataToStudent() {
    	StudentSet sts=new StudentSet();
		try{
			FileInputStream inputStream=new FileInputStream(this.openOrCreate());
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
		    HSSFWorkbook hssfWorkbook =  new HSSFWorkbook(poifsFileSystem);
		    HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		    int rowstart = hssfSheet.getFirstRowNum();
		    int rowEnd = hssfSheet.getLastRowNum();
		    StudentPO st;
		    for(int i=rowstart+1;i<=rowEnd;i++){
		        HSSFRow row = hssfSheet.getRow(i);
		        if(null == row) continue;
		        st=new StudentPO();
		        HSSFCell cell = row.getCell(0);
		        st.setStudentID(cell.getStringCellValue());
		        cell = row.getCell(1);
		        st.setName(cell.getStringCellValue());
		        cell = row.getCell(2);
		        st.setSex(cell.getStringCellValue());
		        cell = row.getCell(3);
		        st.setPhone(cell.getStringCellValue());
		        cell = row.getCell(4);
		        st.setEmail(cell.getStringCellValue());
		        cell = row.getCell(5);
		        st.setInstitute(cell.getStringCellValue());
		        cell = row.getCell(6);
		        st.setMajor(cell.getStringCellValue());

		        sts.add(st);
		    }
		    inputStream.close();
		}catch(FileNotFoundException error){
			System.out.println("excel file cannot find");
		}catch(IOException error){
			System.out.println(error.getMessage());
		}
	    return sts;
    }

    @Override
    public void transStudentsToData(StudentSet sts) {
        HSSFWorkbook workbook = null;
	    workbook = new HSSFWorkbook();

	    int columeCount = 7;

	    HSSFSheet sheet = workbook.createSheet("学生信息簿");

	    HSSFRow headRow = sheet.createRow(0);
	    String[] titleArray = {"学号", "姓名", "性别", "手机号",  "电子邮箱", "学院", "专业ַ"};
	    for(int m=0;m<=columeCount-1;m++){
	        HSSFCell cell = headRow.createCell(m);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        sheet.setColumnWidth(m, 7000);
	        HSSFCellStyle style = workbook.createCellStyle();
	        HSSFFont font = workbook.createFont();
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        short color = HSSFColor.RED.index;
	        font.setColor(color);
	        style.setFont(font);
	        cell.setCellStyle(style);
	        cell.setCellValue(titleArray[m]);

	    }
	    int index = 0;

	    for(StudentPO st : sts){
	        HSSFRow row = sheet.createRow(index+1);
	        for(int n=0;n<=columeCount-1;n++)
	            row.createCell(n);
	        row.getCell(0).setCellValue(st.getStudentID());
	        row.getCell(1).setCellValue(st.getName());
	        row.getCell(2).setCellValue(st.getSex());
	        row.getCell(3).setCellValue(st.getPhone());
	        row.getCell(4).setCellValue(st.getEmail());
	        row.getCell(5).setCellValue(st.getInstitute());
	        row.getCell(6).setCellValue(st.getMajor());

	        index++;
	    }

	    try {
	        FileOutputStream fileOutputStream = new FileOutputStream(this.openOrCreate());
	        workbook.write(fileOutputStream);
	        fileOutputStream.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
}
