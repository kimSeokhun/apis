//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chequer.ax5.api.demo.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ArrayUtils {
    public ArrayUtils() {
    }

    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.size() <= 0;
    }

    public static boolean isNotEmpty(Collection<?> list) {
        return list != null && list.size() > 0;
    }

    public static Collection subtract(Collection firstCollection, Collection secondCollection) {
        ArrayList collection = new ArrayList();
        Iterator var3 = firstCollection.iterator();

        while(var3.hasNext()) {
            Object object = var3.next();
            if(!secondCollection.contains(object)) {
                collection.add(object);
            }
        }

        return collection;
    }
}
