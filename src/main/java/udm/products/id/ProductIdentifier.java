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

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import udm.MutablePersistentEntity;
import udm.products.Product;

/**
 *
 * @author skrymets
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductIdentifier<T extends ProductIdentifierType> extends MutablePersistentEntity {

    private static final long serialVersionUID = 1223919081436750147L;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @Column(nullable = false, unique = true)
    private String identifierData;

    @ManyToOne(optional = false)
    @JoinColumn
    private ProductIdentifierType productIdentifierType;

    public ProductIdentifier() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getIdentifierData() {
        return identifierData;
    }

    public void setIdentifierData(String identifierData) {
        this.identifierData = identifierData;
    }

    public T getProductIdentifierType() {
        return (T) productIdentifierType;
    }

    public void setProductIdentifierType(T productIdentifierType) {
        this.productIdentifierType = productIdentifierType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.identifierData);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductIdentifier other = (ProductIdentifier) obj;
        if (!Objects.equals(this.identifierData, other.identifierData)) {
            return false;
        }
        return true;
    }

}
