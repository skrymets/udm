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
import udm.classes.roles.PartyRoleType;
import udm.dao.PartyRoleDAO;
import udm.dao.PartyRoleTypeDAO;
import udm.dao.PersonDAO;
import udm.domain.roles.StudentRole;
import udm.parties.roles.PartyRole;
import udm.parties.roles.dsl.QPartyRole;

/**
 *
 * @author skrymets
 */
public class PartyRoleTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PartyRoleTest.class);

    private final PersonDAO personDAO = new PersonDAO();
    private final PartyRoleDAO partyRoleDAO = new PartyRoleDAO();
    private final PartyRoleTypeDAO partyRoleTypeDAO = new PartyRoleTypeDAO();

    @Test
    public void partyRoleTest() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Person.Gender.M);
        personDAO.create(person);

        PartyRoleType studentRoleType = new PartyRoleType();
        studentRoleType.setDescription("student");
        partyRoleTypeDAO.create(studentRoleType);

        StudentRole studentRole = new StudentRole();
        studentRole.setParty(person);
        studentRole.setRoleType(studentRoleType);
        partyRoleDAO.create(studentRole);

        person.addPartyRole(studentRole);
        personDAO.update(person);
        studentRoleType.addPartyRole(studentRole);
        partyRoleTypeDAO.update(studentRoleType);

        QPartyRole qpr = QPartyRole.partyRole;
        List<PartyRole> partyRoles = jpaQueryFactory
                .selectFrom(qpr)
                .where(qpr.party.eq(person).and(qpr.roleType.eq(studentRoleType)))
                .fetch();

        assertNotNull(partyRoles);
        assertFalse(partyRoles.isEmpty());
        assertTrue(partyRoles.size() == 1);

    }

}
