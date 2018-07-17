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
package udm.facilities;

import udm.parties.roles.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author skrymets
 */
@Entity
public class FacilityRoleType extends RoleType {

    private static final long serialVersionUID = 5704600205187968577L;

    @OneToMany(
            mappedBy = "facilityRoleType",
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<FacilityRole> facilityRoles = new ArrayList<>();

    public FacilityRoleType() {
    }

    public boolean addFacilityRole(FacilityRole fr) {
        fr.setFacilityRoleType(this);
        return facilityRoles.add(fr);
    }

    public boolean removeFacilityRole(FacilityRole fr) {
        return facilityRoles.remove(fr);
    }

}
