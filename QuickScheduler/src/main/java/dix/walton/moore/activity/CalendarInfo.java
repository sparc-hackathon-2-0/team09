/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package dix.walton.moore.activity;

/**
 * Class that holds information about a Calendar.
 *
 * @author Ravi Mistry
 */
public class CalendarInfo implements Comparable<CalendarInfo> {
  String id;
  public String summary;

  CalendarInfo(String id, String summary) {
    this.id = id;
    this.summary = summary;
  }

  @Override
  public String toString() {
    return summary;
  }

  public int compareTo(CalendarInfo other) {
    return summary.compareTo(other.summary);
  }
}
