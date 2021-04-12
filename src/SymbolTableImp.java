import pt.up.fe.comp.jmm.analysis.table.Symbol;
import pt.up.fe.comp.jmm.analysis.table.SymbolTable;
import pt.up.fe.comp.jmm.analysis.table.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTableImp implements SymbolTable {
    List<String> imports = new ArrayList();
    String className = "";
    String superClass = "";
    HashMap<Symbol, Boolean> fields = new HashMap<>();   // Boolean is true if the variable has been assigned
    HashMap<String, MethodTable> methods = new HashMap<>();

    @Override
    public List<String> getImports() {
        return imports;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getSuper() {
        return superClass;
    }

    @Override
    public List<Symbol> getFields() {
        return new ArrayList<>(fields.keySet());
    }

    @Override
    public List<String> getMethods() {
        return new ArrayList<>(methods.keySet());
    }

    public MethodTable getMethod(String signature) {
        return methods.get(signature);
    }

    @Override
    public Type getReturnType(String signature) {
        return methods.get(signature).getReturnType();
    }

    @Override
    public List<Symbol> getParameters(String signature) {
        return new ArrayList<>(methods.get(signature).getParameters());
    }

    @Override
    public List<Symbol> getLocalVariables(String signature) {
        return new ArrayList<>(methods.get(signature).getLocalVariables().keySet());
    }

    public boolean hasImport(String _import){
        return this.imports.contains(_import);
    }

    public boolean hasSuperClass(){return !this.superClass.equals("");}

    public void addImport(String _import) {
        this.imports.add(_import);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public void addField(Type type, String name) {
        fields.put(new Symbol(type, name), false);
    }

    public void addField(Symbol symbol) {
        fields.put(symbol, false);
    }

    public void assignField(String name) {
        for (Symbol symbol : fields.keySet()) {
            if (symbol.getName().equals(name))
                fields.put(symbol, true);
        }
    }

    public void addMethod(String methodSignature, MethodTable methodTable) {
        this.methods.put(methodSignature, methodTable);
    }

    public void assignMethodVariable(String methodSignature, String variableName) {
        methods.get(methodSignature).assignVariable(variableName);
    }

    public boolean isAssigned(String methodSignature, String identifier) {
        // Searching the symbols of the local variables to see if the variable is initialized
        HashMap<Symbol, Boolean> localVariables = getMethod(methodSignature).getLocalVariables();
        for (Symbol i : localVariables.keySet()) {
            if (i.getName().equals(identifier) && localVariables.get(i)) {
                return true;
            }
        }

        // Searching the symbols of the class variables to see if the variable is initialized
        for (Symbol i : fields.keySet()) {
            if (i.getName().equals(identifier) && fields.get(i)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "SymbolTableImp{" +
                "imports=" + imports +
                ", className='" + className + '\'' +
                ", superClass='" + superClass + '\'' +
                ", fields=" + fields +
                ", methods=" + methods +
                '}';
    }
}
