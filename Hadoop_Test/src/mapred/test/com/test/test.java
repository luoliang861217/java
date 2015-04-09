package test.com.test;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.hadoop.fs.FileStatus;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


import test.com.hdfs.HdfsTest;

public class test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println("ok");
//		System.out.println(System.getProperty("user.dir") + "");
		
//		String str1 = "java";
//		String str2 = "blog";
//		System.out.println(str1 + str2 == "javablog");
//		
//		String luoliang = "luoliang";
//	    String luo = "luo";
//	    String liang = "liang";
//	    System.out.println(luoliang  == "luo" + "liang");
//	    System.out.println(luoliang  == "luo" + liang);
	    
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = factory.newDocumentBuilder();
		Document doc = build.parse(new File("mapred-default.xml"));
//		System.out.println(build.getDOMImplementation().toString());
		
		Element root = doc.getDocumentElement();
		parseXMLFile(root);
	}

	/**
	 * 解析XML文件
	 * @param element 节点元素
	 */
	public static void parseXMLFile(Element element){
		System.out.print("<" + element.getTagName());
		NamedNodeMap attributes = element.getAttributes();
		if(attributes != null){
			for(int i=0;i<attributes.getLength();i++){
				System.out.print(" " + attributes.item(i).getNodeName() + "=\"" + attributes.item(i).getNodeValue() + "\"");
			}
		}
		System.out.print(">");
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			if(childNodes.item(i).getNodeType() == Element.ELEMENT_NODE  ){
				parseXMLFile((Element)childNodes.item(i));
			}
			else{
				System.out.print(childNodes.item(i).getTextContent());
			}
		}
		System.out.print("</" + element.getTagName() + ">");
	}
	
}
