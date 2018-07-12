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
package udm.parties.classifier;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import udm.MutablePersistentEntity;
import udm.parties.Party;

/**
 *
 * @author skrymets
 * @param <T> a specific type of a party
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PartyClassification extends MutablePersistentEntity {

    private static final long serialVersionUID = 1447936822300888937L;

    @OneToOne
    private Party party;

    @OneToOne
    private PartyType partyType;
    @Version
    protected Long version;

    public PartyClassification() {
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public PartyType getPartyType() {
        return partyType;
    }

    public void setPartyType(PartyType partyType) {
        this.partyType = partyType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.party);
        hash = 29 * hash + Objects.hashCode(this.partyType);
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
        final PartyClassification other = (PartyClassification) obj;
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        if (!Objects.equals(this.partyType, other.partyType)) {
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
