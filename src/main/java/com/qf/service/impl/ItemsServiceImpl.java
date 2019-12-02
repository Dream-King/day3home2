package com.qf.service.impl;

import com.qf.pojo.Items;
import com.qf.repository.ItemsRepository;
import com.qf.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 长风 on 2019/11/20.
 */
@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    ItemsRepository itemsRepository;

    @Override
    public List<Items> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public void insertItems(Items items) {
        itemsRepository.save(items);

    }

    @Override
    public void updateItems(Items items) {
        itemsRepository.saveAndFlush(items);

    }

    @Override
    public void deleteById(Integer id) {
        itemsRepository.deleteById(id);

    }

  /* @Override
    public Optional<Items> findById(Integer id) {
        return itemsRepository.findById(id);

    }*/
  @Override
  public Items findById(Integer id) {
      Optional<Items> byId =  itemsRepository.findById(id);
      Items items=null;
      if (byId.isPresent()){
          items = byId.get();
      }
      return items;
  }

    @Override
    public List<Items> FindByNameAndPrice(Items items) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("price", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Items> of = Example.of(items, matcher);
        return itemsRepository.findAll(of);
    }
}
