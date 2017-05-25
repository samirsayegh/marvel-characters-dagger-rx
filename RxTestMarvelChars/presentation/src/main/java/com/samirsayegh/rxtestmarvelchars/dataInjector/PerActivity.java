package com.samirsayegh.rxtestmarvelchars.dataInjector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yormirsamir.sayegh on 18/05/2017.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
