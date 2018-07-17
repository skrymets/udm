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
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.AbstractTest;
import udm.classes.PartyClassificationType;
import udm.dao.CDI.PersistenceContext;
import udm.dao.PartyClassificationDAO;
import udm.dao.PartyClassificationTypeDAO;
import udm.dao.PersonDAO;
import udm.domain.IncomeClassification;
import udm.parties.dsl.QPartyClassification;
import udm.parties.dsl.QPerson;

/**
 *
 * @author skrymets
 */
public class PartyClassificationTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PartyClassificationTest.class);

    private final PersonDAO personDAO = new PersonDAO();
    private final PartyClassificationDAO classificationDAO = new PartyClassificationDAO();
    private final PartyClassificationTypeDAO typeDAO = new PartyClassificationTypeDAO();

    @Test
    public void partyClassificationTest() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Person.Gender.M);

        PartyClassificationType partyType = new PartyClassificationType();
        partyType.setDescription("Student");

        personDAO.create(person);
        typeDAO.create(partyType);

        PartyClassification classification = new IncomeClassification();
        classification.validFrom(LocalDateTime.now().minusDays(1));
        partyType.addPartyClassification(classification);
        person.addPartyClassification(classification);
        // classification.setParty(person);
        // classification.setClassificationType(partyType);
        classificationDAO.create(classification);

        personDAO.update(person);
        typeDAO.update(partyType);

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
        Person hqlReturnedPerson = (Person) PersistenceContext.getEntityManager()
                .createQuery("SELECT p from Person p WHERE p.id = :person_id")
                .setParameter("person_id", person.getId())
                .getSingleResult();

        assertNotNull(hqlReturnedPerson);
        assertEquals(classification.getParty(), hqlReturnedPerson);
    }

}
