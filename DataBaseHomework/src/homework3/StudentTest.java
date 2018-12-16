package homework3;

import homework3.DataAcessObject.DAO_EXCEL_file;
import homework3.DataAcessObject.DAO_SQL;
import homework3.DataAcessObject.DAO_XML_file;

//似乎这是两种不同的格式，出现了某种重复
//import javax.swing.text.Document;
//import javax.swing.text.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Statement;
import java.util.ArrayList;


public class StudentTest {
    private DAO_SQL sql_test;

    private StudentTest(){
        this.sql_test=new DAO_SQL();
    }

    private void print_student_info(StudentPO st){
        System.out.println("学号        "+"姓名   "+"性别 "+"手机号           "+"电子邮箱                         "+"学院    "+"专业    ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //将学生信息打印出来
        System.out.println(st.toString());
    }

    private void print_students_info(StudentSet sts){
        System.out.println("学号        "+"姓名   "+"性别 "+"手机号           "+"电子邮箱                         "+"学院    "+"专业    ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //将学生信息打印出来
        if(sts != null && sts.size()>0){
            for(StudentPO student:sts){
                System.out.println(student.toString());
            }
        }
    }

    //定义生成excel文件的方法,因为仅给main方法调用，因而设置为私有。
    // 实际上，这个方法也可以生成xml文件，也可以初始化一个数据库。
    private void init_excel(IDataAcess dao_excel){
        StudentSet sts=new StudentSet();

        StudentPO st=new StudentPO();
        st.setStudentID("20163933");
        st.setName("梁子");
        st.setSex("男");
        st.setPhone("18842378119");
        st.setEmail("liangzi20163933@gmail.com");
        st.setInstitute("信息学院");
        st.setMajor("自动化");
        sts.add(st);
        st=new StudentPO();
        st.setStudentID("20163876");
        st.setName("贾胜伟");
        st.setSex("男");
        st.setPhone("18842378111");
        st.setEmail("llian3933@gmail.com");
        st.setInstitute("信息学院");
        st.setMajor("自动化");
        sts.add(st);
        st=new StudentPO();
        st.setStudentID("20164184");
        st.setName("李宏宗");
        st.setSex("男");
        st.setPhone("18842300000");
        st.setEmail("lihongzong3933@gmail.com");
        st.setInstitute("信息学院");
        st.setMajor("自动化");
        sts.add(st);
        st=new StudentPO();
        st.setStudentID("20166666");
        st.setName("cc");
        st.setSex("女");
        st.setPhone("00000000000");
        st.setEmail("cccccccccc@gmail.com");
        st.setInstitute("某学院");
        st.setMajor("某专业");
        sts.add(st);

        dao_excel.transStudentsToData(sts);
        System.out.println("==========初始化生成一个文件==========");
        this.print_students_info(sts);
    }

    private void init_configXML(String filename){
        String[] name={"jdbcDriver","dbUrl","dbUser","dbPassword"};
        String[] attr={"org.apache.derby.jdbc.ClientDriver",
                "jdbc:derby://localhost:1527/student;create=true",
                "liangzi",
                "20163933"};

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        try{
            Transformer transformer=transformerFactory.newTransformer();
            transformer.setOutputProperty("indent","yes");
            DOMSource domSource=new DOMSource();
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder documentBuilder=factory.newDocumentBuilder();
            Document xmldoc= documentBuilder.newDocument();

            Element root=((Document) xmldoc).createElement("config");

            Element ele;
            for(int i=0;i<name.length;i++)
            {
                ele=xmldoc.createElement(name[i]);
                ele.setTextContent(attr[i]);
                root.appendChild(ele);
            }

            xmldoc.appendChild(root);
			domSource.setNode(xmldoc);
			StreamResult result=new StreamResult();
			FileOutputStream stream=new FileOutputStream(this.openOrCreate(filename));
			result.setOutputStream(stream);
			transformer.transform(domSource,result);

            System.out.println("==========config.xml文件初始化完成==========");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private File openOrCreate(String filename) {
            try{
                return new File(filename);
            }
            catch (Exception ee){
                System.out.println("open xml file error"+ee);
                return null;
            }
    }

    private void transferEXCELtoXML(String excel_filename,String xml_filename){
        IDataAcess dao=new DAO_EXCEL_file(excel_filename);
        StudentSet sts;
        sts=dao.transDataToStudent();
        dao=new DAO_XML_file(xml_filename);
        dao.transStudentsToData(sts);
        System.out.println("==========下列信息已经从excel中读取，并被存储到XML文件中==========");
        print_students_info(sts);
    }

    private void transferXMLtoSQL(String filename){
        DAO_XML_file daox=new DAO_XML_file(filename);
        StudentSet sts=daox.transDataToStudent();
        sql_test.transStudentsToData(sts);
    }

    private void createSQLtable(){
        try{
			Statement stmt= sql_test.openOrCreate().createStatement();  //primary key指的是主键，主键不能为空
			stmt.execute("create table student (studentID varchar(10) primary key,name varchar(12),sex varchar(10),"+""
                    + "phone varchar(20),email varchar(50)," +""+
                    "institute varchar(30),major varchar(10))");
			System.out.println("创建数据表student成功！ ");
		}catch(Exception e){
			System.out.println("Created table student error:"+e);
		}
    }

    private void dropSQLtable(){
		try{
			Statement stmt= sql_test.openOrCreate().createStatement();
			stmt.execute("drop table student");
			//System.out.println("drop table friend");
		}catch(Exception e){
			System.out.println("drop table student error:"+e);
		}
	}

	public static void main(String args[]){
        StudentTest st=new StudentTest();
        st.init_configXML("src/homework3/datafiles/config.xml");
        IDataAcess dao;
        dao=new DAO_EXCEL_file();//默认值为：src/homework3/datafiles/excel_file.xls

        st.init_excel(dao);
        st.transferEXCELtoXML("src/homework3/datafiles/excel_file.xls",
                                "src/homework3/datafiles/xml_file.xml");

        st.dropSQLtable();
        st.createSQLtable();
        st.transferXMLtoSQL("src/homework3/datafiles/xml_file.xml");

        //定义一个新的学生数据对象
        StudentPO hello=new StudentPO();
        hello.setStudentID("00000000");
        hello.setName("hello");
        hello.setSex("male");
        hello.setPhone("110");
        hello.setEmail("liangzizuishuai@qq.com");
        hello.setInstitute("爪哇岛生物医学院");
        hello.setMajor("爪哇岛奇特动物研究");

        //增 删 改 查 操作
        st.sql_test.addAStudent(hello);
        System.out.println("==========增添了以下对象==========");
        st.print_student_info(hello);

        hello.setPhone("120");
        hello.setSex("female");
        st.sql_test.updateStudent(hello);
        System.out.println("==========以下数据被修改==========");
        st.print_student_info(hello);

        System.out.println("=================================");
        System.out.println("当输入学号20163933后，查到了如下信息");
        st.print_student_info(st.sql_test.queryStudentByID("20163933"));

        System.out.println("==========下列信息已被删除==========");
        st.print_student_info(hello);
        st.sql_test.deleteStudent(hello);

        //System.out.println("如何一定要访问则会出现异常如下：");
        //st.print_student_info(st.sql_test.queryStudentByID(hello.getStudentID()));

        //结束！












    }



}
