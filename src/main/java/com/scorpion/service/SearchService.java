package com.scorpion.service;

import com.scorpion.pojo.New;
import com.scorpion.pojo.NewWithBLOBs;
import com.scorpion.util.data.Page;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import java.text.ParseException;

/**
 * Created by Scorpion on 2017/3/20.
 */
public interface SearchService {
    public New queryByName(String name);
    public Page<NewWithBLOBs> queryDate(String key, int page) throws ParseException, InvalidTokenOffsetsException;
}
