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

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import udm.MutablePersistentEntity;
import udm.parties.classifier.PartyClassification;
import udm.parties.roles.PartyRole;

/**
 *
 * @author skrymets
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party extends MutablePersistentEntity {

    private static final long serialVersionUID = -113357481482772294L;

    @OneToMany(
            mappedBy = "party",
            cascade = ALL, orphanRemoval = true
    )
    private List<PartyClassification> partyClassification = new ArrayList<>();

    @OneToMany(
            mappedBy = "party",
            cascade = ALL, orphanRemoval = true
    )
    private List<PartyRole> partyRoles = new ArrayList<>();

    public boolean addPartyClassification(PartyClassification pc) {
        pc.setParty(this);
        return partyClassification.add(pc);
    }

    public boolean removePartyClassification(PartyClassification pc) {
        return partyClassification.remove(pc);
    }

    public boolean addPartyRole(PartyRole pr) {
        pr.setParty(this);
        return partyRoles.add(pr);
    }

    public boolean removePartyRole(PartyRole pr) {
        return partyRoles.remove(pr);
    }

}
