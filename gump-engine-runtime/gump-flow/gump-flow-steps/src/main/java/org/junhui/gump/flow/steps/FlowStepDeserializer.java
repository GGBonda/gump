package org.junhui.gump.flow.steps;

import com.google.gson.*;
import org.junhui.gump.flow.core.step.FlowStep;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;
import org.junhui.gump.flow.steps.function.CallRpcStep;
import org.junhui.gump.flow.steps.prime.ForStep;
import org.junhui.gump.flow.steps.prime.IfStep;
import org.junhui.gump.flow.steps.prime.InputStep;
import org.junhui.gump.flow.steps.prime.OutputStep;

import java.lang.reflect.Type;

public class FlowStepDeserializer implements JsonDeserializer<FlowStep>, JsonSerializer<FlowStep> {

    @Override
    public FlowStep deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String stepType = jsonObject.get("type").getAsString();

        switch (stepType) {
            case FlowTypeConstants.IF_STEP_TYPE:
                return jsonDeserializationContext.deserialize(jsonElement, IfStep.class);
            case FlowTypeConstants.INPUT_STEP_TYPE:
                return jsonDeserializationContext.deserialize(jsonElement, InputStep.class);
            case FlowTypeConstants.OUTPUT_STEP_TYPE:
                return jsonDeserializationContext.deserialize(jsonElement, OutputStep.class);
            case FlowTypeConstants.FOR_STEP_TYPE:
                return jsonDeserializationContext.deserialize(jsonElement, ForStep.class);
            case FlowTypeConstants.CALL_RPC_STEP_TYPE:
                return jsonDeserializationContext.deserialize(jsonElement, CallRpcStep.class);
        }
        return null;
    }

    @Override
    public JsonElement serialize(FlowStep src, Type typeOfSrc, JsonSerializationContext context) {
        final Type targetType = src != null ? src.getClass() : typeOfSrc;
        return context.serialize(src, targetType);
    }

}
