package com.jueggs.stackdownloader.data.model;

import org.greenrobot.greendao.annotation.*;


@Entity(nameInDb = "ANSWER")
public class AnswerEntity {
    @Id(autoincrement = true)
    private long id;
    @Property
    private long answerId;
    @Property
    private long questionId;

//    private long ownerId;
//
//    @ToOne(joinProperty = "ownerId")
//    private OwnerEntity owner;

    @Property
    private long creationDate;
    @Property
    private String scoreLabel;
    @Property
    private String bodyFromHtml;
//    end
    @Generated(hash = 260569994)
    public AnswerEntity(long id, long answerId, long questionId, long creationDate,
            String scoreLabel, String bodyFromHtml) {
        this.id = id;
        this.answerId = answerId;
        this.questionId = questionId;
        this.creationDate = creationDate;
        this.scoreLabel = scoreLabel;
        this.bodyFromHtml = bodyFromHtml;
    }
    @Generated(hash = 1117918334)
    public AnswerEntity() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getAnswerId() {
        return this.answerId;
    }
    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
    public long getQuestionId() {
        return this.questionId;
    }
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
    public long getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }
    public String getScoreLabel() {
        return this.scoreLabel;
    }
    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel = scoreLabel;
    }
    public String getBodyFromHtml() {
        return this.bodyFromHtml;
    }
    public void setBodyFromHtml(String bodyFromHtml) {
        this.bodyFromHtml = bodyFromHtml;
    }
}
