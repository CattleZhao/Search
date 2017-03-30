package com.scorpion.controller;

import com.scorpion.pojo.NewWithBLOBs;
import com.scorpion.service.SearchService;
import com.scorpion.util.data.Page;
import net.minidev.json.JSONObject;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Scorpion on 2017/3/20.
 */
@Controller
@RequestMapping("/s")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/showSearch")
    public ModelAndView showSearch() {

        return new ModelAndView("search");
    }

    @RequestMapping("/showResult")
    public ModelAndView showResult(String key, ModelMap modelMap) throws ParseException, InvalidTokenOffsetsException {
        modelMap.addAttribute("key", key);
        //Page<NewWithBLOBs> resultPage = searchService.queryDate(key, 3);
        return new ModelAndView("result", modelMap);
    }

    @ResponseBody
    @RequestMapping("/query")
    public void queryResult(HttpServletResponse response, String key, String page) throws IOException, ParseException, InvalidTokenOffsetsException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        int pageNo = page == null ? 1 : Integer.parseInt(page);
        long timeStart = System.currentTimeMillis();
        Page<NewWithBLOBs> resultPage = searchService.queryDate(key, pageNo);
        long timeEnd = System.currentTimeMillis();
        String usedTime = String.format("%.2f", (timeEnd - timeStart) / 1000.0);

        List<NewWithBLOBs> resultList = resultPage.getContent();
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("resultList", resultList);
        json.put("totalRecords", resultPage.getTotalRecords());
        json.put("totalPages", resultPage.getTotalPages());
        json.put("usedTime", usedTime);
        out.print(json);
    }
}
