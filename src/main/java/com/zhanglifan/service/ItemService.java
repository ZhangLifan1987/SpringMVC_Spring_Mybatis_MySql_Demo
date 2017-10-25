package com.zhanglifan.service;

import com.zhanglifan.pojo.Items;

import java.util.List;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/24 22:46
 * Description:
 */
public interface ItemService {
    List<Items> findAllItems();
    Items findItemById(Integer id);
    List<Items> findItemByName(String name);
    void deleteBatch(Integer[] ids);
    void updateBatch(List<Items> itemsList);

    void updateItem(Items items);
}
