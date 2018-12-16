package homework3.DataAcessObject;

import homework3.IDataAcess;
import homework3.StudentPO;
import homework3.StudentSet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class DAO_XML_file implements IDataAcess {
    private String filename="src/homework3/datafiles/xml_files.xml";
    private File file=null;

    public DAO_XML_file(){super();}
    public DAO_XML_file(String filename){
    	this.filename=filename;
	}

    @Override
    public File openOrCreate() {
	    if(file==null){
	        try{
	            file=new File(filename);
            }
            catch (Exception ee){
	            System.out.println("open xml file error"+ee);
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
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try{
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db=factory.newDocumentBuilder();
			Document xmldoc=db.parse(this.openOrCreate());
			Element root=xmldoc.getDocumentElement();
			StudentPO st;
			Node objNode,attrNode;

			for(int i=0;i<root.getChildNodes().getLength();i++){
				objNode=root.getChildNodes().item(i);
				if(objNode.getNodeName().equals("student")){
					st=new StudentPO();
					for (int j=0;j<objNode.getChildNodes().getLength();j++){
						attrNode=objNode.getChildNodes().item(j);
						if(attrNode.getNodeName().equals("name")){
							st.setName(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("studentID")){
							st.setStudentID(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("sex")){
							st.setSex(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("phone")){
							st.setPhone(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("email")){
							st.setEmail(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("institute")){
							st.setInstitute(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("major")){
							st.setMajor(attrNode.getFirstChild().getNodeValue());
						}
					}
					sts.add(st);
				}
			}

		}catch (ParserConfigurationException error){
			error.printStackTrace();
		}catch (SAXException error2){
			error2.printStackTrace();
		}catch(FileNotFoundException error3)  {
			error3.printStackTrace();
		}catch(IOException error4)  {
			error4.printStackTrace();
		}
		return sts;
    }

	@Override
	public void transStudentsToData(StudentSet sts) {
	TransformerFactory transFactory=TransformerFactory.newInstance();

		try{

			Transformer transformer=transFactory.newTransformer();
			transformer.setOutputProperty("indent","yes");
			DOMSource source=new DOMSource();
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db=factory.newDocumentBuilder();
			Document xmldoc=db.newDocument();
			Element root=xmldoc.createElement("students");

            Element theStudent,theAttr;
            for (StudentPO st : sts){
	          //--- 新建一个朋友信息 ----
	            theStudent=xmldoc.createElement("student");

				theAttr=xmldoc.createElement("studentID");
				theAttr.setTextContent(st.getStudentID());
				theStudent.appendChild(theAttr);

	            theAttr=xmldoc.createElement("name");
	            theAttr.setTextContent(st.getName());
	            theStudent.appendChild(theAttr);

	            theAttr=xmldoc.createElement("sex");
	            theAttr.setTextContent(st.getSex());
	            theStudent.appendChild(theAttr);

	            theAttr=xmldoc.createElement("phone");
	            theAttr.setTextContent(st.getPhone());
	            theStudent.appendChild(theAttr);


	            theAttr=xmldoc.createElement("email");
	            theAttr.setTextContent(st.getEmail());
	            theStudent.appendChild(theAttr);


	            theAttr=xmldoc.createElement("institute");
	            theAttr.setTextContent(st.getInstitute());
	            theStudent.appendChild(theAttr);

	            theAttr=xmldoc.createElement("major");
	            theAttr.setTextContent(st.getMajor());
	            theStudent.appendChild(theAttr);


	            root.appendChild(theStudent);
            }
            xmldoc.appendChild(root);
			source.setNode(xmldoc);
			StreamResult result=new StreamResult();
			FileOutputStream stream=new FileOutputStream(this.openOrCreate());
			result.setOutputStream(stream);
			transformer.transform(source,result);

			stream.close();
		}catch (ParserConfigurationException error){
			error.printStackTrace();
		}catch(TransformerConfigurationException error1){
			error1.printStackTrace();
		}catch(TransformerException error2){
			error2.printStackTrace();
		}catch(FileNotFoundException error3)  {
			error3.printStackTrace();
		}catch(IOException error4)  {
			error4.printStackTrace();
		}
	}


}
