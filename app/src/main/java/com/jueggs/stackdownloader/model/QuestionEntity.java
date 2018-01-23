package com.jueggs.stackdownloader.model;

import org.greenrobot.greendao.annotation.*;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "QUESTION")
public class QuestionEntity {
    @Id(autoincrement = true)
    private long id;
    @Property
    @Unique
    private long questionId;
    @Property
    @ToOne(joinProperty = "id")
    private OwnerEntity owner;
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
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 511499490)
    private transient QuestionEntityDao myDao;
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
    @Generated(hash = 1847295403)
    private transient Long owner__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 75554974)
    public OwnerEntity getOwner() {
        long __key = this.id;
        if (owner__resolvedKey == null || !owner__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OwnerEntityDao targetDao = daoSession.getOwnerEntityDao();
            OwnerEntity ownerNew = targetDao.load(__key);
            synchronized (this) {
                owner = ownerNew;
                owner__resolvedKey = __key;
            }
        }
        return owner;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 882262868)
    public void setOwner(@NotNull OwnerEntity owner) {
        if (owner == null) {
            throw new DaoException(
                    "To-one property 'id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.owner = owner;
            id = owner.getId();
            owner__resolvedKey = id;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 462114450)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getQuestionEntityDao() : null;
    }

}