package org.junhui.gump.rpc.core.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvokeRequest {

    private String application;

    private String service;

    private String method;

    private String targetProviderIP;

    private String[] paramsClassNames;

    private Object[] params;
}
