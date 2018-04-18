package com.khohlov.khohlov.service.model.Impl;


import com.khohlov.khohlov.domain.model.Item;
import com.khohlov.khohlov.domain.model.dummies_forms.ItemForm;
import com.khohlov.khohlov.repository.model.CompanyRepository;
import com.khohlov.khohlov.repository.model.CountryRepository;
import com.khohlov.khohlov.repository.model.ItemRepository;
import com.khohlov.khohlov.service.model.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           CompanyRepository companyRepository, CountryRepository countryRepository) {
        this.itemRepository = itemRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Collection<Item> getAllItems() {
        LOGGER.debug("Getting all items");
        return itemRepository.findAll(new Sort("name"));
    }

    @Override
    public boolean remove(Item item) {


        itemRepository.delete(itemRepository.findOne(item.getItemId()));
        return false;
    }

    @Override
    public Item create(ItemForm itemForm) {
        ArrayList<String> stockData = parseCompanyData(itemForm.getCompany());
//        LOGGER.debug(stockData.get(0) + " " + stockData.get(1));
        if (companyRepository.getByCountry_NameAndName(stockData.get(0),stockData.get(1)).isPresent()) {
            Item item = new Item();
            item.setName(itemForm.getName());
            item.setPrice(itemForm.getPrice());
            item.setType(itemForm.getType());
            item.setCountry(countryRepository.findOneByName(itemForm.getCountry()).get());
            item.setCompany(companyRepository.getByCountry_NameAndName(stockData.get(0), stockData.get(1)).get());
            return itemRepository.save(item);
        }
        return null;
    }

    public static ArrayList<String> parseCompanyData(String st){
        int i1 = st.indexOf(',');
        int i2 = st.indexOf('.');
        String s1 = st.substring(0,i1);

        String s2 = st.substring(i1+11,i2 );
        ArrayList<String> res = new ArrayList<>(2);
        res.add(s1);
        res.add(s2);
        return res;
    }

    @Override
    public void delete(Integer id) {
        itemRepository.delete(itemRepository.getOne(id));
    }
}
