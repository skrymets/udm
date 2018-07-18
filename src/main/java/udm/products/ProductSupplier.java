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

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import udm.BusinessEntity;
import udm.Defaults;

/**
 *
 * @author skrymets
 */
@Entity
public class ProductSupplier extends BusinessEntity {

    private static final long serialVersionUID = -8879461339761858911L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private ProductSupplierPartyRole supplierPartyRole;

    @Column(nullable = false)
    private LocalDateTime availableFrom = Defaults.EPOCH_START;

    @Column(nullable = false)
    private LocalDateTime availableThru = Defaults.EPOCH_END;

    public ProductSupplier() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductSupplierPartyRole getSupplierPartyRole() {
        return supplierPartyRole;
    }

    public void setSupplierPartyRole(ProductSupplierPartyRole role) {
        this.supplierPartyRole = role;
    }

    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDateTime getAvailableThru() {
        return availableThru;
    }

    public void setAvailableThru(LocalDateTime availableThru) {
        this.availableThru = availableThru;
    }

}
