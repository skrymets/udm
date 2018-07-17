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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.facilities.Facility;

/**
 *
 * @author skrymets
 */
@Entity
public class FacilityType extends Classifier {

    private static final long serialVersionUID = -5603488086413409198L;

    @OneToMany(
            mappedBy = "facilityType",
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<Facility> facilities = new ArrayList<>();

    public FacilityType() {
    }

    public boolean addFacility(Facility facility) {
        facility.setFacilityType(this);
        return facilities.add(facility);
    }

    public boolean removeFacility(Facility facility) {
        return facilities.remove(facility);
    }

}
