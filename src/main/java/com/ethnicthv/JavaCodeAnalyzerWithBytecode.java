package com.ethnicthv;

import com.ethnicthv.sim.runtime.executor.Execution;
import com.ethnicthv.sim.runtime.executor.Executor;
import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreeScanner;
import com.sun.source.util.Trees;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import javax.lang.model.element.Modifier;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class JavaCodeAnalyzerWithBytecode {
    public static void main(String[] args) {
        String sourceFilePath = "D:\\JavaProject\\WhiteBoxTesting\\JavaRuntime\\src\\main\\java\\com\\ethnicthv\\sim\\runtime\\method\\component\\MonoDirectionNode.java"; // Replace with the path to your .java file
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

//            String classFilePath = "target\\classes\\" + task.getElements().getTypeElement("com.ethnicth.sim.method.component.MonoDirectionNode").getQualifiedName().toString().replaceAll(escapedInput, "\\\\") + ".class";
//
//            analyzeBytecode(classFilePath, methodNameToAnalyze);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        Execution a = new Execution(FunctionConstraints.E.aconst_null, (classFile, frame, index) -> {});
        Executor b = a.EXECUTOR;
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
            if (methodTree.getModifiers().getFlags().contains(Modifier.ABSTRACT)) {
                System.out.println("Abstract");
                return super.visitMethod(methodTree, methodNameToAnalyze);
            }
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
