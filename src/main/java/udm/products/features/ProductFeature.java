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
package udm.products.features;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import udm.MutablePersistentEntity;

/**
 *
 * @author skrymets
 */
@Entity
public class ProductFeature extends MutablePersistentEntity {

    private static final long serialVersionUID = -985801473514421555L;

    @OneToMany(
            mappedBy = "productFeature",
            orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<ProductFeatureAvailability> productFeatureAvailabilities = new ArrayList<>();

    @ManyToOne
    private ProductFeatureCategory productFeatureCategory;

    public ProductFeature() {
    }

    public boolean addProductFeatureAvailability(ProductFeatureAvailability pfa) {
        pfa.setProductFeature(this);
        return productFeatureAvailabilities.add(pfa);
    }

    public boolean removeProductFeatureAvailability(ProductFeatureAvailability pfa) {
        return productFeatureAvailabilities.remove(pfa);
    }

    public ProductFeatureCategory getProductFeatureCategory() {
        return productFeatureCategory;
    }

    public void setProductFeatureCategory(ProductFeatureCategory category) {
        this.productFeatureCategory = category;
    }

}
