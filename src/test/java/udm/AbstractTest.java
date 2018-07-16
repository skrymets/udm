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
package udm;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import udm.dao.CDI.PersistenceContext;

/**
 *
 * @author skrymets
 */
public abstract class AbstractTest {

    protected JPAQueryFactory jpaQueryFactory;

    public AbstractTest() {
    }

    @Before
    public void setUpJPA() {
        PersistenceContext.createEntityManager();
        EntityManager em = PersistenceContext.getEntityManager();
        jpaQueryFactory = new JPAQueryFactory(() -> {
            return em;
        });
        PersistenceContext.beginTransaction();
    }

    @After
    public void tearDownJPA() {        
        PersistenceContext.rollback();
        PersistenceContext.getEntityManager().close();
    }

    public final EntityManager getEntityManager() {
        return PersistenceContext.getEntityManager();
    }

}
