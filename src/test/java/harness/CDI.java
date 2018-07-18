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
package harness;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author skrymets
 */
public final class CDI {

    private CDI() {
    }

    public static final class PersistenceContext {

        private static final Logger LOG = LoggerFactory.getLogger(PersistenceContext.class);

        private static final String UDM_TEST_PU = "udm_test_PU";
        private static final EntityManagerFactory EMF;
        private static final ThreadLocal<EntityManager> TL_EM;

        static {
            EMF = Persistence.createEntityManagerFactory(UDM_TEST_PU);
            TL_EM = new ThreadLocal<EntityManager>() {
                @Override
                protected EntityManager initialValue() {
                    return EMF.createEntityManager();
                }
            };
        }

        private PersistenceContext() {
        }

        public static void createEntityManager() {

            LOG.debug("[{}] Creating new Entity Manager ...", Thread.currentThread().getName());

            closeEntityManager();
            EntityManager em = EMF.createEntityManager();
            TL_EM.set(em);
        }

        public static void closeEntityManager() {
            LOG.debug("[{}] Closing an existing Entity Manager ...", Thread.currentThread().getName());

            EntityManager em = TL_EM.get();
            if (em != null) {
                em.close();
                TL_EM.set(null);
            }
        }

        public static void closeEntityManagerFactory() {
            LOG.debug("[{}] Closing Entity Manager Factory ...", Thread.currentThread().getName());

            EMF.close();
        }

        public static EntityManager getEntityManager() {
            EntityManager em = TL_EM.get();
            LOG.debug("[{}] Query for Entity Manager {} ", Thread.currentThread().getName(), em.hashCode());
            return em;
        }

        public static void beginTransaction() {
            LOG.debug("[{}] Starting a new transaction ...", Thread.currentThread().getName());
            getEntityManager().getTransaction().begin();
        }

        public static void rollback() {
            LOG.debug("[{}] Rolling back a transaction ...", Thread.currentThread().getName());
            getEntityManager().getTransaction().rollback();
        }

        public static void commit() {
            LOG.debug("[{}] Committing a transaction ...", Thread.currentThread().getName());
            getEntityManager().getTransaction().commit();
        }
    }

}
