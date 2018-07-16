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
package udm.validity;

import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.domain.roles.org.DepartmentRole;

/**
 *
 * @author skrymets
 */
public class ValidityTest {

    private static final Logger LOG = LoggerFactory.getLogger(ValidityTest.class);

    public ValidityTest() {
    }

    @Test
    public void testValidByDefault() {

        DepartmentRole role = new DepartmentRole();
        assertTrue(role.isValidNow());

    }

    @Test
    public void testValidFromAMomentAgo() {

        DepartmentRole role = new DepartmentRole();
        role.validFromNow();

        assertTrue(role.isValidNow());

    }

    @Test
    public void testNotValidAnymore() throws InterruptedException {

        DepartmentRole role = new DepartmentRole();
        assertTrue(role.isValidNow());

        role.validThruNow();

        // Workaround: 
        // It seems that (reardless of the documentation) nanoseconds aren't donsidered 
        // in comparation of the dates. But the CPU cpu is too fast, to spend at least 
        // millisecond between calls. So we should help him.
        Thread.sleep(1);
        assertFalse(role.isValidNow());

    }

    @Test
    public void testCantBeInvalidAndValidAtTheSameTime() {

        DepartmentRole role = new DepartmentRole();
        assertTrue(role.isValidNow());

        LocalDateTime aDayBefore = LocalDateTime.now().minusDays(1);
        LocalDateTime inOneDay = LocalDateTime.now().plusDays(1);

        role.validThru(aDayBefore);
        role.validFrom(inOneDay);
        assertFalse(role.isValidNow());

    }

}
