package com.zhanglifan.service.impl;

import com.zhanglifan.mapper.ItemsMapper;
import com.zhanglifan.pojo.Items;
import com.zhanglifan.pojo.ItemsExample;
import com.zhanglifan.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/24 22:46
 * Description:
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    @Resource
    private ItemsMapper itemsMapper;

    public List<Items> findAllItems() {
        return itemsMapper.selectByExampleWithBLOBs(new ItemsExample());
    }

    public Items findItemById(Integer id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    public List<Items> findItemByName(String name) {
        ItemsExample itemsExample = new ItemsExample();
        itemsExample.createCriteria().andNameLike("%"+name+"%");
        List<Items> itemsList = itemsMapper.selectByExampleWithBLOBs(itemsExample);
        return itemsList;
    }
}
