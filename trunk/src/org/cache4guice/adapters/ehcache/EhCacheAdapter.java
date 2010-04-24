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
package org.cache4guice.adapters.ehcache;

import net.sf.ehcache.Element;

import org.cache4guice.adapters.Cache;

public class EhCacheAdapter implements Cache {

    private final net.sf.ehcache.Cache cache;

    public EhCacheAdapter(net.sf.ehcache.Cache cache) {
        this.cache = cache;
    }

    @Override
    public Object get(String key) {
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    @Override
    public void put(String key, Object value) {
        put(key, value, 0);
    }

    @Override
    public void put(String key, Object value, int timeToLiveInSeconds) {
        Element element = new Element(key, value, Boolean.FALSE, 0, timeToLiveInSeconds);
        cache.put(element);
    }

}
