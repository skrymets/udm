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
package udm.parties;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.AbstractTest;
import udm.dao.CDI;
import udm.dao.CDI.PersistenceContext;
import udm.domain.IncomeClassification;
import udm.parties.classifier.PartyClassification;
import udm.parties.classifier.PartyClassificationType;
import udm.parties.classifier.QPartyClassification;

/**
 *
 * @author skrymets
 */
public class PartyClassificationTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PartyClassificationTest.class);

    @Test
    public void partyClassificationTest() {
        final EntityManager em = getEntityManager();
        // em.getTransaction().begin();
        // PersistenceContext.beginTransaction();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Person.Gender.M);

        PartyClassificationType partyType = new PartyClassificationType();
        partyType.setDescription("Student");

        PartyClassification classification = new IncomeClassification();
        classification.setParty(person);
        classification.setPartyType(partyType);
        classification.validFrom(LocalDateTime.now().minusDays(1));

        em.persist(person);
        em.persist(partyType);
        em.persist(classification);

        //em.getTransaction().commit();
        // PersistenceContext.commit();

        // -------------------------------------------------------------------------------
        QPartyClassification qpc = QPartyClassification.partyClassification;

        List<PartyClassification> persons = jpaQueryFactory.selectFrom(qpc)
                .where(qpc.validNow())
                .fetch();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

        // -------------------------------------------------------------------------------        
        QPerson dslPerson = QPerson.person;
        Person dslReturnedPerson = jpaQueryFactory.selectFrom(dslPerson)
                .where(dslPerson.id.eq(person.getId()))
                .fetchOne();

        assertEquals(classification.getParty(), dslReturnedPerson);

        // -------------------------------------------------------------------------------
        Person hqlReturnedPerson = (Person) em.createQuery("SELECT p from Person p WHERE p.id = :person_id")
                .setParameter("person_id", person.getId())
                .getSingleResult();

        assertNotNull(hqlReturnedPerson);
        assertEquals(classification.getParty(), hqlReturnedPerson);
    }

}
