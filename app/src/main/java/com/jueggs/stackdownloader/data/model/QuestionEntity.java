package com.jueggs.stackdownloader.data.model;

import org.greenrobot.greendao.annotation.*;

@Entity(nameInDb = "QUESTION")
public class QuestionEntity {
    @Id(autoincrement = true)
    private long id;
    @Property
    private long questionId;

//    private long ownerId;
//
//    @ToOne(joinProperty = "ownerId")
//    private OwnerEntity owner;

    @Property
    private String tagsLabel;
    @Property
    private String scoreLabel;
    @Property
    private String answerCountLabel;
    @Property
    private long creationDate;
    @Property
    private String title;
    @Property
    private String bodyFromHtml;
//    end
    @Generated(hash = 2109539706)
    public QuestionEntity(long id, long questionId, String tagsLabel,
            String scoreLabel, String answerCountLabel, long creationDate,
            String title, String bodyFromHtml) {
        this.id = id;
        this.questionId = questionId;
        this.tagsLabel = tagsLabel;
        this.scoreLabel = scoreLabel;
        this.answerCountLabel = answerCountLabel;
        this.creationDate = creationDate;
        this.title = title;
        this.bodyFromHtml = bodyFromHtml;
    }
    @Generated(hash = 98121125)
    public QuestionEntity() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getQuestionId() {
        return this.questionId;
    }
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
    public String getTagsLabel() {
        return this.tagsLabel;
    }
    public void setTagsLabel(String tagsLabel) {
        this.tagsLabel = tagsLabel;
    }
    public String getScoreLabel() {
        return this.scoreLabel;
    }
    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel = scoreLabel;
    }
    public String getAnswerCountLabel() {
        return this.answerCountLabel;
    }
    public void setAnswerCountLabel(String answerCountLabel) {
        this.answerCountLabel = answerCountLabel;
    }
    public long getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBodyFromHtml() {
        return this.bodyFromHtml;
    }
    public void setBodyFromHtml(String bodyFromHtml) {
        this.bodyFromHtml = bodyFromHtml;
    }
}