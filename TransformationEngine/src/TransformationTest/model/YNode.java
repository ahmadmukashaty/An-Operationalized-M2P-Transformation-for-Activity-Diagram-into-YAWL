package TransformationTest.model;

import java.util.List;

public class YNode {
	public String id;
	public String name;
	public List<YNode> flowsInto;
	public YNodeType type;
	public YGate joinType;
	public YGate splitType;
}
