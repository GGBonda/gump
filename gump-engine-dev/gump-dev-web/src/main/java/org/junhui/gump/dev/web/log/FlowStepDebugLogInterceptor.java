package org.junhui.gump.dev.web.log;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.junhui.gump.flow.core.interceptor.FlowStepInterceptor;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.flow.core.runner.RuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FlowStepDebugLogInterceptor implements FlowStepInterceptor {

    private static final ThreadLocal<Long> STEP_START_TIME = new TransmittableThreadLocal<>();

    @Autowired
    private FlowDebugLogService flowDebugLogService;

    @Override
    public void before(FlowContext context) {
        STEP_START_TIME.set(System.currentTimeMillis());
    }

    @Override
    public void after(FlowContext context) {
        try {
            Map<String, Object> stepOutputMap = context.getStepOutputMap();
            if (MapUtils.isEmpty(stepOutputMap)) {
                return;
            }

            FlowDebugLogDTO logDTO = new FlowDebugLogDTO();
            logDTO.setConsumeTime(System.currentTimeMillis() - STEP_START_TIME.get());

            if (context.stackEmpty()) {
                String currentStepCode = context.getCurrentStepCode();
                logDTO.setStepCode(currentStepCode);
                logDTO.setOutput(stepOutputMap.get(currentStepCode));
            } else {
                String preStepCode = context.getPreStepCode();
                logDTO.setStepCode(preStepCode);
                logDTO.setOutput(stepOutputMap.get(preStepCode));
            }

            List<FlowDebugLogDTO> logDTOList = flowDebugLogService.get(RuntimeEnv.traceId());
            if (CollectionUtils.isEmpty(logDTOList)) {
                logDTOList = new ArrayList<>();
                logDTOList.add(logDTO);
                flowDebugLogService.setCache(RuntimeEnv.traceId(), logDTOList);
            } else {
                logDTOList.add(logDTO);
            }
        } finally {
            STEP_START_TIME.remove();
        }
    }
}
