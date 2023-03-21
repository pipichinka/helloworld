package ru.nsu.Sirotkin.task1.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ContextImplementation implements Context {
    private final Map<String, Double> defineMap;


    private final Stack<Double> valuesStack;


    public ContextImplementation(){
        defineMap = new HashMap<>();
        valuesStack = new Stack<>();
    }


    public void setDefine(String defineKey, Double defineValue){
        defineMap.put(defineKey,defineValue);
    }


    public void pushToStack(Double value){
        valuesStack.push(value);
    }


    public Double getFromStack(){
        if (valuesStack.empty()){
            return null;
        }
        return valuesStack.pop();
    }


    public Double getDefine(String defineKey){
        return defineMap.getOrDefault(defineKey,null);
    }

    @Override
    public Double peekFromStack() {
        if (valuesStack.empty()){
            return null;
        }
        return valuesStack.peek();
    }


}
