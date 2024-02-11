package org.junhui.gump.common.entity;

import com.google.common.base.Objects;

import java.io.Serializable;

public class MetadataId implements Serializable {
    private static final long serialVersionUID = 4264910201590498548L;

    private Long id;

    private MetadataId() {}

    public MetadataId(Long id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("MetadataId id不能为空");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetadataId)) return false;
        MetadataId that = (MetadataId) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
