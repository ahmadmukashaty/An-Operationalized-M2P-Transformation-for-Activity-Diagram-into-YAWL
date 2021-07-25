package TransformationTest.yawl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import TransformationTest.model.Edge;
import TransformationTest.model.Node;
import TransformationTest.model.NodeType;
import TransformationTest.model.YGate;
import TransformationTest.model.YNode;
import TransformationTest.model.YNodeType;

public class TransformationManagement {
	private static List<String> generatedYNodesList = new ArrayList<String>();
	public static void transform() {
		
		// get initial Node
		Node startNode = ActivityManagement.getInputConditionNode();
		generateAllYawlNodes(startNode);
		generateAllYawlEdges();
		yawlNodesReduction();
		YawlManagement.sortNodes();
		
	}
	
	private static void generateAllYawlNodes(Node initNode){
		Queue<Node> q = new LinkedList<Node>();
		q.add(initNode);
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(!generatedYNodesList.contains(node.name)) {
				generateYawlNode(node);
				generatedYNodesList.add(node.name);
				ArrayList<Edge> edges = (ArrayList<Edge>)ActivityManagement.getOutgoingEdges(node);
				for(Edge edge : edges) {
					Node tNode = ActivityManagement.getNodeByName(edge.targetNode);
					if(tNode != null) {
						q.add(tNode);
					}
				}
			}
		}
	}
	
	private static void generateAllYawlEdges() {
		for(Node node : ActivityManagement.getAllNodes()) {
			YNode ynode = YawlManagement.getMappedYNode(node);
			for(Node n : node.outGoing) {
				YNode yn = YawlManagement.getMappedYNode(n);
				ynode.flowsInto.add(yn);
			}
		}
	}
	
	private static void yawlNodesReduction() {
		
		boolean reductionMatch = false;
		for(YNode ynode : YawlManagement.getAllNodes()) {
			if(ynode.type == YNodeType.TASK) {
				for(YNode n : ynode.flowsInto) {				
					if(n.name == "" && n.type == YNodeType.TASK) {
						// check if has one input which is the same node					
						Node node = ActivityManagement.getMappedNode(n);
						if(node.inComing.size() == 1) {
							Node incomingNode = node.inComing.get(0);
							if(incomingNode.name == ynode.id) {
								reductionMatch = true;
								//make the reduction
								YawlManagement.implementReduction(ynode, n);
							}
						}
					}
					if(reductionMatch) {
						break;
					}
				}
			}
			if(reductionMatch) {
				break;
			}
		}
		if(reductionMatch) {
			yawlNodesReduction();
		}
	}
	
	private static YNode generateYawlNode(Node node) {
		
		if(node.type == NodeType.InitialNode) {
			return generateInputCondition(node);
		}else if (node.type == NodeType.ActivityFinalNode) {
			return generateOutputCondition(node);
		}else if (node.type == NodeType.DecisionNode) {
			generateTaskDecisionNode(node);
		}else if (node.type == NodeType.MergeNode) {
			generateTaskMergeNode(node);
		}else if (node.type == NodeType.ForkNode) {
			generateTaskForkNode(node);
		}else if (node.type == NodeType.JoinNode) {
			generateTaskJoinNode(node);
		}else if (node.type == NodeType.OpaqueAction) {
			generateTaskNode(node);
		}
		return null;
	}
	
	private static YNode generateInputCondition(Node node) {
		YNode ynode = new YNode();
		ynode.id = "InputCondition";
		ynode.name = "";
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.xor;
		ynode.splitType = YGate.xor;
		ynode.type = YNodeType.INPUT_CONDITION;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
	
	private static YNode generateOutputCondition(Node node) {
		YNode ynode = new YNode();
		ynode.id = "OutputCondition";
		ynode.name = "";
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.xor;
		ynode.splitType = YGate.xor;
		ynode.type = YNodeType.OUTPUT_CONDITION;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
	
	private static YNode generateTaskDecisionNode(Node node) {
		YNode ynode = new YNode();
		ynode.id = node.name;
		ynode.name = "";
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.xor;
		ynode.splitType = YGate.xor;
		ynode.type = YNodeType.TASK;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
	
	private static YNode generateTaskMergeNode(Node node) {
		YNode ynode = new YNode();
		ynode.id = node.name;
		ynode.name = "";
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.xor;
		ynode.splitType = YGate.xor;
		ynode.type = YNodeType.TASK;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
	
	private static YNode generateTaskForkNode(Node node) {
		YNode ynode = new YNode();
		ynode.id = node.name;
		ynode.name = "";
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.xor;
		ynode.splitType = YGate.and;
		ynode.type = YNodeType.TASK;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
	
	private static YNode generateTaskJoinNode(Node node) {
		YNode ynode = new YNode();
		ynode.id = node.name;
		ynode.name = "";
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.and;
		ynode.splitType = YGate.xor;
		ynode.type = YNodeType.TASK;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
	
	private static YNode generateTaskNode(Node node) {
		YNode ynode = new YNode();
		ynode.id = node.name;
		ynode.name = node.name;
		ynode.flowsInto = new ArrayList<YNode>();
		ynode.joinType = YGate.xor;
		ynode.splitType = YGate.xor;
		ynode.type = YNodeType.TASK;
		YawlManagement.insertNode(ynode);
		return ynode;
	}
}
