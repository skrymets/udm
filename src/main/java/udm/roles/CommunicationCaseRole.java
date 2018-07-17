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
package udm.roles;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.BusinessEntity;
import udm.classifiers.roles.CommunicationCaseRoleType;
import udm.communication.CommunicationCase;
import udm.parties.Party;

/**
 *
 * @author skrymets
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CommunicationCaseRole extends BusinessEntity {

    private static final long serialVersionUID = 5704600205187968577L;

    @ManyToOne(optional = false)
    @JoinColumn
    private Party party;

    @ManyToOne(optional = false)
    @JoinColumn
    private CommunicationCase communicationCase;

    @ManyToOne(optional = false)
    @JoinColumn
    private CommunicationCaseRoleType communicationCaseRoleType;

    public CommunicationCaseRole() {
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public CommunicationCase getCommunicationCase() {
        return communicationCase;
    }

    public void setCommunicationCase(CommunicationCase cc) {
        this.communicationCase = cc;
    }

    public CommunicationCaseRoleType getCommunicationCaseRoleType() {
        return communicationCaseRoleType;
    }

    public void setCommunicationCaseRoleType(CommunicationCaseRoleType ccrt) {
        this.communicationCaseRoleType = ccrt;
    }

}
