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
package udm.parties;

import java.util.Objects;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.BusinessEntity;
import udm.classes.PartyClassificationType;

/**
 *
 * @author skrymets
 * @param <T> a specific type of a party
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PartyClassification extends BusinessEntity {

    private static final long serialVersionUID = 1447936822300888937L;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn
    private Party party;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn
    private PartyClassificationType classificationType;

    public PartyClassification() {
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public PartyClassificationType getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(PartyClassificationType classificationType) {
        this.classificationType = classificationType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.party);
        hash = 29 * hash + Objects.hashCode(this.classificationType);
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
        if (!Objects.equals(this.classificationType, other.classificationType)) {
            return false;
        }
        return true;
    }

}
