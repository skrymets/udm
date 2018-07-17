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

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import udm.PersistentEntity;
import udm.classes.purpose.CommunicationEventPurposeType;

/**
 *
 * @author skrymets
 */
public class CommunicationEventPurpose extends PersistentEntity {

    private static final long serialVersionUID = -846785284561442553L;

    @Column(nullable = true)
    private String description;

    @ManyToOne(optional = false)
    private CommunicationEventPurposeType eventPurposeType;

    public CommunicationEventPurpose() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommunicationEventPurposeType getEventPurposeType() {
        return eventPurposeType;
    }

    public void setEventPurposeType(CommunicationEventPurposeType eventPurposeType) {
        this.eventPurposeType = eventPurposeType;
    }

}
