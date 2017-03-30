package com.scorpion.searchEngine;

import com.scorpion.pojo.NewWithBLOBs;
import com.scorpion.util.analyzer.IKAnalyzer5x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Scorpion on 2017/3/20.
 */
public class IndexManager {
    private static final String index = "D:/index/";

    public static void createIndex() throws SQLException, IOException {
        DBConnect con = new DBConnect();
        List<NewWithBLOBs> dates = con.getDatas();
        Analyzer analyzer = new IKAnalyzer5x();
        Path path = Paths.get(index);
        File indexFile = path.toFile();
        //删除文件夹下的所有文件
        File[] files = indexFile.listFiles();
        for (File file : files) {
            file.delete();
        }
        Directory dir = FSDirectory.open(path);
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for (NewWithBLOBs data : dates){
            Document doc = new Document();
            //String formatdate = sdf.format(data.getDate());
            doc.add(new TextField("name",data.getName(), Field.Store.YES));
            doc.add(new TextField("url",data.getUrl(), Field.Store.YES));
            doc.add(new TextField("title",data.getTitle(), Field.Store.YES));
            doc.add(new TextField("date",data.getDate(),Field.Store.YES));
            doc.add(new TextField("description",data.getDescription(), Field.Store.YES));
            doc.add(new TextField("html",data.getHtml(), Field.Store.YES));
            doc.add(new TextField("keywords",data.getKeywords(), Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.commit();
        writer.close();
        dir.close();
        System.out.println("index create is finished!!!");
    }

    private static void printAnalysisResult(Analyzer analyzer, String keyWord)
            throws Exception {
        System.out.println("["+keyWord+"]分词效果如下");
        TokenStream tokenStream = analyzer.tokenStream("content",
                new StringReader(keyWord));
        tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream
                    .getAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute.toString());

        }
        tokenStream.end();
        tokenStream.close();
    }
    public static void main(String[] args) throws Exception {

    }
}
