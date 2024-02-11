package org.junhui.gump.flow.core.entity;



import lombok.Getter;
import lombok.Setter;
import org.junhui.gump.common.entity.BaseMetadata;
import org.junhui.gump.flow.core.step.FlowStep;

import java.util.List;

@Getter
@Setter
public class GumpFlow extends BaseMetadata {

    private List<FlowStep> steps;
}
