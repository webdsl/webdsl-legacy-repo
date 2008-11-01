package org.webdsl;

import java.io.Serializable;

public interface WebDSLEntity extends Serializable, Comparable<WebDSLEntity> {
    boolean isInstance(Class<?> c);
    public boolean instanceOf(String s);
}
