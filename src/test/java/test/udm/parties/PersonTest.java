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
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.udm.AbstractTest;
import test.udm.domain.IncomeClassification;
import udm.parties.Party;
import udm.parties.Person;
import udm.parties.QPerson;

/**
 *
 * @author skrymets
 */
public class PersonTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PersonTest.class);

    @Test
    @Ignore
    public void dumpDB() {
        try {
            org.hibernate.Session session = getEntityManager().unwrap(org.hibernate.Session.class);
            org.hibernate.engine.spi.SessionFactoryImplementor sfImpl = (org.hibernate.engine.spi.SessionFactoryImplementor) session.getSessionFactory();
            java.sql.Connection jdbcConnection = sfImpl
                    .getJdbcServices()
                    .getBootstrapJdbcConnectionAccess()
                    .obtainConnection();

            try (java.sql.Statement jdbcStatement = jdbcConnection.createStatement()) {
                java.sql.ResultSet rs = jdbcStatement.executeQuery("SCRIPT SIMPLE NODATA TO './DUMP.sql';");
            }

        } catch (java.sql.SQLException e) {
            LOG.error(e.getMessage());
        }

    }

    @Test
    public void persistPartyTest() {
        Party party = new Person();
        getEntityManager().persist(party);

        assertNotNull(party.getId());
        LOG.info("A new party saved with ID: {}", party.getId());
    }

    @Test
    public void persistPersonTest() {
        Person person = new Person();
        getEntityManager().persist(person);

        final Long id = person.getId();
        assertNotNull(id);
        LOG.info("A new person saved with ID: {}", id);

        person.setGender(Person.Gender.M);
        person = getEntityManager().merge(person);

        assertEquals(id, person.getId());
    }

    @Test
    public void partyClassificationTest() {
        final EntityManager em = getEntityManager();

        LOG.info("Saving the entity");
        em.getTransaction().begin();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Person.Gender.M);

        IncomeClassification classification = new IncomeClassification();
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