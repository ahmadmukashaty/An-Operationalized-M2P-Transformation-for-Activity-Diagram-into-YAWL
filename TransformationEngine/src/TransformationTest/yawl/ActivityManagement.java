package TransformationTest.yawl;

import java.util.ArrayList;
import java.util.List;

import TransformationTest.model.*;

public class ActivityManagement {
	
	private static Activity activityDiagram = new Activity();
	
	public static String getActivityName() {
		
		return activityDiagram.name;
	}
	
	public static boolean hasNodes() {
		
		return (activityDiagram.nodes.size() > 0);
	}
	
	public static List<Node> getAllNodes(){
		
		return activityDiagram.nodes;
	}
	
	public static List<Edge> getAllEdges(){
		
		return activityDiagram.edges;
	}
	
	public static List<Edge> getOutgoingEdges(Node node){
		List<Edge> edges = new ArrayList<Edge>();
		for(Edge edge: activityDiagram.edges) {
			if(edge.sourceNode == node.name) {
				edges.add(edge);
			}
		}
		return edges;
	}
	
	public static List<Edge> getIncomingEdges(Node node){
		List<Edge> edges = new ArrayList<Edge>();
		for(Edge edge: activityDiagram.edges) {
			if(edge.targetNode == node.name) {
				edges.add(edge);
			}
		}
		return edges;
	}
	
	public static Node getInputConditionNode(){
		
		for(Node node: activityDiagram.nodes) {
			if(node.type == NodeType.InitialNode) {
				return node;
			}
		}
		return null;
	}
	
	public static Node getOutputConditionNode(){
		
		for(Node node: activityDiagram.nodes) {
			if(node.type == NodeType.ActivityFinalNode) {
				return node;
			}
		}
		return null;
	}
	
	public static Node getNodeByName(String nodeName){
		
		for(Node node: activityDiagram.nodes) {
			if(node.name == nodeName) {
				return node;
			}
		}
		return null;
	}
	
	public static Node getMappedNode(YNode ynode) {
		if(ynode.type == YNodeType.INPUT_CONDITION) {
			return getInputConditionNode();
		}
		if(ynode.type == YNodeType.OUTPUT_CONDITION) {
			return getOutputConditionNode();
		}
		for(Node node : activityDiagram.nodes) {
			if(node.name == ynode.id)
				return node;
		}
		return null;
	}
	
	public static void printData() {
		System.out.println("Nodes Are :");
		for(Node node: activityDiagram.nodes) {
			System.out.println("Name : " + node.name + " Type : " + node.type);
			System.out.println("Incoming :");
			for(Node n: node.inComing) {
				System.out.println("Name : " + n.name + " Type : " + n.type);
			}
			System.out.println("Outgoing :");
			for(Node n: node.outGoing) {
				System.out.println("Name : " + n.name + " Type : " + n.type);
			}
			System.out.println("Done");
		}
	}
}
