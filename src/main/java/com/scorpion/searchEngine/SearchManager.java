package com.scorpion.searchEngine;


import com.scorpion.pojo.NewWithBLOBs;
import com.scorpion.util.analyzer.IKAnalyzer5x;
import com.scorpion.util.data.Page;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Scorpion on 2017/3/20.
 */
public class SearchManager {

    final static int pageSize = 10;
    final static int perTotal = 50;

    public static Page<NewWithBLOBs> search(String key, int page) throws IOException, ParseException, java.text.ParseException, InvalidTokenOffsetsException {
        Directory dir = FSDirectory.open(Paths.get("D:/index/"));
        Analyzer analyzer = new IKAnalyzer5x();
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        String[] fields = new String[]{"name", "title", "description", "html", "keywords"};
        QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        //允许在关键词开头使用通配符？
        parser.setAllowLeadingWildcard(true);
        //\s+匹配多个任何空白字符，包括空格、制表符、换页符等等
        key = key.replaceAll("\\s+", "");
        Query query = parser.parse(key);
        TopDocs topDocs = searcher.search(query, (page * pageSize + perTotal - 1) / perTotal * perTotal);

        //css 设置 <em> 样式为红色#dd4b39
        Formatter formatter = new SimpleHTMLFormatter("&lt;em&gt;", "&lt;/em&gt;");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        Fragmenter fragmenter = new SimpleFragmenter(80);            //设置每次返回的字符数
        highlighter.setTextFragmenter(fragmenter);

        ScoreDoc[] hits = topDocs.scoreDocs;
        List<NewWithBLOBs> dateList = new ArrayList<>();
        ScoreDoc scoreDoc = null;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = (page - 1) * pageSize; i < page * pageSize && i < topDocs.totalHits; i++) {
            scoreDoc = hits[i];
            Document doc = searcher.doc(scoreDoc.doc);
            NewWithBLOBs data = new NewWithBLOBs();
            String url = doc.get("url");
            String showurl = "";
            if (url.length() <= 80) {
                showurl = url.substring(0, url.length());
            } else if (url.length() > 80) {
                showurl = url.substring(0, 80) + "...";
            }
            String name = doc.get("name");
            String title = highlighter.getBestFragment(analyzer, "title", doc.get("title"));
            if (title == null) {
                title = doc.get("title");
            }
            String description = highlighter.getBestFragment(analyzer, "description", doc.get("title"));
            if (description == null) {
                description = doc.get("description");
            }
            String date = doc.get("date");
            Date date1 = sim.parse(date);
            String date2 = sim.format(date1);
            data.setTitle(title);
            data.setUrl(showurl);
            data.setName(name);
            Date date3 = new Date();
            date3 = sim.parse(date2);
            data.setDate(date);
            data.setDescription(description);
            dateList.add(data);
        }
        /*System.out.println("关键词：" + key);
        System.out.println("搜索结果为：");
        *//*for (int i = 0; i < dateList.size(); i++) {
            NewWithBLOBs data = dateList.get(i);
            System.out.println(i + "  " + data.getName() + "  " + data.getTitle() + "   url:" + data.getUrl() + "   " + sim.format(data.getDate()));
        }*/
        reader.close();
        dir.close();
        Page<NewWithBLOBs> resultPage = new Page<NewWithBLOBs>(dateList,topDocs.totalHits,10);
        return resultPage;
    }

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException, InvalidTokenOffsetsException {
        long timeStart = System.currentTimeMillis();
        Page<NewWithBLOBs> data = SearchManager.search("上海三星半导体", 20);
        long timeEnd = System.currentTimeMillis();
        String usedTime = String.format("%.2f", (timeEnd - timeStart) / 1000.0);
        System.out.println("检索用时：" + usedTime);
    }
}
