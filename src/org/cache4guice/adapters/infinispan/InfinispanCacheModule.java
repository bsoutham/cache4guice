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
package org.cache4guice.adapters.infinispan;

import org.cache4guice.Cached;
import org.cache4guice.aop.CacheInterceptor;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class InfinispanCacheModule extends AbstractModule {

    private final InfinispanAdapter cache;
    private static final String CACHE_NAME = InfinispanCacheModule.class.getName();

    public InfinispanCacheModule(Cache<String, Object> cache) {
        if (cache == null) {
            this.cache = new InfinispanAdapter(getDefaultCache());
        } else {
            this.cache = new InfinispanAdapter(cache);
        }
    }

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class), new CacheInterceptor(cache));
    }

    final Cache<String, Object> getDefaultCache() {
        return new DefaultCacheManager().getCache(CACHE_NAME);
    }
}
