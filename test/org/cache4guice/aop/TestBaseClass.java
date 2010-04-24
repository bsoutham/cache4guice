package org.cache4guice.aop;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.cache4guice.Cached;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.internal.ImmutableMap;

public abstract class TestBaseClass {

    protected Injector injector = Guice.createInjector(getModule());

    abstract Module getModule();

    @Test(expected = NullPointerException.class)
    public void testCacheInterceptorThrowsOnNullConstructorArg() {
        new CacheInterceptor(null);
    }

    @Test
    public void testCacheInterecptor() throws Throwable {
        TestObject o = injector.getInstance(TestObject.class);
        o.noArgMethod();
        o.noArgMethod();
        Assert.assertEquals(1, o.count.get());
    }

    @Test
    public void testMultiArgmethod() throws Throwable {
        TestObject o = injector.getInstance(TestObject.class);
        o.multiArgMethod(new ArrayList<String>(), 1, true, new String[] { "arg", "arg2" }, ImmutableMap.of("key1",
                "val1", "key2", "val2"));
        o.multiArgMethod(new ArrayList<String>(), 1, true, new String[] { "arg", "arg2" }, ImmutableMap.of("key1",
                "val1", "key2", "val2"));
        Assert.assertEquals(1, o.count.get());
    }

    static class TestObject {
        AtomicInteger count = new AtomicInteger();

        @Cached(timeToLiveSeconds = 100)
        public Integer noArgMethod() {
            return count.getAndIncrement();
        }

        @Cached
        public Integer multiArgMethod(Object o, int i, boolean b, String[] c, Map<String, String> map) {
            return count.getAndIncrement();
        }
    }

}
