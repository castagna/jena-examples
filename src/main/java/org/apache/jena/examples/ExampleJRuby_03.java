package org.apache.jena.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExampleJRuby_03 {

    public static void main(String[] args) throws FileNotFoundException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine ruby = sem.getEngineByExtension("rb");
        try {
            FileReader reader = new FileReader(new File("src/main/jruby", "hello_jena.rb"));
            ruby.eval(reader);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
}
