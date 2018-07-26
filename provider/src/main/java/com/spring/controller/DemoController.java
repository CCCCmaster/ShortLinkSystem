package com.spring.controller;



import com.spring.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 短网址控制器
 */
@RestController
public class DemoController {

    /**
     * service服务
     * 长链转短链
     * 短链接查长链
     */
    @Autowired
    URLService urlService;

    /**
     * 长链接转短链接接口
     * POST方式
     * @param initialURL
     * @return 短链接
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> insert(String initialURL){
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", urlService.shortenURL(initialURL));
        return map;
    }

    /**
     * 根据短链接查询出原始链接
     * GET方式
     * @param shortURL
     * @return 原始链接
     */
    @RequestMapping(value = "/{shortURL}", method = RequestMethod.GET)
    public Map<String, String> redirect(@PathVariable("shortURL") String shortURL){
        String initialURL = urlService.queryRealURL(shortURL);
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", initialURL);
        return map;
    }



}
