package org.junhui.gump.dev.web.entity;

import java.util.List;

public class GumpFunctionInfoDTO {

    private String desc;

    private List<FunctionItemDTO> functionItems;

    public static class FunctionItemDTO {

        private String code;

        private String defaultInsertCode;

        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDefaultInsertCode() {
            return defaultInsertCode;
        }

        public void setDefaultInsertCode(String defaultInsertCode) {
            this.defaultInsertCode = defaultInsertCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<FunctionItemDTO> getFunctionItems() {
        return functionItems;
    }

    public void setFunctionItems(List<FunctionItemDTO> functionItems) {
        this.functionItems = functionItems;
    }
}
