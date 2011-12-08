package org.apache.jena.examples;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExampleJRuby_01 {

    public static void main(String[] args) {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine ruby = sem.getEngineByExtension("rb");
        try {
            ruby.eval("puts 'Hello World!'");
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
}
