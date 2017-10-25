package com.zhanglifan.controller;

import com.zhanglifan.pojo.Items;
import com.zhanglifan.service.ItemService;
import com.zhanglifan.vo.ItemListVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("/queryItem")
    public String queryItem(@RequestParam("item_name") String name,Model model){
        List<Items> itemsList = itemService.findItemByName(name);
        model.addAttribute("itemList",itemsList);
        model.addAttribute("keyword",name);
        return "itemList";
    }

    @RequestMapping("deleteBatch")
    public String deleteBatch(@RequestParam("id") Integer[] ids){
        itemService.deleteBatch(ids);
        return "redirect:itemList";
    }

    @RequestMapping("/updateBatch")
    public void updateBatch(ItemListVo itemListVo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemService.updateBatch(itemListVo.getItemList());
        request.getRequestDispatcher("/item/itemList").forward(request,response);
    }

    @RequestMapping("/itemEdit/{id}")
    public ModelAndView toEdit(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item",itemService.findItemById(id));
        modelAndView.setViewName("editItem");
        return modelAndView;
    }

    @RequestMapping("/updateItem")
    public String edit(Items items , MultipartFile pictureFile) throws IOException {
        if(pictureFile!=null){
            String suffix = pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;
            pictureFile.transferTo(new File("D:/下载/"+fileName));
            items.setPic(fileName);
        }
        itemService.updateItem(items);
        return "forward:itemList"; //此为相对路径,不是逻辑视图
    }

}
