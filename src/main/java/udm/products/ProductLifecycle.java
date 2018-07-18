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

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.MutablePersistentEntity;
import udm.ValidityAware;

/**
 *
 * @author skrymets
 */
@Entity
public class ProductLifecycle extends MutablePersistentEntity {

    private static final long serialVersionUID = 6791662664295132910L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    private LocalDate introducedToMarketDate = ValidityAware.EPOCH_START.toLocalDate();

    private LocalDate salesDiscontinuationDate = ValidityAware.EPOCH_END.toLocalDate();

    private LocalDate supportDiscontinuationDate = ValidityAware.EPOCH_END.toLocalDate();

    public ProductLifecycle() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getIntroducedToMarketDate() {
        return introducedToMarketDate;
    }

    public void setIntroducedToMarketDate(LocalDate introducedToMarketDate) {
        this.introducedToMarketDate = introducedToMarketDate;
    }

    public LocalDate getSalesDiscontinuationDate() {
        return salesDiscontinuationDate;
    }

    public void setSalesDiscontinuationDate(LocalDate salesDiscontinuationDate) {
        this.salesDiscontinuationDate = salesDiscontinuationDate;
    }

    public LocalDate getSupportDiscontinuationDate() {
        return supportDiscontinuationDate;
    }

    public void setSupportDiscontinuationDate(LocalDate supportDiscontinuationDate) {
        this.supportDiscontinuationDate = supportDiscontinuationDate;
    }

}
