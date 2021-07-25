package TransformationTest.model;
/* Generated Class From UML AD */
import java.util.ArrayList;
import java.util.List;

public class Activity {
	public String name;
	public List<Node> nodes;
	public List<Edge> edges;

	public Activity(){
		this.name = "Sample";
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	
		//filling the model with UML Data
		Node node1 = new Node();
		node1.name = "Arrange Regular Delivery";
		node1.type = NodeType.OpaqueAction;
		this.nodes.add(node1);
		Node node2 = new Node();
		node2.name = "Arrange Overnight Delivery";
		node2.type = NodeType.OpaqueAction;
		this.nodes.add(node2);
		Node node3 = new Node();
		node3.name = "Recieve Order";
		node3.type = NodeType.OpaqueAction;
		this.nodes.add(node3);
		Node node4 = new Node();
		node4.name = "m1";
		node4.type = NodeType.MergeNode;
		this.nodes.add(node4);
		Node node5 = new Node();
		node5.name = "j1";
		node5.type = NodeType.JoinNode;
		this.nodes.add(node5);
		Node node6 = new Node();
		node6.name = "Start";
		node6.type = NodeType.InitialNode;
		this.nodes.add(node6);
		Node node7 = new Node();
		node7.name = "Fill Order";
		node7.type = NodeType.OpaqueAction;
		this.nodes.add(node7);
		Node node8 = new Node();
		node8.name = "End";
		node8.type = NodeType.ActivityFinalNode;
		this.nodes.add(node8);
		Node node9 = new Node();
		node9.name = "f1";
		node9.type = NodeType.ForkNode;
		this.nodes.add(node9);
		Node node10 = new Node();
		node10.name = "Send Invoice";
		node10.type = NodeType.OpaqueAction;
		this.nodes.add(node10);
		Node node11 = new Node();
		node11.name = "Receive Payment";
		node11.type = NodeType.OpaqueAction;
		this.nodes.add(node11);
		Node node12 = new Node();
		node12.name = "d1";
		node12.type = NodeType.DecisionNode;
		this.nodes.add(node12);
	
		Edge edge1 = new Edge();
		edge1.sourceNode = "Fill Order";
		edge1.targetNode = "d1";
		edge1.type = "ControlFlow";
		edge1.name = "";
		this.edges.add(edge1);
		Edge edge2 = new Edge();
		edge2.sourceNode = "Arrange Overnight Delivery";
		edge2.targetNode = "m1";
		edge2.type = "ControlFlow";
		edge2.name = "";
		this.edges.add(edge2);
		Edge edge3 = new Edge();
		edge3.sourceNode = "j1";
		edge3.targetNode = "End";
		edge3.type = "ControlFlow";
		edge3.name = "";
		this.edges.add(edge3);
		Edge edge4 = new Edge();
		edge4.sourceNode = "Receive Payment";
		edge4.targetNode = "j1";
		edge4.type = "ControlFlow";
		edge4.name = "";
		this.edges.add(edge4);
		Edge edge5 = new Edge();
		edge5.sourceNode = "m1";
		edge5.targetNode = "j1";
		edge5.type = "ControlFlow";
		edge5.name = "";
		this.edges.add(edge5);
		Edge edge6 = new Edge();
		edge6.sourceNode = "d1";
		edge6.targetNode = "Arrange Regular Delivery";
		edge6.type = "ControlFlow";
		edge6.name = "normal order";
		this.edges.add(edge6);
		Edge edge7 = new Edge();
		edge7.sourceNode = "d1";
		edge7.targetNode = "Arrange Overnight Delivery";
		edge7.type = "ControlFlow";
		edge7.name = "rush order";
		this.edges.add(edge7);
		Edge edge8 = new Edge();
		edge8.sourceNode = "Recieve Order";
		edge8.targetNode = "f1";
		edge8.type = "ControlFlow";
		edge8.name = "";
		this.edges.add(edge8);
		Edge edge9 = new Edge();
		edge9.sourceNode = "Arrange Regular Delivery";
		edge9.targetNode = "m1";
		edge9.type = "ControlFlow";
		edge9.name = "";
		this.edges.add(edge9);
		Edge edge10 = new Edge();
		edge10.sourceNode = "f1";
		edge10.targetNode = "Fill Order";
		edge10.type = "ControlFlow";
		edge10.name = "";
		this.edges.add(edge10);
		Edge edge11 = new Edge();
		edge11.sourceNode = "Start";
		edge11.targetNode = "Recieve Order";
		edge11.type = "ControlFlow";
		edge11.name = "";
		this.edges.add(edge11);
		Edge edge12 = new Edge();
		edge12.sourceNode = "f1";
		edge12.targetNode = "Send Invoice";
		edge12.type = "ControlFlow";
		edge12.name = "";
		this.edges.add(edge12);
		Edge edge13 = new Edge();
		edge13.sourceNode = "Send Invoice";
		edge13.targetNode = "Receive Payment";
		edge13.type = "ControlFlow";
		edge13.name = "";
		this.edges.add(edge13);
		addIncomingOutgoingEdges();
	}

	private void addIncomingOutgoingEdges() {
		for(Edge edge : edges) {
			Node source = getNodeByName(edge.sourceNode);
			Node dest = getNodeByName(edge.targetNode);
			
			dest.inComing.add(source);
			source.outGoing.add(dest);
		}
	}
	
	private Node getNodeByName(String nodeName) {
		for(Node node: nodes) {
			if(node.name == nodeName) {
				return node;
			}
		}
		return null;
	}
}


