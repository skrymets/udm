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
package udm.classes.roles;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.communication.CommunicationEventRole;

/**
 *
 * @author skrymets
 */
@Entity
public class CommunicationEventRoleType extends AbstractRoleType {

    private static final long serialVersionUID = -5415684740514785615L;

    @OneToMany(
            mappedBy = "eventRoleType",
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<CommunicationEventRole> communicationEventRoles = new ArrayList<>();

    public CommunicationEventRoleType() {
    }

    public void addCommunicationEventRole(CommunicationEventRole cer) {
        cer.setEventRoleType(this);
        communicationEventRoles.add(cer);
    }

    public boolean remove(CommunicationEventRole cer) {
        return communicationEventRoles.remove(cer);
    }

}
