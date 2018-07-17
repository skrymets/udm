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
package udm.classifiers.purpose;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.communication.CommunicationEventPurpose;

/**
 *
 * @author skrymets
 */
public class CommunicationEventPurposeType extends AbstractPurposeType {

    private static final long serialVersionUID = -7259560991245811987L;

    @OneToMany(
            mappedBy = "eventPurposeType",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<CommunicationEventPurpose> eventPurposes = new ArrayList<>();

    public CommunicationEventPurposeType() {
    }

    public boolean addCommunicationEventPurpose(CommunicationEventPurpose cep) {
        cep.setEventPurposeType(this);
        return eventPurposes.add(cep);
    }

    public boolean removeCommunicationEventPurpose(CommunicationEventPurpose cep) {
        return eventPurposes.remove(cep);
    }

}
