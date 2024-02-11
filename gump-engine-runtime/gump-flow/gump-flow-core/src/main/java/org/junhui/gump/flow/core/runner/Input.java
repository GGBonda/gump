package org.junhui.gump.flow.core.runner;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class Input {

    /**
     * flow运行过程中的header信息
     * */
    private final Header header;

    private final Map<String, Object> input;

    public static class Header {

    }
}
