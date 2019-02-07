/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bson.codecs.pojo.entities.conventions;

import java.util.ArrayList;
import java.util.List;

public class CollectionsGetterMutableModel {

    private final List<Integer> listField;

    public CollectionsGetterMutableModel() {
        this(new ArrayList<Integer>());
    }

    public CollectionsGetterMutableModel(final List<Integer> listField) {
        this.listField = listField;
    }

    public List<Integer> getListField() {
        return listField;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CollectionsGetterMutableModel that = (CollectionsGetterMutableModel) o;
        return listField != null ? listField.equals(that.listField) : that.listField == null;
    }

    @Override
    public int hashCode() {
        return listField != null ? listField.hashCode() : 0;
    }
}