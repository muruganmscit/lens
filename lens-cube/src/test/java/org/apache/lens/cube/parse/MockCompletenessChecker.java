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
package org.apache.lens.cube.parse;

import java.util.*;

import org.apache.lens.server.api.error.LensException;
import org.apache.lens.server.api.metastore.*;

public class MockCompletenessChecker implements DataCompletenessChecker {

  @Override
  public Map<String, Map<Date, Float>> getCompleteness(String factTag, Date start, Date end, Set<String> measureTag)
    throws LensException {
    Map<Date, Float> partitionCompleteness = new HashMap<>();
    Map<String, Map<Date, Float>> result = new HashMap<>();
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("GMT"));
    cal.add(Calendar.DATE, -1);
    if (start.before(cal.getTime()) && end.after(cal.getTime())) {
      if (factTag.equals("f1")) {
        partitionCompleteness.put(cal.getTime(), 80f);
      } else {
        partitionCompleteness.put(cal.getTime(), 90f);
      }
    }
    result.put("tag1", partitionCompleteness);
    return result;
  }

}

