package org.junhui.gump.flow.compile;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;

import java.util.ArrayList;
import java.util.List;

public class JDTCompilerRequestor implements ICompilerRequestor {

    private final List<IProblem> problems = new ArrayList<>(0);
    private final FlowClassLoader classLoader;

    public JDTCompilerRequestor(FlowClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void acceptResult(CompilationResult compilationResult) {
        if (compilationResult.hasProblems()) {
            for (IProblem problem : compilationResult.getProblems()) {
                if (problem.isError()) {
                    problems.add(problem);
                }
            }
        }

        if (problems.isEmpty()) {
            ClassFile[] classFiles = compilationResult.getClassFiles();
            for (ClassFile classFile : classFiles) {
                char[][] compoundName = classFile.getCompoundName();

                StringBuilder className = new StringBuilder();
                String split = "";
                for (char[] chars : compoundName) {
                    className.append(split);
                    className.append(new String(chars));
                    split = ".";
                }

                byte[] bytes = classFile.getBytes();

                classLoader.put(className.toString(), bytes);
            }
        }
    }

    public List<IProblem> getProblems() {
        return problems;
    }
}
