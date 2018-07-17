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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import udm.PersistentEntity;
import udm.classes.FacilityType;

/**
 *
 * @author skrymets
 */
@Entity
public class Facility extends PersistentEntity {

    private static final long serialVersionUID = -4956438267804944383L;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn
    private FacilityType facilityType;

    @OneToMany(
            mappedBy = "facility",
            cascade = CascadeType.ALL, orphanRemoval = true
    )
    private List<FacilityContactMechanism> facilityContactMechanisms = new ArrayList<>();

    public Facility() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    public boolean addFacilityContactMechanism(FacilityContactMechanism contactMechanism) {
        contactMechanism.setFacility(this);
        return facilityContactMechanisms.add(contactMechanism);
    }

    public boolean removeFacilityContactMechanism(FacilityContactMechanism contactMechanism) {
        return facilityContactMechanisms.remove(contactMechanism);
    }

}
