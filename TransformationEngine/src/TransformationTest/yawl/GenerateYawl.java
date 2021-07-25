package TransformationTest.yawl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;
import java.io.IOException;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



import org.apache.*;


import TransformationTest.model.*;




public class GenerateYawl {
	
	public static String defaultNamespace = "http://www.yawlfoundation.org/yawlschema";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//writeYawlformat();
		ActivityManagement.printData();
		TransformationManagement.transform();
		YawlManagement.printData();
		writeYawlformat();
		System.out.print("Completed!");
	}
	
	private static void writeYawlformat() throws FileNotFoundException, IOException {

		Document document = initYawlformat();
		XMLOutputter outputFile = new XMLOutputter(Format.getPrettyFormat().setIndent("  ").setLineSeparator("\n"));
		
		outputFile.output(document, new FileOutputStream( new File("src/TransformationTest/yawl/" + ActivityManagement.getActivityName() + ".yawl")));
		

	}
	
	private static Document initYawlformat() {
		Document document = new Document();
		
		Element specificationSet = generateSpecificationSetElem();
		
		Element specification = generateSpecificationElem();
		
		Element documentation = generateDocumentationElem();
		
		Element metaData = generateMetadataElem();
		
		Element xsSchema = generateXsSchemaElem();
		
		Element decomposition = generateDecompositionElem();
		
		Element processControlElements = generateProcessControlElements();
		
		Element layout = generateLayoutElements();	
		
		decomposition.addContent(processControlElements);	

		specification.addContent(documentation);
		specification.addContent(metaData);
		specification.addContent(xsSchema);
		specification.addContent(decomposition);

		specificationSet.addContent(specification);		
		specificationSet.addContent(layout);
		
		document.setRootElement(specificationSet);
		
		return document;
		
	}
	
	private static Element generateXsSchemaElem() {
		Namespace xs = Namespace.getNamespace("xs", "http://www.w3.org/2001/XMLSchema");
		Element xsSchema = new Element("schema",xs);
		
		return xsSchema;
	}
	
	private static Element generateDocumentationElem() {
		Element documentation = new Element("documentation",  defaultNamespace);
		documentation.setText("No description provided");
		
		return documentation;
	}
	
	private static Element generateDecompositionElem() {
		
		Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Element decomposition = new Element("decomposition",  defaultNamespace);
		decomposition.setAttribute("id","Net");
		decomposition.setAttribute("isRootNet","true");
		decomposition.setAttribute("type","NetFactsType", xsi);
		
		return decomposition;
	}
	
	private static Element generateSpecificationElem() {
		Element specification = new Element("specification",  defaultNamespace);
		specification.setAttribute("uri","Test");
		
		return specification;
	}
	
	private static Element generateSpecificationSetElem() {
		Element specificationSet = new Element("specificationSet",  defaultNamespace);
		Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		specificationSet.addNamespaceDeclaration(xsi);
		specificationSet.setAttribute("version","4.0");
		specificationSet.setAttribute("schemaLocation","http://www.yawlfoundation.org/yawlschema http://www.yawlfoundation.org/yawlschema/YAWL_Schema4.0.xsd", xsi);
		
		return specificationSet;
	}
	
	private static Element generateMetadataElem() {
		Element metaData = new Element("metaData",  defaultNamespace);
		
		
		Element creator = new Element("creator",  defaultNamespace);
		creator.setText("Ahmad Mukashaty");
		
		Element description = new Element("description",  defaultNamespace);
		description.setText("No description provided");
		
		Element coverage = new Element("coverage", defaultNamespace);
		coverage.setText("4.3.1.772");
		
		Element version = new Element("version", defaultNamespace);
		version.setText("0.1");
		
		Element persistent = new Element("persistent", defaultNamespace);
		persistent.setText("false");
		
		Element identifier = new Element("identifier", defaultNamespace);
		identifier.setText("UID_531fa076-f073-42cd-b005-c60b88b5408f");
		

		metaData.addContent(creator);
		metaData.addContent(description);
		metaData.addContent(coverage);
		metaData.addContent(version);
		metaData.addContent(persistent);
		metaData.addContent(identifier);

		
		return metaData;
		
	}
	
	private static Element generateProcessControlElements() {
		Element processControlElements = new Element("processControlElements", defaultNamespace);
		
		for(YNode node : YawlManagement.getAllNodes()) {
				Element elem = NodeGenerator.generateNode(node);
				processControlElements.addContent(elem);
		}
		
		return processControlElements;
	}
	
	private static Element generateLayoutElements() {
		
		LayoutGenerator.generateLayout();
		LayoutGenerator.generateLayoutNet();
		
		return LayoutGenerator.getLayout();
	}

}
