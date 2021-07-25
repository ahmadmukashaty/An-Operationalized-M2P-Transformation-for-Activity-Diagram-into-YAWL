package TransformationTest.yawl;

import java.util.ArrayList;
import java.util.List;

import TransformationTest.model.Node;
import TransformationTest.model.NodeType;
import TransformationTest.model.YNode;
import TransformationTest.model.YNodeType;
import TransformationTest.model.Yawl;

public class YawlManagement {
	
	private static Yawl yawlDiagram = new Yawl();
	
	public static void insertNode(YNode node) {
		yawlDiagram.nodes.add(node);
	}
	
	public static List<YNode> getAllNodes() {
		return yawlDiagram.nodes;
	}
	
	public static YNode getInputCondition() {
		
		for(YNode node : yawlDiagram.nodes) {
			if(node.type == YNodeType.INPUT_CONDITION)
				return node;
		}
		return null;
	}
	
	public static YNode getOutputCondition() {
		
		for(YNode node : yawlDiagram.nodes) {
			if(node.type == YNodeType.OUTPUT_CONDITION)
				return node;
		}
		return null;
	}
	
	public static YNode getNodeByName(String nodeName) {
		for(YNode node : yawlDiagram.nodes) {
			if(node.id == nodeName)
				return node;
		}
		return null;
	}
	
	public static YNode getMappedYNode(Node node) {
		if(node.type == NodeType.InitialNode) {
			return getInputCondition();
		}
		if(node.type == NodeType.ActivityFinalNode) {
			return getOutputCondition();
		}
		for(YNode ynode : yawlDiagram.nodes) {
			if(ynode.id == node.name)
				return ynode;
		}
		return null;
	}
	
	public static void implementReduction(YNode removedNode, YNode unnamedNode) {
		
		String unnamedNodeId = unnamedNode.id;
		
		unnamedNode.id = removedNode.id;
		unnamedNode.name = removedNode.name;
		
		//get pre of node & match them with unnamed node
		for(YNode ynode : yawlDiagram.nodes) {
			for(YNode outgoingNode : ynode.flowsInto) {
				if(outgoingNode.id == removedNode.id) {
					ynode.flowsInto.remove(outgoingNode);
					ynode.flowsInto.add(unnamedNode);
					break;
				}
			}
		}
		
		//remove node
		for(YNode ynode : yawlDiagram.nodes) {
			if(ynode.id == removedNode.id) {
				 yawlDiagram.nodes.remove(ynode);
				 break;
			}
		}
		
		//update UnnamedNode Info
		for(YNode ynode : yawlDiagram.nodes) {
			if(ynode.id == unnamedNodeId) {
				ynode = unnamedNode;
			}
		}
		

	}
	
	public static void sortNodes() {
		List<YNode> nodes = new ArrayList<YNode>();
		for(YNode node: yawlDiagram.nodes) {
			if(node.type != YNodeType.OUTPUT_CONDITION) {
				nodes.add(node);
			}
		}
		nodes.add(getOutputCondition());
		yawlDiagram.nodes = nodes;
	}
	
	public static void printData() {
		System.out.println("Nodes Are :");
		for(YNode node: yawlDiagram.nodes) {
			System.out.println("Id : " + node.id + " Name : " + node.name + " Type : " + node.type + " Split : " + node.splitType + " Join : " + node.joinType);
			System.out.println("flowsInto :");
			for(YNode n: node.flowsInto) {
				System.out.println("Id : " + n.id + " Name : " + n.name + " Type : " + n.type + " Split : " + n.splitType + " Join : " + n.joinType);
			}

			System.out.println("Done");
		}
	}
	
}
