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

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author skrymets
 */
public class County extends GeorgraphicBoundary {

    private static final long serialVersionUID = 1895218135023943003L;

    @OneToMany(
            mappedBy = "county",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<CountyCity> countiesCities;

    public County() {
    }

    public boolean addCountyCity(CountyCity cc) {
        cc.setCounty(this);
        return countiesCities.add(cc);
    }

    public boolean removeCountyCity(CountyCity cc) {
        return countiesCities.remove(cc);
    }
}
