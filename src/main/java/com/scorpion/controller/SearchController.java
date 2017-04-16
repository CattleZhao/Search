package com.scorpion.controller;

import com.scorpion.pojo.NewWithBLOBs;
import com.scorpion.pojo.NewsOfCompany;
import com.scorpion.service.SearchFromData;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scorpion on 2017/3/20.
 */
@Controller
@RequestMapping("/s")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private SearchFromData searchFromData;

    /**
     * 跳转到首页，需要进行从数据查询各大公司的新闻数量，选取前十画个柱状图
     *
     * @return
     */
    @RequestMapping("/showHome")
    public ModelAndView showHome(ModelMap modelMap) {
        List<NewsOfCompany> newsOfCompanyList = searchFromData.queryNum();
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();
        for (NewsOfCompany newsOfCompany : newsOfCompanyList) {
            nameList.add(newsOfCompany.getName());
            numList.add(newsOfCompany.getNum());
        }
        jsonObject.put("nameList", nameList);
        jsonObject.put("numList", numList);
        modelMap.addAttribute("jsonObject", jsonObject.toString());
        return new ModelAndView("home", modelMap);
    }

    /**
     * 搜索所有新闻
     *
     * @param
     * @return
     */
    @RequestMapping("/showNews")
    public ModelAndView showNews(HttpServletResponse response, String key, String page) throws IOException {
        return new ModelAndView("resultAllNews");
    }

    @RequestMapping("/showSearch")
    public ModelAndView showSearch() {
        return new ModelAndView("search");
    }

    @RequestMapping("/showResult")
    public ModelAndView showResult(String key, ModelMap modelMap) throws ParseException, InvalidTokenOffsetsException {
        modelMap.addAttribute("key", key);
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
    /*
    Ajax 返回所有新闻的json文件
     */
    @ResponseBody
    @RequestMapping("/queryNews")
    public void queryNews(HttpServletResponse response, String page) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int pageNo = page == null ? 1 : Integer.parseInt(page);
        Page<NewWithBLOBs> resultPage = searchService.queryAllNews(pageNo);
        List<NewWithBLOBs> resultList = resultPage.getContent();
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("resultList", resultList);
        json.put("totalRecords", resultPage.getTotalRecords());
        json.put("totalPages", resultPage.getTotalPages());
        out.print(json);
    }
}
