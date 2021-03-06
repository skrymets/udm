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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author skrymets
 */
public interface ValidityAware {

    public LocalDateTime EPOCH_START = LocalDateTime.of(LocalDate.MIN, LocalTime.MIN);

    public LocalDateTime EPOCH_END = LocalDateTime.of(LocalDate.of(9999, Month.DECEMBER, 31), LocalTime.MAX);

    boolean isValidNow();

    void validFromNow();

    void validFrom(LocalDateTime dateTime);

    void validThruNow();

    void validThru(LocalDateTime dateTime);

}
