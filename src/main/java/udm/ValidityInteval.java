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

/**
 *
 * @author skrymets
 */
public class ValidityInteval extends PersistentEntity {

    private static final long serialVersionUID = 3872171862460852449L;

    private LocalDateTime validFrom;

    private LocalDateTime validThru;

    public ValidityInteval() {
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidThru() {
        return validThru;
    }

    public void setValidThru(LocalDateTime validThru) {
        this.validThru = validThru;
    }

}
