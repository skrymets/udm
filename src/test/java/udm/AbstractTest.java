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
import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udm.dao.CDI.PersistenceContext;

/**
 *
 * @author skrymets
 */
public abstract class AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTest.class);

    protected JPAQueryFactory jpaQueryFactory;

    public AbstractTest() {
    }

    protected void dumpDB() {
        dumpDB(null);
    }

    protected void dumpDB(String scriptName) {

        String safeName = (scriptName == null || scriptName.isEmpty())
                ? "./DUMP.sql"
                : scriptName;

        try {
            Connection jdbcConnection = getJDBCConnection();

            try (java.sql.Statement jdbcStatement = jdbcConnection.createStatement()) {
                java.sql.ResultSet rs = jdbcStatement.executeQuery("SCRIPT TO '" + safeName + "';");
            }

        } catch (java.sql.SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    protected Connection getJDBCConnection() throws SQLException {
        // Get a connection directly from c3p0 pool instead of unwrapping HB internals
        // see: hibernate.c3p0.dataSourceName@persistence.xml
        com.mchange.v2.c3p0.PooledDataSource dataSource
                = com.mchange.v2.c3p0.C3P0Registry.pooledDataSourceByName("DefaultTestDataSource");
        return dataSource.getConnection();

//        org.hibernate.Session session = getEntityManager().unwrap(org.hibernate.Session.class);
//
//        org.hibernate.engine.spi.SessionFactoryImplementor sfImpl
//                = (org.hibernate.engine.spi.SessionFactoryImplementor) session.getSessionFactory();
//
//        java.sql.Connection jdbcConnection = sfImpl
//                .getJdbcServices()
//                .getBootstrapJdbcConnectionAccess()
//                .obtainConnection();
//
//        return jdbcConnection;
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

    @Before
    public void setupDatabaseSchema() {

        try {
            Connection existingConnection = getJDBCConnection();
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(existingConnection));

            Liquibase liquibase = new Liquibase("db/db.changelog.xml", new ClassLoaderResourceAccessor(), database);
            //liquibase.dropAll();
            liquibase.update("test");

        } catch (SQLException | DatabaseException e) {
            LOG.error("Failed to setup DB schema due to: {}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        } catch (LiquibaseException e) {
            LOG.error("Failed to setup DB schema due to: {}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @After
    public void tearDownJPA() {
        PersistenceContext.rollback();
        PersistenceContext.getEntityManager().close();
    }

    public static final EntityManager getEntityManager() {
        return PersistenceContext.getEntityManager();
    }

}
