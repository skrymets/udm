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
package udm.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import udm.MutablePersistentEntity;

/**
 *
 * @author skrymets
 */
@Entity
public class WorkEffort extends MutablePersistentEntity {

    private static final long serialVersionUID = 3047320865503775280L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    public WorkEffort() {
    }

}
