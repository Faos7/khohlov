package com.khohlov.khohlov.service.model;



import com.khohlov.khohlov.domain.model.Item;
import com.khohlov.khohlov.domain.model.dummies_forms.ItemForm;

import java.util.Collection;

public interface ItemService {

    Collection<Item> getAllItems();

    boolean remove(Item item);

    Item create(ItemForm itemForm);

    void delete(Integer id);
}
