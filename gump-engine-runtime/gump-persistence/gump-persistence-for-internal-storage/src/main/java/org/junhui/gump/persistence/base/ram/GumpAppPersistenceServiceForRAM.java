package org.junhui.gump.persistence.base.ram;

import org.junhui.gump.persistence.client.api.GumpAppPersistenceService;
import org.junhui.gump.persistence.client.model.GumpApp;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GumpAppPersistenceServiceForRAM implements GumpAppPersistenceService {

    private static final Map<String, GumpApp> data = new HashMap<>();

    @Override
    public void save(GumpApp app) {
        data.put(app.getCode(), app);
    }

    @Override
    public GumpApp getUnique(String appCode) {
        return data.get(appCode);
    }
}
