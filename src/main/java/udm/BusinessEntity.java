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
package udm;

import java.time.LocalDateTime;
import static java.util.Objects.*;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author skrymets
 */
@MappedSuperclass
public abstract class BusinessEntity extends MutablePersistentEntity implements ValidityAware {

    private static final long serialVersionUID = 249793911463091173L;

    @Column(nullable = false)
    private LocalDateTime validFrom = Defaults.EPOCH_START;

    @Column(nullable = false)
    private LocalDateTime validThru = Defaults.EPOCH_END;

    public BusinessEntity() {
    }

    @Override
    public boolean isValidNow() {
        LocalDateTime now = LocalDateTime.now();
        return (validFrom.compareTo(now) <= 0 && validThru.compareTo(now) >= 0);
    }

    @Override
    public void validFromNow() {
        validFrom(LocalDateTime.now());
    }

    @Override
    public void validThruNow() {
        validThru(LocalDateTime.now());
    }

    @Override
    public void validFrom(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.validFrom = dateTime;

        if (this.validThru.compareTo(dateTime) <= 0) { // now or in the past?
            this.validThru = EPOCH_END;
        }
    }

    @Override
    public void validThru(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.validThru = dateTime;

        if (this.validFrom.compareTo(dateTime) >= 0) { // now or in the future?
            this.validFrom = dateTime;
        }
    }

}
