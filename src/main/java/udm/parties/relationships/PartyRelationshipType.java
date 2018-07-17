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
package udm.parties.relationships;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.Classifier;

/**
 *
 * @author skrymets
 */
@Entity
public class PartyRelationshipType extends Classifier {

    private static final long serialVersionUID = -1451526790422664271L;

    @OneToMany(
            mappedBy = "relationshipType",
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<PartyRelationship> relationships = new ArrayList<>();

    public PartyRelationshipType() {
    }

    public boolean addPartyRelationship(PartyRelationship pr) {
        pr.setRelationshipType(this);
        return relationships.add(pr);
    }

    public boolean removePartyRelationship(PartyRelationship pr) {
        return relationships.remove(pr);
    }

}
