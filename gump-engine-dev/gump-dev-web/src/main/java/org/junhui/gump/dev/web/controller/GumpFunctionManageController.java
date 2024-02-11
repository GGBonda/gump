package org.junhui.gump.dev.web.controller;

import org.junhui.gump.dev.web.entity.GumpFunctionInfoDTO;
import org.junhui.gump.flow.core.annotation.GumpFunctionBean;
import org.junhui.gump.flow.core.functions.GumpFunctionManager;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gf")
public class GumpFunctionManageController {


    @GetMapping(value = "queryGumpFunctionInfo", produces = "application/json")
    @ResponseBody
    public List<GumpFunctionInfoDTO> queryGumpFunctionInfo() {
        Map<String, Object> beanMap = GumpFunctionManager.getInstance().getGumpFunctionBeanMap();

        List<GumpFunctionInfoDTO> res = new ArrayList<>(beanMap.values().size());

        for (Object bean : beanMap.values()) {
            GumpFunctionInfoDTO info = new GumpFunctionInfoDTO();

            List<GumpFunctionInfoDTO.FunctionItemDTO> items = new ArrayList<>();
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                GumpFunctionBean.Function annotation = method.getAnnotation(GumpFunctionBean.Function.class);
                if (annotation == null) {
                    continue;
                }
                GumpFunctionInfoDTO.FunctionItemDTO item = new GumpFunctionInfoDTO.FunctionItemDTO();

                item.setCode(annotation.code());
                item.setDefaultInsertCode(annotation.defaultExpr());
                item.setDesc(annotation.desc());
                items.add(item);
            }

            info.setDesc(bean.getClass().getAnnotation(GumpFunctionBean.class).desc());
            info.setFunctionItems(items);
            res.add(info);
        }

        return res;
    }


}
