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

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.AbstractTest;
import udm.classifiers.PartyRelationshipType;
import udm.classifiers.roles.PartyRoleType;
import harness.dao.PartyRelationshipDAO;
import harness.dao.PartyRelationshipTypeDAO;
import harness.dao.PartyRoleDAO;
import harness.dao.PartyRoleTypeDAO;
import harness.dao.PersonDAO;
import udm.domain.rlshp.TeachingInstructorRelationship;
import udm.domain.roles.InstructorRole;
import udm.domain.roles.StudentRole;
import udm.parties.dsl.QPartyRelationship;

/**
 *
 * @author skrymets
 */
public class PartyRelationshipTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PartyRelationshipTest.class);

    private final PersonDAO personDAO = new PersonDAO();
    private final PartyRoleDAO partyRoleDAO = new PartyRoleDAO();
    private final PartyRoleTypeDAO partyRoleTypeDAO = new PartyRoleTypeDAO();
    private final PartyRelationshipDAO relationshipDAO = new PartyRelationshipDAO();
    private final PartyRelationshipTypeDAO relationshipTypeDAO = new PartyRelationshipTypeDAO();

    @Test
    public void testPartyRelationshipPersist() {

        Person personJD = new Person();
        personJD.setFirstName("John");
        personJD.setLastName("Doe");
        personJD.setGender(Person.Gender.M);

        Person personMW = new Person();
        personMW.setFirstName("Max");
        personMW.setLastName("Wax");
        personMW.setGender(Person.Gender.M);

        personDAO.create(personJD);
        personDAO.create(personMW);

        // -------------------------------------------------------------------------------
        PartyRoleType studentRoleType = new PartyRoleType();
        studentRoleType.setDescription("student");

        PartyRoleType instructorRoleType = new PartyRoleType();
        instructorRoleType.setDescription("instructor");

        partyRoleTypeDAO.create(instructorRoleType);
        partyRoleTypeDAO.create(studentRoleType);

        // -------------------------------------------------------------------------------
        StudentRole studentRole = new StudentRole();
        studentRole.setParty(personJD);
        studentRole.setRoleType(studentRoleType);

        InstructorRole instructorRole = new InstructorRole();
        instructorRole.setParty(personMW);
        instructorRole.setRoleType(instructorRoleType);

        partyRoleDAO.create(studentRole);
        partyRoleDAO.create(instructorRole);

        // -------------------------------------------------------------------------------
        personJD.addPartyRole(studentRole);
        studentRoleType.addPartyRole(studentRole);
        personDAO.update(personJD);
        partyRoleTypeDAO.update(studentRoleType);

        personMW.addPartyRole(instructorRole);
        studentRoleType.addPartyRole(instructorRole);
        personDAO.update(personMW);
        partyRoleTypeDAO.update(instructorRoleType);

        // -------------------------------------------------------------------------------
        PartyRelationshipType mentoringRelationshipType = new PartyRelationshipType();
        mentoringRelationshipType.setDescription("mentor");
        relationshipTypeDAO.create(mentoringRelationshipType);

        TeachingInstructorRelationship tir = new TeachingInstructorRelationship();
        tir.setFrom(instructorRole);
        tir.setTo(studentRole);
        tir.validFromNow();
        tir.setRelationshipType(mentoringRelationshipType);
        relationshipDAO.create(tir);

        mentoringRelationshipType.addPartyRelationship(tir);
        relationshipTypeDAO.update(mentoringRelationshipType);

        // -------------------------------------------------------------------------------
        QPartyRelationship qprs = QPartyRelationship.partyRelationship;

        List<PartyRelationship> relationships = jpaQueryFactory
                .selectFrom(qprs)
                .where(
                        qprs.from.party.eq(personMW).and(qprs.relationshipType.eq(mentoringRelationshipType))
                ).fetch();

        assertNotNull(relationships);
        assertFalse(relationships.isEmpty());
        assertTrue(relationships.size() == 1);

    }

}
