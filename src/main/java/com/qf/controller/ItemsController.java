package com.qf.controller;

import com.qf.pojo.Items;
import com.qf.service.ItemsService;
import com.qf.utils.UploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 长风 on 2019/11/20.
 */
@RequestMapping("/items")
@Controller
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @Value("${qiniu.url}")
    private String url;
    @Autowired
    private UploadUtils uploadUtils;

    /*
    查询全部
     */
    @RequiresPermissions(value = {"user_find"})
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Items> all = itemsService.findAll();
        model.addAttribute("all", all);
        return "show";
    }

    /*
    跳转添加页面
     */
    @RequiresPermissions(value = {"user_insert"})
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "insert";
    }

    /*
    增
     */
/* @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String insertItems(@RequestParam("name") String name,
                              @RequestParam("price") Float price,
                              @RequestParam("detail") String detail,
                              @RequestParam("file") String pic,
                              @RequestParam("createtime") String createtime) throws ParseException {
        Items items = new Items();
        items.setName(name);
        items.setPrice(price);
        items.setPic(pic);
        items.setDetail(detail);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(createtime);
        items.setCreatetime(parse);
        itemsService.insertItems(items);
        return "redirect:show";
    }*/

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertItems(Items items, @RequestParam("file") MultipartFile muli) {
//        items.setPic(muli.getOriginalFilename());
        try {
            String upload = uploadUtils.upload(muli);
            items.setPic(url + upload);
            System.out.println(url + upload);
            itemsService.insertItems(items);
            return "redirect:show";
        } catch (Exception e) {
            return "404";
        }
    }

    /*
    查询一个
     */
    @RequiresPermissions(value = {"user_update"})
    @RequestMapping(value = "/selectOne", method = RequestMethod.GET)
    public String findById(Model model, @RequestParam("id") Integer id) {
        /*Optional<Items> byId = itemsService.findById(id);
        Items items = null;
        if (byId.isPresent()) {
            items = byId.get();
        }
        model.addAttribute("items", items);
        return "update";*/
        Items items = itemsService.findById(id);
        model.addAttribute("items", items);
        return "update";
    }

    /*
    删
     */

    @RequiresPermissions(value = {"user_delate"})
    @RequestMapping(value = "/deleteItems", method = RequestMethod.GET)
    public String deleteById(@RequestParam("id") Integer id) {
        itemsService.deleteById(id);
        return "redirect:show";

    }

    /*
    修改
     */
    @RequiresPermissions(value = {"user_update"})
    @RequestMapping(value = "/updateItems", method = RequestMethod.POST)
   /* public String updateItems(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("price") Float price,
                              @RequestParam("detail") String detail,
                              @RequestParam("pic") String pic,
                              @RequestParam("createtime") String createtime) throws ParseException {
        Items items = new Items();
        items.setId(id);
        items.setName(name);
        items.setPrice(price);
        items.setPic(pic);
        items.setDetail(detail);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(createtime);
        items.setCreatetime(parse);
        itemsService.updateItems(items);
        return "redirect:show";
    }*/
    public String updateItems(Items items, @RequestParam("file") MultipartFile muli) {
        String originalFilename = muli.getOriginalFilename();

        if(!originalFilename.equals("")){
                String upload = uploadUtils.upload(muli);
                items.setPic(url + upload);
            }
            itemsService.updateItems(items);
            return "redirect:show";

    }

    @RequestMapping(value = "/selectByNameAndPrice", method = RequestMethod.GET)
    public String findByNameAndPrice(@RequestParam("name") String name, @RequestParam("price") Float price, Model model) {
        Items items = new Items();
        items.setName(name);
        items.setPrice(price);
        List<Items> all = itemsService.FindByNameAndPrice(items);
        model.addAttribute("all", all);
        return "show";
    }


}
