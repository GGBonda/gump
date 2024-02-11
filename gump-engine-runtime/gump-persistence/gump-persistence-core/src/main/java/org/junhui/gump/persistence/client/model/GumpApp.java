package org.junhui.gump.persistence.client.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GumpApp {

    private final String code;

    private final String name;

    private final String desc;
}
