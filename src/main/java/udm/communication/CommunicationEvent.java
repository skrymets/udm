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
package udm.communication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import udm.PersistentEntity;
import udm.parties.PartyRelationship;
import udm.roles.CommunicationEventRole;

/**
 *
 * @author skrymets
 */
@Entity
public class CommunicationEvent extends PersistentEntity {

    private static final long serialVersionUID = 6058270614761003580L;

    @Column(nullable = false)
    private LocalDateTime started;

    @Column(nullable = true)
    private LocalDateTime ended;

    @OneToMany(
            mappedBy = "communicationEvent",
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<CommunicationEventRole> communicationEventRoles = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn
    private PartyRelationship partyRelationship;

    public CommunicationEvent() {
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    public void setEnded(LocalDateTime ended) {
        this.ended = ended;
    }

    public PartyRelationship getPartyRelationship() {
        return partyRelationship;
    }

    public void setPartyRelationship(PartyRelationship partyRelationship) {
        this.partyRelationship = partyRelationship;
    }

    public boolean addCommunicationEventRole(CommunicationEventRole cer) {
        cer.setCommunicationEvent(this);
        return communicationEventRoles.add(cer);
    }

    public boolean removeCommunicationEventRole(CommunicationEventRole cer) {
        return communicationEventRoles.remove(cer);
    }

}
