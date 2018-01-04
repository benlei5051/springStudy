package org.andy.jackson.entity.type;

import java.util.List;

/**
 * @author: andy
 * @Date: 2017/12/27 16:35
 * @Description:
 */
public class PojoWithTypedObjects {
    public List<BaseClass> items;

    public List<BaseClass> getItems() {
        return items;
    }

    public void setItems(List<BaseClass> items) {
        this.items = items;
    }
}