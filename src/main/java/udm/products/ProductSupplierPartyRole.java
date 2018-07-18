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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import udm.roles.PartyRole;

@Entity
public class ProductSupplierPartyRole extends PartyRole {

    private static final long serialVersionUID = 3112512889815073035L;

    @OneToMany(
            mappedBy = "supplierPartyRole",
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<ProductSupplier> productSuppliers = new ArrayList<>();

    public ProductSupplierPartyRole() {
    }

    public boolean addProductSupplier(ProductSupplier ps) {
        ps.setSupplierPartyRole(this);
        return productSuppliers.add(ps);
    }

    public boolean removeProductSupplier(ProductSupplier ps) {
        return productSuppliers.remove(ps);
    }

}
