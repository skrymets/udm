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
package udm.parties.relationships;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import udm.BusinessEntity;
import udm.classes.PartyRelationshipType;
import udm.communication.CommunicationEvent;
import udm.parties.roles.PartyRole;

/**
 *
 * @author skrymets
 */
@Entity
public class PartyRelationship extends BusinessEntity {

    private static final long serialVersionUID = -5107722598244190677L;

    @ManyToOne(optional = false)
    private PartyRole from;

    @ManyToOne(optional = false)
    private PartyRole to;

    @ManyToOne(optional = false)
    private PartyRelationshipType relationshipType;

    @OneToMany(
            mappedBy = "partyRelationship",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<CommunicationEvent> communicationEvents = new ArrayList<>();

    public PartyRelationship() {
    }

    public PartyRole getFrom() {
        return from;
    }

    public void setFrom(PartyRole from) {
        this.from = from;
    }

    public PartyRole getTo() {
        return to;
    }

    public void setTo(PartyRole to) {
        this.to = to;
    }

    public PartyRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(PartyRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public boolean addCommunicationEvent(CommunicationEvent event) {
        event.setPartyRelationship(this);
        return communicationEvents.add(event);
    }

    public boolean removeCommunicationEvent(CommunicationEvent event) {
        return communicationEvents.remove(event);
    }

}
