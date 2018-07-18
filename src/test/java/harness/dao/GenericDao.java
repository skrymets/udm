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
package harness.dao;

import harness.CDI.PersistenceContext;
import javax.persistence.EntityManager;
import udm.PersistentEntity;

/**
 *
 * @author skrymets
 * @param <T>
 */
public interface GenericDao<T extends PersistentEntity> {

    public default EntityManager entityManager() {
        return PersistenceContext.getEntityManager();
    }

    public Class<T> getEntityClass();

    public default T create(T t) {
        entityManager().persist(t);
        return t;
    }

    public default T read(Long id) {
        return entityManager().find(getEntityClass(), id);
    }

    public default T update(T t) {
        return entityManager().merge(t);
    }

    public default void delete(T t) {
        t = entityManager().merge(t);
        entityManager().remove(t);
    }

    /**
     * public <T> T find(Class<T> type, Serializable id);
     *
     * public <T> T[] find(Class<T> type, Serializable... ids);
     *
     * public <T> T getReference(Class<T> type, Serializable id);
     *
     * public <T> T[] getReferences(Class<T> type, Serializable... ids);
     *
     * public boolean save(Object entity);
     *
     * public boolean[] save(Object... entities);
     *
     * public boolean remove(Object entity);
     *
     * public void remove(Object... entities);
     *
     * public boolean removeById(Class<?> type, Serializable id);
     *
     * public void removeByIds(Class<?> type, Serializable... ids);
     *
     * public <T> List<T> findAll(Class<T> type);
     *
     * public List search(ISearch search);
     *
     * public Object searchUnique(ISearch search);
     *
     * public int count(ISearch search);
     *
     * public SearchResult searchAndCount(ISearch search);
     *
     * public boolean isAttached(Object entity);
     *
     * public void refresh(Object... entities);
     *
     * public void flush();
     */
}
