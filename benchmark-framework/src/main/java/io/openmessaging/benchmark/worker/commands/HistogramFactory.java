/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.openmessaging.benchmark.worker.commands;

import org.HdrHistogram.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistogramFactory {
    private static final Logger log = LoggerFactory.getLogger(HistogramFactory.class);
    private static final int DEFAULT_NUMBER_OF_SIGNIFICANT_VALUE_DIGITS = 5;
    private static int NUMBER_OF_SIGNIFICANT_VALUE_DIGITS = DEFAULT_NUMBER_OF_SIGNIFICANT_VALUE_DIGITS;

    static {
        String value = System.getenv("HISTOGRAM.NUMBER_OF_SIGNIFICANT_VALUE_DIGITS");
        if (value != null && !value.isEmpty()) {
            try {
                NUMBER_OF_SIGNIFICANT_VALUE_DIGITS = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // PASS
            }
        }
        log.info("Histogram - numberOfSignificantValueDigits : {}", NUMBER_OF_SIGNIFICANT_VALUE_DIGITS);
    }

    public static Histogram create(long highestTrackableValue) {
        return new Histogram(highestTrackableValue, NUMBER_OF_SIGNIFICANT_VALUE_DIGITS);
    }
}
