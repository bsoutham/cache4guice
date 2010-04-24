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
package org.cache4guice.adapters.jbosscache;

import org.cache4guice.Cached;
import org.cache4guice.aop.CacheInterceptor;
import org.jboss.cache.Cache;
import org.jboss.cache.DefaultCacheFactory;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class JBossCacheModule extends AbstractModule {

    private final JBossCacheAdapter cache;

    public JBossCacheModule(Cache<String, Object> cache) {
        if (cache == null) {
            this.cache = new JBossCacheAdapter(getDefaultCache());
        } else {
            this.cache = new JBossCacheAdapter(cache);
        }
    }

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class), new CacheInterceptor(cache));
    }

    final Cache<String, Object> getDefaultCache() {
        return new DefaultCacheFactory<String, Object>().createCache();
    }
}
