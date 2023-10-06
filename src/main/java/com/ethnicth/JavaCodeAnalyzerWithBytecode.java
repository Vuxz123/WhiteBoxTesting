package com.ethnicth;

import com.sun.source.tree.MethodTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreeScanner;
import com.sun.source.util.Trees;
import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class JavaCodeAnalyzerWithBytecode {
    public static void main(String[] args) {
        String sourceFilePath = "D:\\a_project\\JavaProject\\WhiteBoxTesting\\src\\main\\java\\com\\ethnicth\\analyzer\\method\\Test.java"; // Replace with the path to your .java file
        String methodNameToAnalyze = "doSomething"; // Replace with the method name to analyze

        analyzeJavaCode(sourceFilePath, methodNameToAnalyze);
    }

    private static void analyzeJavaCode(String sourceFilePath, String methodNameToAnalyze) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> sourceFiles = fileManager.getJavaFileObjects(new File(sourceFilePath));
            JavacTask task = (JavacTask) compiler.getTask(null, fileManager, null, null, null, sourceFiles);

            Trees trees = Trees.instance(task);
            TreeScanner<Void, String> methodFinder = new MethodFinder(trees, task);
            methodFinder.scan(task.parse().iterator().next(), methodNameToAnalyze);
            String escapedInput = Pattern.quote(".");

            String classFilePath = "D:\\a_project\\JavaProject\\WhiteBoxTesting\\target\\classes\\" + task.getElements().getTypeElement("com.ethnicth.analyzer.method.Test").getQualifiedName().toString().replaceAll(escapedInput, "\\\\") + ".class";

            analyzeBytecode(classFilePath, methodNameToAnalyze);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MethodFinder extends TreeScanner<Void, String> {
        private final Trees trees;
        private final JavacTask task;

        public MethodFinder(Trees trees, JavacTask task) {
            this.trees = trees;
            this.task = task;
        }

        @Override
        public Void visitMethod(MethodTree methodTree, String methodNameToAnalyze) {
            System.out.println("\nFound method: " + methodTree.getName());
//                    long startPos = trees.getSourcePositions().getStartPosition(task.parse().iterator().next(), methodTree);
//                    long endPos = trees.getSourcePositions().getEndPosition(task.parse().iterator().next(), methodTree);
            System.out.println("Method Source Code: ");
            List<? extends StatementTree> statementTreeList = methodTree.getBody().getStatements();
            statementTreeList.forEach(System.out::println);
            return super.visitMethod(methodTree, methodNameToAnalyze);
        }
    }

    private static void analyzeBytecode(String classFilePath, String methodNameToAnalyze) {
        System.out.println(classFilePath);
        try (FileInputStream fileInputStream = new FileInputStream(classFilePath)) {
            ClassReader classReader = new ClassReader(fileInputStream);
            StringWriter sw = new StringWriter();
            TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(System.out));
            classReader.accept(tcv, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
