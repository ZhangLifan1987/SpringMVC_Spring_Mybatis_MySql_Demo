package com.zhanglifan.controller;

import com.zhanglifan.pojo.Items;
import com.zhanglifan.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/24 22:15
 * Description:
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    @RequestMapping("/itemList")
    public String itemList(HttpServletRequest request){
        List<Items> itemsList = itemService.findAllItems();
        request.setAttribute("itemList",itemsList);
        return "itemList";
    }

    @RequestMapping("/itemEdit/{id}")
    public ModelAndView toEdit(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item",itemService.findItemById(id));
        modelAndView.setViewName("editItem");
        return modelAndView;
    }

    @RequestMapping("/queryItem")
    public String queryItem(@RequestParam("item_name") String name,Model model){
        List<Items> itemsList = itemService.findItemByName(name);
        model.addAttribute("itemList",itemsList);
        return "itemList";
    }

}
