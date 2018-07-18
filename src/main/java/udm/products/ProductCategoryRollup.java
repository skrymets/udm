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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import udm.BusinessEntity;

/**
 *
 * @author skrymets
 */
@Entity
@Table(
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"generalCategory_id", "subCategory_id"})
        }
)
public class ProductCategoryRollup extends BusinessEntity {

    private static final long serialVersionUID = 6791662664295132910L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private ProductCategory subCategory;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private ProductCategory generalCategory;

    public ProductCategoryRollup() {
    }

    public ProductCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductCategory category) {
        this.subCategory = category;
    }

    public ProductCategory getGeneralCategory() {
        return generalCategory;
    }

    public void setGeneralCategory(ProductCategory category) {
        this.generalCategory = category;
    }

}
