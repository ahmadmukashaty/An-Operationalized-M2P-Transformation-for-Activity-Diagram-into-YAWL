package TransformationTest.yawl;

import java.util.List;

import org.jdom2.Element;
import TransformationTest.model.*;

public class NodeGenerator {
	
	private static String defaultNamespace = "http://www.yawlfoundation.org/yawlschema";
	
	private static Element generateInputCondition(YNode node) {
		
		Element inputCondition = new Element("inputCondition", defaultNamespace);
		inputCondition.setAttribute("id","InputCondition");
		
		for(YNode edge: node.flowsInto) {
			Element flowsInto = new Element("flowsInto", defaultNamespace);
			
			Element nextElementRef = new Element("nextElementRef", defaultNamespace);
			nextElementRef.setAttribute("id",edge.id);
			
			flowsInto.addContent(nextElementRef);		
			inputCondition.addContent(flowsInto);
		}
		
		return inputCondition;	
	}
	
	private static Element generateOutputCondition(YNode node) {
		
		Element outputCondition = new Element("outputCondition", defaultNamespace);
		outputCondition.setAttribute("id","OutputCondition");	
		
		return outputCondition;
		
	}
	
	private static Element generateTask(YNode node) {
		
		Element task = new Element("task", defaultNamespace);
		task.setAttribute("id",node.id);
		
		Element name = new Element("name", defaultNamespace);
		name.setText(node.name);
		
		task.addContent(name);
		
		for(YNode edge: node.flowsInto) {
			Element flowsInto = new Element("flowsInto", defaultNamespace);
			
			Element nextElementRef = new Element("nextElementRef", defaultNamespace);
			nextElementRef.setAttribute("id",edge.id);
			
			flowsInto.addContent(nextElementRef);
			
			task.addContent(flowsInto);
		}
		
		Element join = new Element("join", defaultNamespace);
		join.setAttribute("code", node.joinType.toString());
		
		Element split = new Element("split", defaultNamespace);
		split.setAttribute("code", node.splitType.toString());
		
		task.addContent(join);
		
		task.addContent(split);
		
		Element resourcing = generateTaskResourcing();
		task.addContent(resourcing);
		
		return task;
		
	}
	
	private static Element generateTaskResourcing() {
		Element resourcing = new Element("resourcing", defaultNamespace);
		
		Element offer = new Element("offer", defaultNamespace);
		offer.setAttribute("initiator","user");
		Element allocate = new Element("allocate", defaultNamespace);
		allocate.setAttribute("initiator","user");
		Element start = new Element("start", defaultNamespace);
		start.setAttribute("initiator","user");
		
		
		resourcing.addContent(offer);
		resourcing.addContent(allocate);
		resourcing.addContent(start);
		
		return resourcing;
	}
	
	public static Element generateNode(YNode node) {

		if(node.type == YNodeType.INPUT_CONDITION) {
			return generateInputCondition(node);
		}else if(node.type == YNodeType.OUTPUT_CONDITION) {
			return generateOutputCondition(node);
		}else {
			return generateTask(node);
		}
	}
	
	public static String getNodeMappingValue(Node node) {
		if(node.type == NodeType.InitialNode) {
			return "InputCondition";
		}else if(node.type == NodeType.ActivityFinalNode) {
			return "OutputCondition";
		}else {
			return node.name;
		}
	}
	
	
}
