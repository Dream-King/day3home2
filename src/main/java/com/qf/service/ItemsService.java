package com.qf.service;

import com.qf.pojo.Items;

import java.util.List;
import java.util.Optional;

/**
 * Created by 长风 on 2019/11/20.
 */
public interface ItemsService {
    List<Items> findAll();

    void insertItems(Items items);

    void updateItems(Items items);

    void deleteById(Integer id);

    /*Optional<Items> findById(Integer id);*/
    Items findById(Integer id);

    List<Items> FindByNameAndPrice(Items items);

}
