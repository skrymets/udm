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
package udm.parties.roles;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import udm.MutablePersistentEntity;
import udm.parties.Party;

/**
 *
 * @author skrymets
 */
@Entity
public abstract class PartyRole extends MutablePersistentEntity {

    private static final long serialVersionUID = 5704600205187968577L;
    
    @OneToOne
    private Party party;
    
    @OneToOne
    private RoleType roleType;
    @Version
    protected Long version;

    public PartyRole() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
