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
package org.cache4guice;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.cache4guice.key.KeyGenerator;
import org.cache4guice.key.ToStringKeyGenerator;

import com.google.inject.BindingAnnotation;

@Retention(RUNTIME)
@BindingAnnotation
@Target(ElementType.METHOD)
public @interface Cached {
    int timeToLiveSeconds() default 0;

    Class<? extends KeyGenerator> keyGeneratorClass() default ToStringKeyGenerator.class;
}
