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
package udm.products.id;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import udm.classifiers.Classifier;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductIdentifierType<T extends ProductIdentifier> extends Classifier {

    private static final long serialVersionUID = 3013854711843974165L;

    @OneToMany(
            mappedBy = "productIdentifierType",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<ProductIdentifier> productIdentifiers = new ArrayList<>();

    public ProductIdentifierType() {
    }

    public boolean addProductIdentifier(T pi) {
        pi.setProductIdentifierType(this);
        return productIdentifiers.add(pi);
    }

    public boolean removeProductIdentifier(T pi) {
        return productIdentifiers.remove(pi);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
