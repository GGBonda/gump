package org.junhui.gump.persistence.client.api;

import org.junhui.gump.persistence.client.model.GumpApp;

public interface GumpAppPersistenceService {

    void save(GumpApp app);

    GumpApp getUnique(String appCode);
}
