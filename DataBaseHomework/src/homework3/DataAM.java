package homework3;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataAM {

    //需要写成一个文件去读取的一部分信息。这里需要修改

    private Connection dbConnection=null;

    private String jdbcDriver="org.apache.derby.jdbc.ClientDriver";
	private String dbUrl = "jdbc:derby://localhost:1527/addressBook;create=true";
	private String dbUser="root";
	private String dbPassword="manager";
	private String filepath="src/homework3/datafiles/";

	private File excelFile=null;
	private File xmlFile=null;


	public  DataAM() {
		super();

	}
	//用于指定文件放置地址
	public DataAM(String filepath){
	    super();
	    this.filepath=filepath;
    }



/*
   public File open_excel(){
	    if(excelFile==null){
	        try{
	            excelFile=new File(filepath+"excel_form_file.xls");
            }
            catch (Exception e){
	            System.out.println("open excel file error:"+e);
            }
        }
        return excelFile;
   }
   public File open_xml(){
	    if(xmlFile==null){
	        try{
	            xmlFile=new File(filepath+"xml_form_file.xml");
            }
            catch (Exception ee){
	            System.out.println("open xml file error"+ee);
            }
        }
        return xmlFile;
   }
*/
    public Connection getConnection() {
        if (dbConnection==null){
			try{       //jdbcDriver类仅能通过Class.forname()方法进行加载，不能够通过一般方式加载
				Class.forName(jdbcDriver).newInstance();
				dbConnection=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			}catch(Exception e){
				System.out.println("open databse connection error:"+e);
			}
		}
		return dbConnection;
    }

    public void closeConnection(){
		try{
			dbConnection.close();
		}catch (Exception e){
			System.out.println("close databse connection error:"+e);
		}
	}


}
