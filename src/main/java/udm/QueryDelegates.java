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

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.time.LocalDateTime;
import udm.dsl.QBusinessEntity;

/**
 *
 * @author skrymets
 */
public class QueryDelegates {

    @QueryDelegate(BusinessEntity.class)
    public static BooleanExpression validNow(final QBusinessEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        return validAt(entity, now);
    }

    @QueryDelegate(BusinessEntity.class)
    public static BooleanExpression validAt(final QBusinessEntity entity, LocalDateTime instant) {
        return entity.validFrom.before(instant).or(entity.validFrom.eq(instant))
                .and(entity.validThru.after(instant).or(entity.validThru.eq(instant)));

    }
}
