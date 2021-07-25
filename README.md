# An-Operationalized-M2P-Transformation-for-Activity-Diagram-into-YAWL
Transformation from UML activity diagram to a formal workflow language YAWL (Yet Another Workflow Language) by implementing set of model transformation types and mapping rules in order to bridge between UML-AD to Yawl-Net in both behavioral and functional concepts.

<br>

<p align="center">
    <img src="https://github.com/ahmadmukashaty/An-Operationalized-M2P-Transformation-for-Activity-Diagram-into-YAWL/blob/main/images/ArchitecureOverview.jpg" width="600" height="350"/>
</p>

<br>

In order to achieve model-to-model transformation, which is the transformation from UML-AD to YAWL-NET, the transformation process should pass through different stages of transformation and each stage has set of interactions to implement. The source is an activity diagram built on Papyrus UML which provides service to export the abstract syntax of an activity diagram and its corresponding xml format. The output is an xml (.yawl) file able to be parsed and run by YAWL Application.
