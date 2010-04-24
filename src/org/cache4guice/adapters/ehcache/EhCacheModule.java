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

import java.util.concurrent.locks.ReentrantLock;

import org.cache4guice.Cached;
import org.cache4guice.aop.CacheInterceptor;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class EhCacheModule extends AbstractModule {

    private final EhCacheAdapter cache;
    private static final ReentrantLock CACHE_CREATE_LOCK = new ReentrantLock();
    private static final String CACHE_NAME = EhCacheModule.class.getName();

    public EhCacheModule() {
        this(getDefaultCache());
    }

    public EhCacheModule(Cache cache) {
        this.cache = new EhCacheAdapter(cache);
    }

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class), new CacheInterceptor(cache));
    }

    static final Cache getDefaultCache() {
        CACHE_CREATE_LOCK.lock();
        try {
            Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
            if (cache == null) {
                CacheManager.create().addCache(CACHE_NAME);
                cache = CacheManager.getInstance().getCache(CACHE_NAME);
            }
            return cache;
        } finally {
            CACHE_CREATE_LOCK.unlock();
        }
    }

}
