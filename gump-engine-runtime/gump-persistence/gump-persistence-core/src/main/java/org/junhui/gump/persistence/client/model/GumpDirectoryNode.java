package org.junhui.gump.persistence.client.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GumpDirectoryNode {

    private final Long id;

    private final String appCode;

    private final Boolean folder;

    private final String code;

    private final Long parentId;

    private final String desc;

    private final String type;
}
