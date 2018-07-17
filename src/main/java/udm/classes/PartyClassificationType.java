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
package udm.classes;

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import udm.parties.PartyClassification;

/**
 *
 * @author skrymets
 */
@Entity
public class PartyClassificationType extends Classifier {

    private static final long serialVersionUID = -1735451119505129396L;

    @OneToMany(
            mappedBy = "classificationType",
            cascade = ALL, orphanRemoval = true
    )
    private List<PartyClassification> partyClassifications = new ArrayList<>();

    public PartyClassificationType() {
    }

    public boolean addPartyClassification(PartyClassification pc) {
        pc.setClassificationType(this);
        return partyClassifications.add(pc);
    }

    public boolean removePartyClassification(PartyClassification pc) {
        return partyClassifications.remove(pc);
    }

}
