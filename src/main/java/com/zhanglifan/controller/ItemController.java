package com.zhanglifan.controller;

import com.zhanglifan.exception.CustomException;
import com.zhanglifan.pojo.Items;
import com.zhanglifan.service.ItemService;
import com.zhanglifan.validator.ValidatorGroup1;
import com.zhanglifan.vo.ItemListVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public String deleteBatch(@RequestParam(value = "id",required = false) Integer[] ids) throws CustomException {
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
    //验证器使用时,BindingResult必须紧跟其后,否则报错400
    public String edit(@Validated(value={ValidatorGroup1.class}) Items items, BindingResult bindingResult, MultipartFile pictureFile) throws IOException {
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println("*** error 的值是: " + new String(error.getDefaultMessage().getBytes("iso-8859-1"),"gbk")  + " ***");//此处要看properties文件是什么类型,这里是GBK类型
            }
        }

        if(pictureFile!=null){
            String suffix = pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;
            pictureFile.transferTo(new File("D:/下载/"+fileName));
            items.setPic(fileName);
        }
        itemService.updateItem(items);
        return "forward:itemList"; //此为相对路径,不是逻辑视图
    }

    @RequestMapping("/viewItem")
    public @ResponseBody Items viewItem(@RequestBody Items items){
        Items item = itemService.findItemById(items.getId());
        return item;
    }

}
