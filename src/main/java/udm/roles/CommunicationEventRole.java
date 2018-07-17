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
import udm.classifiers.roles.CommunicationEventRoleType;
import udm.communication.CommunicationEvent;

/**
 *
 * @author skrymets
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CommunicationEventRole extends AbstractRole {

    private static final long serialVersionUID = 5704600205187968577L;

    @ManyToOne(optional = false)
    @JoinColumn
    private CommunicationEvent communicationEvent;

    @ManyToOne(optional = false)
    @JoinColumn
    private CommunicationEventRoleType eventRoleType;

    public CommunicationEventRole() {
    }

    public CommunicationEventRoleType getEventRoleType() {
        return eventRoleType;
    }

    public void setEventRoleType(CommunicationEventRoleType eventRoleType) {
        this.eventRoleType = eventRoleType;
    }

    public CommunicationEvent getCommunicationEvent() {
        return communicationEvent;
    }

    public void setCommunicationEvent(CommunicationEvent communicationEvent) {
        this.communicationEvent = communicationEvent;
    }

}
