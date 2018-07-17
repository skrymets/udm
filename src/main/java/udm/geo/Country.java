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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

/**
 *
 * @author skrymets
 */
public class Country extends GeorgraphicBoundary {

    private static final long serialVersionUID = 7229605227240873050L;

    @OneToMany(mappedBy = "country")
    private List<PostalCode> postalCodes = new ArrayList<>();
    @OneToMany(mappedBy = "country")
    private List<Province> provinces = new ArrayList<>();
    @OneToMany(mappedBy = "country")
    private List<Territory> territories = new ArrayList<>();
    @OneToMany(mappedBy = "country")
    private List<Region> regions = new ArrayList<>();

    public Country() {
    }

}
