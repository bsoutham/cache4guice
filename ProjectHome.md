# cache4guice #

Cache4Guice allows you to cache results from methods calls, and return the cached value on subsequent calls to the method.  An optional "TimeToLive" value may be specified, causing the cached result to be evicted from the cache, and the method call will be made again to recompute the value, and the new value will be stored in the cache.

Assuming that you are already using [Google Guice](http://code.google.com/p/google-guice/), caching the method result can be as simple as adding an annotation to the method.


```
        @Cached
        public Integer multiArgMethod(Object o, int i, boolean b, String[] c, Map<String, String> map) {
           // Some expensive long running calculation
        }
```



Cache4Guice currently integrates with the following caches:
  * [Ehcache](http://ehcache.org/)
  * [Infinispan](http://www.jboss.org/infinispan)
  * [JBossCache](http://www.jboss.org/jbosscache)
  * Very simple Cache4Guice internal cache