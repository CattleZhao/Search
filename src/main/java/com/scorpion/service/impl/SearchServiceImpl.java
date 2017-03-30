package com.scorpion.service.impl;

import com.scorpion.dao.NewMapper;
import com.scorpion.pojo.New;
import com.scorpion.pojo.NewWithBLOBs;
import com.scorpion.searchEngine.SearchManager;
import com.scorpion.service.SearchService;
import com.scorpion.util.data.Page;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Scorpion on 2017/3/20.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private NewMapper newMapper;
    public New queryByName(String name){
        return newMapper.selectByName(name);
    }

    public Page<NewWithBLOBs> queryDate(String key, int page) throws java.text.ParseException, InvalidTokenOffsetsException {
        Page<NewWithBLOBs> resultPage = null;
        try {
            resultPage = SearchManager.search(key,page);
        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultPage;
    }
}
