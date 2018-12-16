package homework3.DataAcessObject;

import homework3.IDataAcess;
import homework3.StudentPO;
import homework3.StudentSet;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class DAO_SQL implements IDataAcess {
    private Connection dbConnection=null;

    private String info_xml_filename="src/homework3/datafiles/config.xml";
    //private String info_xml_filename="src/homework3/datafiles/config_MySQL.xml";


    private String jdbcDriver;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;


    public DAO_SQL(){
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db = factory.newDocumentBuilder();
			File configFile = new File(this.info_xml_filename);
			Document xmldoc = db.parse(configFile);
			Element root = xmldoc.getDocumentElement();
			Node attrNode;
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				attrNode = root.getChildNodes().item(i);
				if (attrNode.getNodeName().equals("jdbcDriver")) {
					jdbcDriver=attrNode.getFirstChild().getNodeValue();
				}else if (attrNode.getNodeName().equals("dbUrl")) {
					dbUrl=attrNode.getFirstChild().getNodeValue();
				}else if (attrNode.getNodeName().equals("dbUser")) {
					dbUser=attrNode.getFirstChild().getNodeValue();
				}else if (attrNode.getNodeName().equals("dbPassword")) {
					dbPassword=attrNode.getFirstChild().getNodeValue();
				}

			}

		} catch (ParserConfigurationException error) {
			error.printStackTrace();
		} //catch (SAXException error2) {
            //error2.printStackTrace();
		//}
        catch (FileNotFoundException error3) {
			error3.printStackTrace();
		} catch (IOException error4) {
			error4.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }


    }
    public DAO_SQL(String info_xml_filename){
        super();
        this.info_xml_filename=info_xml_filename;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db = factory.newDocumentBuilder();
			File configFile = new File(this.info_xml_filename);
			Document xmldoc = db.parse(configFile);
			Element root = xmldoc.getDocumentElement();
			Node attrNode;
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				attrNode = root.getChildNodes().item(i);
				if (attrNode.getNodeName().equals("jdbcDriver")) {
					jdbcDriver=attrNode.getFirstChild().getNodeValue();
				}else if (attrNode.getNodeName().equals("dbURL")) {
					dbUrl=attrNode.getFirstChild().getNodeValue();
				}else if (attrNode.getNodeName().equals("dbUser")) {
					dbUser=attrNode.getFirstChild().getNodeValue();
				}else if (attrNode.getNodeName().equals("dbPassword")) {
					dbPassword=attrNode.getFirstChild().getNodeValue();
				}

			}

		} catch (ParserConfigurationException error) {
			error.printStackTrace();
		} //catch (SAXException error2) {
            //error2.printStackTrace();
		//}
        catch (FileNotFoundException error3) {
			error3.printStackTrace();
		} catch (IOException error4) {
			error4.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }

	//===============自定义部分，emmmmmmmm属于SQL的特性=========================
	public void addAStudent(StudentPO st){
		try {
			String sqlStr = "insert into student(studentID,name,sex,phone,email,institute,major) values(?,?,?,?,?,?,?)";
			PreparedStatement prepStmt = this.openOrCreate().prepareStatement(sqlStr);
			prepStmt.setString(1, st.getStudentID());
			prepStmt.setString(2, st.getName());
			prepStmt.setString(3, st.getSex());
			prepStmt.setString(4, st.getPhone());
			prepStmt.setString(5, st.getEmail());
			prepStmt.setString(6, st.getInstitute());
			prepStmt.setString(7, st.getMajor());

			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("add student error:" + e);
		}
	}
	public void deleteStudent(StudentPO st) {
		try {
			String sqlStr = "delete from student where studentID=?";
			PreparedStatement prepStmt = this.openOrCreate().prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, st.getStudentID());
			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("delete student error:" + e);
		}
	}

	public void deleteStudents(StudentSet sts){
		for (StudentPO st:sts){
			deleteStudent(st);
		}

	}
	public void updateStudent(StudentPO st) {
		try {
			String sqlStr = "update student set name=?,sex=?,phone=?,email=?,institute=?,major=? "
					+ " where studentID=?";
			PreparedStatement prepStmt = this.openOrCreate().prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, st.getName());
			prepStmt.setString(2, st.getSex());
			prepStmt.setString(3, st.getPhone());
			prepStmt.setString(4, st.getEmail());
			prepStmt.setString(5, st.getInstitute());
			prepStmt.setString(6, st.getMajor());
			prepStmt.setString(7, st.getStudentID());

			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("update student error:" + e);
		}
	}

	public void updateStudents(StudentSet sts){
		for(StudentPO st:sts){
			updateStudent(st);
		}
	}

	public StudentPO queryStudentByID(String id) {
		StudentPO st=null;
		try {
			if (this.openOrCreate() != null) {
				String sqlStr="SELECT * FROM student  where studentID=?";
				PreparedStatement prepStmt = this.openOrCreate().prepareStatement(sqlStr);
				prepStmt.setString(1, id);
				ResultSet rs = prepStmt.executeQuery();
				if (rs.next()) {
					st = new StudentPO();
					st.setStudentID(rs.getString("studentID"));
					st.setName(rs.getString("name"));
					st.setSex(rs.getString("sex"));
					st.setPhone(rs.getString("phone"));
					st.setEmail(rs.getString("email"));
					st.setInstitute(rs.getString("institute"));
					st.setMajor(rs.getString("major"));
				}
			}

		} catch (Exception e) {
			System.out.println("query Student error:" + e);
		}

		return st;

	}
	public StudentSet queryStudents() {
		StudentSet sts = new StudentSet();
		StudentPO st;
		try {
			if (this.openOrCreate() != null) {
				Statement stmt = this.openOrCreate().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM student order by studentID");
				while (rs.next()) {
					st = new StudentPO();
					st.setStudentID(rs.getString("studentID"));
					st.setName(rs.getString("name"));
					st.setSex(rs.getString("sex"));
					st.setPhone(rs.getString("phone"));
					st.setEmail(rs.getString("email"));
					st.setInstitute(rs.getString("institute"));
					st.setMajor(rs.getString("major"));
					sts.add(st);
				}
			}

		} catch (Exception e) {
			System.out.println("query student error:" + e);
		}

		return sts;
	}


	//===============接口实现部分===================


	@Override
	public void transStudentsToData(StudentSet sts) {
		for(StudentPO st:sts){
			addAStudent(st);
		}
	}

	@Override
	public StudentSet transDataToStudent() {
		return null;
	}


	@Override
	public void closeOrNothingToDo() {
		try{
			dbConnection.close();
		}catch (Exception e){
			System.out.println("close databse connection error:"+e);
		}
	}

	@Override
	public Connection openOrCreate() {
		if (dbConnection==null){
			try{       //jdbcDriver类仅能通过Class.forname()方法进行加载，不能够通过一般方式加载
				Class.forName(jdbcDriver).newInstance();
				dbConnection= DriverManager.getConnection(dbUrl,dbUser, dbPassword);
			}catch(Exception e){
				System.out.println("open databse connection error:"+e);
			}
		}
		return dbConnection;
	}
















}
