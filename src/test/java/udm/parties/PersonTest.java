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

import javax.persistence.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.AbstractTest;
import udm.classifiers.PartyClassificationType;
import harness.dao.PartyClassificationDAO;
import harness.dao.PartyClassificationTypeDAO;
import harness.dao.PersonDAO;
import udm.domain.IncomeClassification;
import udm.parties.dsl.QParty;
import udm.parties.dsl.QPerson;

/**
 *
 * @author skrymets
 */
public class PersonTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PersonTest.class);

    private final PersonDAO personDAO = new PersonDAO();
    private final PartyClassificationDAO classificationDAO = new PartyClassificationDAO();
    private final PartyClassificationTypeDAO typeDAO = new PartyClassificationTypeDAO();

    @Test
    public void persistPartyWithDAOTest() {
        Person party = personDAO.create(new Person());
        Long partyId = party.getId();

        final QParty pty = QParty.party;
        Party persistedParty = jpaQueryFactory.selectFrom(pty)
                .where(pty.id.eq(partyId))
                .fetchOne();

        assertNotNull(persistedParty);
        LOG.info("A new party saved with ID: {}", party.getId());

    }

    @Test
    public void persistPersonTest() {
        Person person = personDAO.create(new Person());

        final Long id = person.getId();
        assertNotNull(id);
        LOG.info("A new person saved with ID: {}", id);

        person.setGender(Person.Gender.M);
        person = personDAO.update(person);

        assertEquals(id, person.getId());
    }

    @Test
    public void partyClassificationTest() {
        final EntityManager em = getEntityManager();

        LOG.info("Saving the entity");

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Person.Gender.M);

        personDAO.create(person);

        PartyClassificationType partyType = new PartyClassificationType();
        partyType.setDescription("Student");

        typeDAO.create(partyType);

        PartyClassification classification = new IncomeClassification();
        classification.setParty(person);
        classification.setClassificationType(partyType);
        classification.validFromNow();

        classificationDAO.create(classification);

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

        dumpDB();
    }
}
