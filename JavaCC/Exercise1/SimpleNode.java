/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 7.0 */
/* JavaCCOptions:MULTI=false,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  public int id;
  protected Object value;
  protected Calculator parser;

  // added
  public int val;
  public String var;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(Calculator p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) { this.value = value; }
  public Object jjtGetValue() { return value; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() {
    return CalculatorTreeConstants.jjtNodeName[id];
  }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));

    switch(this.id) {
      case CalculatorTreeConstants.JJTADD:
        System.out.println("\t[ + ]");break;
      case CalculatorTreeConstants.JJTSUB:
        System.out.println("\t[ - ]");break;
      case CalculatorTreeConstants.JJTMUL:
        System.out.println("\t[ * ]");break;
      case CalculatorTreeConstants.JJTDIV:
        System.out.println("\t[ / ]");break;
      case CalculatorTreeConstants.JJTASSIGN:
        System.out.println("\t[ " + this.var + " ]");break;
      case CalculatorTreeConstants.JJTRETRIEVE:
        System.out.println("\t[ " + this.var + " ]");break;
    }

    if(children == null && this.id != CalculatorTreeConstants.JJTRETRIEVE) {
      System.out.println("\t[ " + this.val + " ]");
    }
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        if (n != null) {
          n.dump(prefix + " ");
        }
      }
    }
  }

  public int getId() {
    return id;
  }
}

/* JavaCC - OriginalChecksum=31cdd72a253d260f01fedc6c5a0e3edc (do not edit this line) */
