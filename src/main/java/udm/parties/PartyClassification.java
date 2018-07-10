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
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import udm.VolatileEntity;

/**
 *
 * @author skrymets
 * @param <T> a specific type of a party
 */
@MappedSuperclass
public abstract class PartyClassification<T extends Party> extends VolatileEntity {

    private static final long serialVersionUID = 1447936822300888937L;

    @OneToOne
    private T party;

    public PartyClassification() {
    }

    public T getParty() {
        return party;
    }

    public void setParty(T party) {
        this.party = party;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.party);
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
        final PartyClassification<?> other = (PartyClassification<?>) obj;
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        return true;
    }

}
