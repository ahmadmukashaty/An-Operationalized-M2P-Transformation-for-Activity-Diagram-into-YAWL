package TransformationTest.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String name;
	public NodeType type;
	public List<Node> inComing;
	public List<Node> outGoing;	
	
	public Node() {
		this.inComing = new ArrayList<Node>();
		this.outGoing = new ArrayList<Node>();
	}
}

