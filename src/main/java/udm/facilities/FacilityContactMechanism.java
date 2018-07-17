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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.BusinessEntity;
import udm.contacts.ContactMechanism;

/**
 *
 * @author skrymets
 */
@Entity
public class FacilityContactMechanism extends BusinessEntity {

    private static final long serialVersionUID = -6688342880305802924L;

    @ManyToOne(optional = false)
    @JoinColumn
    private Facility facility;

    @ManyToOne(optional = false)
    @JoinColumn
    private ContactMechanism contactMechanism;

    private String comment;

    public FacilityContactMechanism() {
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public ContactMechanism getContactMechanism() {
        return contactMechanism;
    }

    public void setContactMechanism(ContactMechanism contactMechanism) {
        this.contactMechanism = contactMechanism;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
