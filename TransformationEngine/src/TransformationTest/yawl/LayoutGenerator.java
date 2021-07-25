package TransformationTest.yawl;
import org.jdom2.Element;

import TransformationTest.model.*;

public class LayoutGenerator {

	private static Element layout;
	
	public static void generateLayout() {
		layout = new Element("layout");
		
		Element locale = new Element("locale");
		locale.setAttribute("language","en");
		locale.setAttribute("country","US");
		
		layout.addContent("\n");
		layout.addContent(locale);
		layout.addContent("\n");
		
	}
	
	public static void generateLayoutNet() {
		Element specification = new Element("specification");
		specification.setAttribute("id","Test");
		
		Element size = new Element("size");
		size.setAttribute("w","58");
		size.setAttribute("h","28");
		
		Element net = new Element("net");
		net.setAttribute("id","Net");
		
		Element bounds = new Element("bounds");
		bounds.setAttribute("x","0");
		bounds.setAttribute("y","0");
		bounds.setAttribute("w","1126");
		bounds.setAttribute("h","447");
		
		Element frame = new Element("frame");
		frame.setAttribute("x","0");
		frame.setAttribute("y","0");
		frame.setAttribute("w","1129");
		frame.setAttribute("h","450");
		
		Element viewport = new Element("viewport");
		viewport.setAttribute("x","0");
		viewport.setAttribute("y","0");
		viewport.setAttribute("w","1129");
		viewport.setAttribute("h","450");
		
		Element scale = new Element("scale");
		scale.setText("1.5");
		
		specification.addContent("\n");
		specification.addContent(size);
		specification.addContent("\n");
		
		net.addContent("\n");
		net.addContent(bounds);
		net.addContent("\n");
		net.addContent(frame);
		net.addContent("\n");
		net.addContent(viewport);
		net.addContent("\n");
		net.addContent(scale);
		net.addContent("\n");
		
		for(Node node: ActivityManagement.getAllNodes()) {
			net.addContent(generateLayoutNode(node));
			net.addContent("\n");
		}
		
		for(Edge edge: ActivityManagement.getAllEdges()) {
			net.addContent(generateLayoutEdge(edge));
			net.addContent("\n");
		}
		
		specification.addContent(net);
		specification.addContent("\n");
		
		layout.addContent(specification);
		layout.addContent("\n");
	}
	
	private static Element generateLayoutNode(Node node) {
		
		String nodeName = NodeGenerator.getNodeMappingValue(node);

		if(nodeName.equals("InputCondition") || nodeName.equals("OutputCondition")) {
			return generateLayoutVertex(nodeName);
		}else 
			return generateLayoutContainer(node);
	}
	
	private static Element generateLayoutEdge(Edge edge) {
		
		Node startNode = ActivityManagement.getInputConditionNode();
		Node endNode = ActivityManagement.getOutputConditionNode();
		
		String sourceNode = (startNode.name == edge.sourceNode) ? "InputCondition" : edge.sourceNode;
		String targetNode = (endNode.name == edge.targetNode) ? "OutputCondition" : edge.targetNode;
		
		Element flow = new Element("flow");
		flow.setAttribute("source", sourceNode);
		flow.setAttribute("target", targetNode);
		
		Element ports = new Element("ports");
		ports.setAttribute("in", "13");
		ports.setAttribute("out", "12");
		
		Element attributes = new Element("attributes");
		
		Element lineStyle = new Element("lineStyle");
		lineStyle.setText("11");
		
		attributes.addContent("\n");
		attributes.addContent(lineStyle);
		attributes.addContent("\n");
		
		flow.addContent("\n");
		flow.addContent(ports);
		flow.addContent("\n");
		flow.addContent(attributes);
		flow.addContent("\n");
		
		return flow;
	}
	
	private static Element generateLayoutVertex(String nodeName) {
		
		Element vertex = new Element("vertex");
		vertex.setAttribute("id", nodeName);
		
		Element attributes = new Element("attributes");
		
		Element bounds = new Element("bounds");
		bounds.setAttribute("x","44");
		bounds.setAttribute("y","104");
		bounds.setAttribute("w","32");
		bounds.setAttribute("h","32");
		
		attributes.addContent("\n");
		attributes.addContent(bounds);
		attributes.addContent("\n");
		
		vertex.addContent("\n");
		vertex.addContent(attributes);
		vertex.addContent("\n");
		
		return vertex;	
	}
	
	private static Element generateLayoutContainer(Node node) {
		
		Element container = new Element("container");
		container.setAttribute("id", node.name);
		
		Element vertex = new Element("vertex");
		
		Element attributes = new Element("attributes");
		
		Element bounds = new Element("bounds");
		bounds.setAttribute("x","44");
		bounds.setAttribute("y","104");
		bounds.setAttribute("w","32");
		bounds.setAttribute("h","32");
		
		attributes.addContent("\n");
		attributes.addContent(bounds);
		attributes.addContent("\n");
		
		vertex.addContent("\n");
		vertex.addContent(attributes);
		vertex.addContent("\n");
		
		Element label = new Element("label");
		
		Element attributes2 = new Element("attributes");
		
		Element bounds2 = new Element("bounds");
		bounds2.setAttribute("x","44");
		bounds2.setAttribute("y","104");
		bounds2.setAttribute("w","97");
		bounds2.setAttribute("h","17");
		
		attributes2.addContent("\n");
		attributes2.addContent(bounds2);
		attributes2.addContent("\n");
		
		label.addContent("\n");
		label.addContent(attributes2);
		label.addContent("\n");
		
		container.addContent("\n");
		container.addContent(vertex);
		container.addContent("\n");
		container.addContent(label);
		container.addContent("\n");
		
		return container;
	}
	
	public static Element getLayout() {
		
		return layout;
	}
}
