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
package udm.contacts;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.PersistentEntity;

/**
 *
 * @author skrymets
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ContactMechanism extends PersistentEntity {

    private static final long serialVersionUID = -1001047402929393911L;

    @ManyToOne(optional = false)
    @JoinColumn
    private ContactMechanismType mechanismType;

    public ContactMechanism() {
    }

    public ContactMechanismType getMechanismType() {
        return mechanismType;
    }

    public void setMechanismType(ContactMechanismType mechanismType) {
        this.mechanismType = mechanismType;
    }

}
