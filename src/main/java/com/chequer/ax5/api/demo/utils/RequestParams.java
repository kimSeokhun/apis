//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chequer.ax5.api.demo.utils;

import com.chequer.ax5.api.demo.response.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.*;
import java.util.stream.Collectors;

public class RequestParams<T> {
    private Map<String, Object> map;
    private List<Order> sortOrders = new ArrayList();
    public Class<T> clazz;

    public RequestParams(Class<T> clazz) {
        this.clazz = clazz;
        this.map = new HashMap();
    }

    public RequestParams() {
        this.map = new HashMap();
    }

    public void put(String key, Object value) {
        this.map.put(key, value);
    }

    public void setParameterMap(Map<String, String[]> map) {
        Iterator var2 = map.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            String[] values = (String[])map.get(key);
            String value = (String)Arrays.stream(values).collect(Collectors.joining(","));
            this.put(key, value);
        }

    }

    public String getString(String key, String defaultValue) {
        if(this.map.containsKey(key)) {
            String value = (String)this.map.get(key);
            return StringUtils.isEmpty(value)?defaultValue:value;
        } else {
            return defaultValue;
        }
    }

    public String getString(String key) {
        return this.getString(key, (String)null);
    }

    public int getInt(String key, int defaultValue) {
        String value = this.getString(key);
        return StringUtils.isEmpty(value)?defaultValue:Integer.parseInt(value);
    }

    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        String value = this.getString(key);
        return StringUtils.isEmpty(value)?defaultValue:Long.parseLong(value);
    }

    public long getLong(String key) {
        return this.getLong(key, 0L);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = this.getString(key);
        return StringUtils.isEmpty(value)?defaultValue:Boolean.parseBoolean(value);
    }

    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    public Pageable getPageable() {
        int page = this.getInt("pageNumber", 0);
        int size = this.getInt("pageSize", 2147483647);
        return new PageRequest(page, size, this.getSort());
    }

    public void addSort(String value, Direction direction) {
        if(!this.hasSortParameter()) {
            this.sortOrders.add(new Order(direction, value));
        }

    }

    public boolean hasSortParameter() {
        return StringUtils.isNotEmpty(this.getString("sort"));
    }

    public Sort getSort() {
        if(!this.hasSortParameter()) {
            return ArrayUtils.isNotEmpty(this.sortOrders)?new Sort(this.sortOrders):null;
        } else {
            ArrayList orders = new ArrayList();
            String sortParameter = this.getString("sort");
            String[] sortValues = sortParameter.split(",");

            for(int i = 0; i < sortValues.length; i += 2) {
                orders.add(new Order(Direction.fromString(sortValues[i + 1]), sortValues[i]));
            }

            return new Sort(orders);
        }
    }

    public String getFilter() {
        return this.getString("filter", "");
    }

    public Object getObject(String key) {
        return this.map.containsKey(key)?this.map.get(key):null;
    }

    public Object getNotEmptyObject(String key) {
        if(!this.map.containsKey(key)) {
            throw new ApiException(key + " is not present");
        } else {
            Object object = this.map.get(key);
            if(object != null && !StringUtils.isEmpty(object.toString())) {
                return object;
            } else {
                throw new ApiException(key + " is not present");
            }
        }
    }

    public Integer getNotEmptyInt(String key) {
        return Integer.valueOf(Integer.parseInt(this.getNotEmptyString(key)));
    }

    public String getNotEmptyString(String key) {
        return (String)this.getNotEmptyObject(key);
    }

    public Long getNotEmptyLong(String key) {
        return Long.valueOf(Long.parseLong(this.getNotEmptyString(key)));
    }
}
