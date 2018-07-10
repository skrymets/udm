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

import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.AbstractTest;

/**
 *
 * @author skrymets
 */
public class PartyTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(PartyTest.class);

    @Test
    public void persistPartyTest() {
        Party party = new Party();
        getEntityManager().persist(party);

        assertNotNull(party.getId());
        LOG.info("A new party saved with ID: {}", party.getId());
    }

}
