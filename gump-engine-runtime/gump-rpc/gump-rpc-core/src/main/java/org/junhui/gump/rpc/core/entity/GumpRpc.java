package org.junhui.gump.rpc.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.junhui.gump.common.entity.BaseMetadata;
import org.junhui.gump.common.entity.FlowParam;

import java.util.List;

@Getter
@Setter
public class GumpRpc extends BaseMetadata {

    private String application;

    private String service;

    private String method;

    private List<FlowParam> input;

    //预处理脚本
    private String script;

    private List<FlowParam> output;
}
