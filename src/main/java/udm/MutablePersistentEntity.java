/*
 * Copyright 2018 skrymets.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package udm;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author skrymets
 */
@MappedSuperclass
public abstract class MutablePersistentEntity extends PersistentEntity {

    private static final long serialVersionUID = 8814997773907565299L;

    private LocalDateTime validFrom;

    private LocalDateTime validThru;
    @Version
    protected Long version;

    public MutablePersistentEntity() {
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidThru() {
        return validThru;
    }

    public void setValidThru(LocalDateTime validThru) {
        this.validThru = validThru;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.validFrom);
        hash = 79 * hash + Objects.hashCode(this.validThru);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MutablePersistentEntity other = (MutablePersistentEntity) obj;
        if (!Objects.equals(this.validFrom, other.validFrom)) {
            return false;
        }
        if (!Objects.equals(this.validThru, other.validThru)) {
            return false;
        }
        return true;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
