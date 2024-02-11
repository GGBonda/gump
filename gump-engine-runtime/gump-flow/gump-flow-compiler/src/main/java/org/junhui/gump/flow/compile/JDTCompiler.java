package org.junhui.gump.flow.compile;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.junhui.gump.flow.core.compile.java.JavaClassCompiler;
import org.junhui.gump.flow.core.runner.BaseFlowRunner;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.flow.core.runner.Input;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import static org.eclipse.jdt.internal.compiler.impl.CompilerOptions.*;

public class JDTCompiler implements JavaClassCompiler {
    public static final FlowClassLoader FLOW_CLASS_LOADER = new FlowClassLoader(JDTCompiler.class.getClassLoader());
    private static final CompilerOptions COMPILER_OPTIONS;

    static {
        Map<String, String> settings = new HashMap<>();

        settings.put(OPTION_ReportMissingSerialVersion, IGNORE);
        settings.put(OPTION_LineNumberAttribute, GENERATE);
        settings.put(OPTION_SourceFileAttribute, GENERATE);
        settings.put(OPTION_LocalVariableAttribute, GENERATE);
        settings.put(OPTION_PreserveUnusedLocal, PRESERVE);
        settings.put(OPTION_ReportDeprecation, IGNORE);
        settings.put(OPTION_ReportUnusedImport, IGNORE);
        settings.put(OPTION_Encoding, "UTF-8");
        settings.put(OPTION_Process_Annotations, ENABLED);
        settings.put(OPTION_Source, VERSION_1_8);
        settings.put(OPTION_TargetPlatform, VERSION_1_8);
        settings.put(OPTION_Compliance, VERSION_1_8);

        COMPILER_OPTIONS = new CompilerOptions(settings);
    }

    @Override
    public Map<String, Class<?>> compile(Map<String, String> javaCodes) {
        FlowClassLoader flowClassLoader = new FlowClassLoader(JDTCompiler.class.getClassLoader());
        compileWithClassLoader(javaCodes, flowClassLoader);

        Map<String, Class<?>> map = new HashMap<>(javaCodes.size());
        javaCodes.forEach((k, v) -> {
            try {
                map.put(k, flowClassLoader.findClass(k));
            } catch (ClassNotFoundException ignored) {
            }
        });
        return map;
    }
    /*public Map<String, Class<?>> compile(Map<String, String> javaCodes) {
        compileWithClassLoader(javaCodes, FLOW_CLASS_LOADER);

        Map<String, Class<?>> map = new HashMap<>(javaCodes.size());
        javaCodes.forEach((k, v) -> {
            try {
                map.put(k, FLOW_CLASS_LOADER.findClass(k));
            } catch (ClassNotFoundException ignored) {
            }
        });
        return map;
    }*/

    private void compileWithClassLoader(Map<String, String> javaCodes, FlowClassLoader flowClassLoader) {
        INameEnvironment env = new JDTNameEnvironment(javaCodes);
        IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.exitOnFirstError();
        IProblemFactory problemFactory = new DefaultProblemFactory(Locale.ENGLISH);

        JDTCompilerRequestor result = new JDTCompilerRequestor(flowClassLoader);
        ICompilationUnit[] compilationUnits = new ICompilationUnit[javaCodes.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : javaCodes.entrySet()) {
            compilationUnits[i] = new JDTCompilationUnit(entry.getKey(), entry.getValue());
            i++;
        }

        Compiler compiler = new Compiler(env, policy, COMPILER_OPTIONS, result, problemFactory);

        compiler.compile(compilationUnits);

        List<IProblem> problems = result.getProblems();
        if (!problems.isEmpty()) {
            StringBuilder errorInfo = new StringBuilder();
            errorInfo.append("\n");
            errorInfo.append("  classCodes: ").append(javaCodes.keySet()).append("\n");
            errorInfo.append("  errorList:\n");
            problems.forEach(problem -> {
                String errorMessage = String.format("%s line %d : %s",
                        new String(problem.getOriginatingFileName()), problem.getSourceLineNumber(), problem.getMessage());
                errorInfo.append("    class ").append(errorMessage).append("\n");
            });
            throw new RuntimeException(errorInfo.toString());
        }
    }
}
