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
package test.udm.parties;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.udm.AbstractTest;
import test.udm.domain.IncomeClassification;
import udm.parties.classifier.PartyClassification;
import udm.parties.Person;
import udm.parties.QPerson;

/**
 *
 * @author skrymets
 */
public class PartyClassificationTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PartyClassificationTest.class);

    //@Test
    public void partyClassificationTest() {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Person.Gender.M);

        PartyClassification classification = new IncomeClassification();
        classification.setParty(person);

        em.persist(person);
        em.persist(classification);

        em.getTransaction().commit();

        // -------------------------------------------------------------------------------
        JPQLQuery query = new JPAQuery(em);

        QPerson dslPerson = QPerson.person;
        Person dslReturnedPerson = query.from(dslPerson)
                .where(dslPerson.id.eq(person.getId()))
                .uniqueResult(dslPerson);

        assertEquals(classification.getParty(), dslReturnedPerson);

        // -------------------------------------------------------------------------------
        Person hqlReturnedPerson = (Person) em.createQuery("SELECT p from Person p WHERE p.id = :person_id")
                .setParameter("person_id", person.getId())
                .getSingleResult();

        assertNotNull(hqlReturnedPerson);
        assertEquals(classification.getParty(), hqlReturnedPerson);
    }

}
