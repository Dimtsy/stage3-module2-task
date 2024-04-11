package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSource {
    private static final int MAX_AMOUNT_OF_NEWS = 20;
    private final String AUTHOR_PATH = "authors";
    private final String CONTENT_PATH = "content";
    private final String NEWS_PATH = "news";

    private List<AuthorModel> authorModelList;
    private List<NewsModel> newsModelList;

    @Autowired
    public DataSource() {
        fillAuthorModelList();
        fillNewsModelList();
    }

    public List<AuthorModel> getAuthorList() {
        return authorModelList;
    }

    public List<NewsModel> getNewsList() {
        return newsModelList;
    }

    private void fillAuthorModelList() {
        authorModelList = new ArrayList<>();
        List<String> authorNames = Randomizer.getTextListFromFile(AUTHOR_PATH);
        for (int i=0; i<MAX_AMOUNT_OF_NEWS; i++) {
            String name = Randomizer.getRandomText(authorNames);
            LocalDateTime dateTime = Randomizer.getRandomDateTime();
            AuthorModel authorModel = new AuthorModel((long) (i+1), name, dateTime, dateTime);
            authorModelList.add(authorModel);
        }
    }

    private void fillNewsModelList() {
        newsModelList = new ArrayList<>();
        Random random = new Random();
        List<String> newsTitles = Randomizer.getTextListFromFile(NEWS_PATH);
        List<String> newsContents = Randomizer.getTextListFromFile(CONTENT_PATH);
        for (int i=0; i<MAX_AMOUNT_OF_NEWS; i++) {
            String title = Randomizer.getRandomText(newsTitles);
            String content = Randomizer.getRandomText(newsContents);
            LocalDateTime dateTime = Randomizer.getRandomDateTime();
            Long randomAuthorId = random.nextLong((authorModelList.size()-1L) + 1L) + 1L;
            NewsModel newsModel = new NewsModel((long) (i+1), title, content,
                    dateTime, dateTime, randomAuthorId);
            newsModelList.add(newsModel);
        }
    }
}