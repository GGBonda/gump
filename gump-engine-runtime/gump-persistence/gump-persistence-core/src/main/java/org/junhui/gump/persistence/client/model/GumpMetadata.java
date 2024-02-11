package org.junhui.gump.persistence.client.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GumpMetadata {

    private final Long nodeId;

    private final String code;

    private final String desc;

    private final String type;

    private final String appCode;

    private final String content;

}
