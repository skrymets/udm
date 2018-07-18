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
package udm.products;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.JOINED;
import javax.persistence.OneToMany;
import udm.MutablePersistentEntity;

/**
 *
 * @author skrymets
 */
@Entity
@Inheritance(strategy = JOINED)
public abstract class Product extends MutablePersistentEntity {

    private static final long serialVersionUID = -5429124106731393559L;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String productKey;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<ProductCategoryClassification> categoryClassifications = new ArrayList<>();

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<ProductLifecycle> lifecycles = new ArrayList<>();

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String key) {
        this.productKey = key;
    }

    public boolean addCategoryClassification(ProductCategoryClassification pcc) {
        pcc.setProduct(this);
        return categoryClassifications.add(pcc);
    }

    public boolean removeCategoryClassification(ProductCategoryClassification pcc) {
        return categoryClassifications.remove(pcc);
    }

    public boolean addProductLifecycle(ProductLifecycle pl) {
        pl.setProduct(this);
        return lifecycles.add(pl);
    }

    public boolean removeProductLifecycle(ProductLifecycle pl) {
        return lifecycles.remove(pl);
    }

}
