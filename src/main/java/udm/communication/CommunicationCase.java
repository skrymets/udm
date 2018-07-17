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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.MutablePersistentEntity;
import udm.classifiers.status.CommunicationCaseStatusType;

/**
 *
 * @author skrymets
 */
@Entity
public class CommunicationCase extends MutablePersistentEntity {

    private static final long serialVersionUID = 2628553547600101906L;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime created;

    @ManyToOne(optional = false)
    @JoinColumn
    private CommunicationCaseStatusType communicationCaseStatusType;

    public CommunicationCase() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public CommunicationCaseStatusType getCommunicationCaseStatusType() {
        return communicationCaseStatusType;
    }

    public void setCommunicationCaseStatusType(CommunicationCaseStatusType ccst) {
        this.communicationCaseStatusType = ccst;
    }

}
