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
import javax.persistence.OneToMany;
import udm.classifiers.Classifier;

/**
 *
 * @author skrymets
 */
@Entity
public class ProductFeatureCategory extends Classifier {

    private static final long serialVersionUID = -8799739623728383894L;

    @OneToMany(
            mappedBy = "productFeatureCategory",
            orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<ProductFeature> productFeatures = new ArrayList<>();

    public ProductFeatureCategory() {
    }

    public boolean addProductFeature(ProductFeature pf) {
        pf.setProductFeatureCategory(this);
        return productFeatures.add(pf);
    }

    public boolean removeProductFeature(ProductFeature pf) {
        return productFeatures.remove(pf);
    }

}
