/***
 * Copyright 2010 Blaine R Southam
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cache4guice.key;

import java.util.Arrays;
import java.util.Collection;

public class ToStringKeyGenerator extends AbstractKeyGenerator {

    @Override
    public String getKey(Object keyObject) {
        String key = keyObject.getClass().getSimpleName() + ":";
        if (keyObject instanceof Object[]) {
            return key + Arrays.deepToString((Object[]) keyObject);
        }
        if (keyObject instanceof Collection<?>) {
            return key + Arrays.deepToString(((Collection<?>) keyObject).toArray());
        }
        return key + "[" + String.valueOf(keyObject) + "]";
    }

}
