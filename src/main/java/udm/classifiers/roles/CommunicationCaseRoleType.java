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
package udm.classifiers.roles;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.roles.CommunicationCaseRole;

/**
 *
 * @author skrymets
 */
@Entity
public class CommunicationCaseRoleType extends AbstractRoleType {

    private static final long serialVersionUID = 5254659432600049771L;

    @OneToMany(
            mappedBy = "communicationCaseRoleType",
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<CommunicationCaseRole> communicationCaseRoles = new ArrayList<>();

    public CommunicationCaseRoleType() {
    }

    public boolean addCaseRole(CommunicationCaseRole cr) {
        cr.setCommunicationCaseRoleType(this);
        return communicationCaseRoles.add(cr);
    }

    public boolean removeCaseRole(CommunicationCaseRole cr) {
        return communicationCaseRoles.remove(cr);
    }

}
