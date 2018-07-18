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
package udm.classifiers.status;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.communication.CommunicationCase;

@Entity
public class CommunicationCaseStatusType extends AbstractStatusType {

    private static final long serialVersionUID = 6131305133780722324L;

    @OneToMany(
            mappedBy = "communicationCaseStatusType",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<CommunicationCase> communicationCases = new ArrayList<>();

    public CommunicationCaseStatusType() {
    }

    public boolean addCommunicationCase(CommunicationCase cc) {
        cc.setCommunicationCaseStatusType(this);
        return communicationCases.add(cc);
    }

    public boolean removeCommunicationCase(CommunicationCase cc) {
        return communicationCases.remove(cc);
    }
}