package com.zhanglifan.service.impl;

import com.zhanglifan.exception.CustomException;
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

    public void deleteBatch(Integer[] ids) throws CustomException {
        if(ids==null || ids.length==0){
            throw new CustomException("请选择要删除的物品~!");
        }
        for (Integer id : ids) {
            //itemsMapper.deleteByPrimaryKey(id);
            System.out.println("*** 删除了 "+id+" ***");
        }
    }

    public void updateBatch(List<Items> itemsList) {
        for (Items items : itemsList) {
            itemsMapper.updateByPrimaryKeySelective(items);
        }
    }

    public void updateItem(Items items) {
        itemsMapper.updateByPrimaryKeySelective(items);
    }
}
