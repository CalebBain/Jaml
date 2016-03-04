package nodeList;

import java.util.ArrayList;
import java.util.List;

public class ComponentNode implements node {

    private List<NodeCall> calls = new ArrayList<>();

    public ComponentNode() {}
    public ComponentNode(String prop) { addCalls(prop); }

    private ChildNode childCalls = new ChildNode();
    private FunctionNode functionCalls = new FunctionNode();

    public node addCalls(String prop){
        String[] calls = prop.split("\r\n(\t)?");
        for(String call: calls){
            String[] p = call.split("::"), p2 = p[0].split(":"), p3 = new String[0];
            if(p.length > 1) p3 = p[1].split(":");
            String value = p2[0];
            switch(p2[0]){
                case "trySetNameText":
                case "trySetName":
                    if(p2.length == 2) value += "Events";
                    this.calls.add(new NodeCall(value, p3[0]));break;
                case "trySetNameValue":
                    if(p2.length == 2) value += "Events";
                    this.calls.add(new NodeCall(value, p3[0], p3[1]));break;
                case "tryMap": this.calls.add(new NodeCall("tryMap", p2[1], p3[0], p3[1])); break;
                case "tryList":
                case "trySize":
                case "tryCheck":
                case "tryValue":
                case "tryBoolean":
                    this.calls.add(new NodeCall(p2[0], p3[0], p3[1])); break;
                case "tryFlags": this.calls.add(new NodeCall("tryFlags", p3[0], p2[1])); break;
                case "addChild":
                case "addChildLayout":
                    this.calls.add(new NodeCall(p2[0], p3[0])); break;
                case "write":
                    if(p2.length == 2) this.calls.add(new NodeCall("writeName", p3[0]));
                    else this.calls.add(new NodeCall("write", p3[0])); break;
                case "call":
                    this.calls.add(new NodeCall("call", p2[1])); break;
                case "setStyle":
                case "functions":
                    this.calls.add(new NodeCall(p2[0])); break;
            }
        }
        return this;
    }

    public void addChildCalls(String prop){ childCalls = new ChildNode(prop); }

    public ChildNode getChildCalls() { return childCalls; }

    public void addFunctionCalls(String prop){ functionCalls = new FunctionNode(prop); }

    public FunctionNode getFunctionCalls() { return functionCalls; }

    public List<NodeCall> getCalls(){ return calls; }

    public boolean Type(Class c) { return (this.getClass() == c); }
}