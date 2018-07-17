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

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import udm.PersistentEntity;
import udm.classifiers.GeorgraphicBoundaryType;

/**
 *
 * @author skrymets
 */
public class GeorgraphicBoundary extends PersistentEntity {

    private static final long serialVersionUID = 9183765246659609693L;

    @Column(nullable = true)
    private String geoCode;

    @Column(nullable = true)
    private String name;

    @ManyToOne
    @Column(nullable = false)
    private GeorgraphicBoundaryType boundaryType;

    public String getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeorgraphicBoundaryType getBoundaryType() {
        return boundaryType;
    }

    public void setBoundaryType(GeorgraphicBoundaryType boundaryType) {
        this.boundaryType = boundaryType;
    }

}
