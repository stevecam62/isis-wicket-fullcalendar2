/*
 *  Copyright 2013~2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.wicket.fullcalendar2.fixture.scripts;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class FullCalendar2WicketTearDownFixture extends FixtureScript {

    private final String ownedBy;

    public FullCalendar2WicketTearDownFixture(final String ownedBy) {
        this.ownedBy = ownedBy;
    }

    @Override
    protected void execute(ExecutionContext executionContext) {
        if(ownedBy != null) {
            isisJdoSupport.executeUpdate("delete from \"FullCalendar2WicketToDoItemDependencies\" where \"dependingId\" IN (select \"id\" from \"Fullcalendar2WicketToDoItem\" where \"ownedBy\" = '" + ownedBy + "') ");
            isisJdoSupport.executeUpdate("delete from \"FullCalendar2WicketToDoItemDependencies\" where \"dependentId\" IN (select \"id\" from \"Fullcalendar2WicketToDoItem\" where \"ownedBy\" = '" + ownedBy + "') ");
            isisJdoSupport.executeUpdate("delete from \"FullCalendar2WicketToDoItem\" where \"ownedBy\" = '" + ownedBy + "'");
        } else {
            isisJdoSupport.executeUpdate("delete from \"FullCalendar2WicketToDoItemDependencies\"");
            isisJdoSupport.executeUpdate("delete from \"FullCalendar2WicketToDoItem\"");
        }
    }


    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
