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
package udm.geo;

import javax.persistence.ManyToOne;
import udm.PersistentEntity;

/**
 *
 * @author skrymets
 */
public class GeorgraphicBoundaryAssociation extends PersistentEntity {

    private static final long serialVersionUID = -8257884445484358719L;

    /**
     * inside the boundaries or limits
     * the part that is included
     */
    @ManyToOne(optional = false)
    private GeorgraphicBoundary within;

    /**
     * denotes inclusion or location
     * the part that is composed of
     */
    @ManyToOne(optional = false)
    private GeorgraphicBoundary in;

    public GeorgraphicBoundaryAssociation() {
    }

    public GeorgraphicBoundary getWithin() {
        return within;
    }

    public void setWithin(GeorgraphicBoundary within) {
        this.within = within;
    }

    public GeorgraphicBoundary getIn() {
        return in;
    }

    public void setIn(GeorgraphicBoundary in) {
        this.in = in;
    }

}
