package com.ethnicthv;

import com.sun.source.tree.BlockTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreeScanner;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    static String sourceFilePath = "D:\\a_project\\JavaProject\\WhiteBoxTesting\\src\\main\\java\\com\\ethnicth\\analyzer\\method\\Test.java"; // Replace with the path to your .java file
    static String outputDirectory = "output\\";
    static JavaCompiler compiler;
    static StandardJavaFileManager fileManager;

    static  {
        compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            try {
                throw new Exception("Java compiler not found. Ensure you're using a JDK.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        fileManager = compiler.getStandardFileManager(null, null, null);
    }

    public static void main(String[] args) {
        // Prepare the source file
        analyzeAST(sourceFilePath);

        // You can now analyze the parsed Abstract Syntax Tree (AST) if the compilation was successful.
        // The Java Compiler API provides ways to access and analyze the AST, but it requires more code.
    }



    private static void analyzeAST(String sourceFilePath) {
        try {
            // Create a compilation task to parse the code
            Iterable<? extends JavaFileObject> sourceFiles = fileManager.getJavaFileObjects(new File(sourceFilePath));
            JavacTask task = (JavacTask) compiler.getTask(null, fileManager, null, null, null, sourceFiles);

            // Get the CompilationUnitTree
            CompilationUnitTree compilationUnitTree = task.parse().iterator().next();

            // Create an instance of MyTreeVisitor and visit the CompilationUnitTree
            MyTreeVisitor visitor = new MyTreeVisitor(task);
            compilationUnitTree.accept(visitor, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MyTreeVisitor extends TreeScanner<Void, Void> {
        private final JavacTask task;

        MyTreeVisitor(JavacTask task) {
            this.task = task;
        }

        @Override
        public Void visitMethod(MethodTree methodTree, Void aVoid) {
            // This method is called when a method declaration is encountered in the AST.
            // You can analyze the method here.
            System.out.println("Method Name: " + methodTree.getName());
            BlockTree methodBody = methodTree.getBody();
            if (methodBody != null) {
                List<? extends StatementTree> statements = methodBody.getStatements();
                for (StatementTree statement : statements) {
                    System.out.println("Statement: " + statement);
                }
            } else {
                System.out.println("Method has no body.");
            }

            return super.visitMethod(methodTree, aVoid);
        }
    }


}