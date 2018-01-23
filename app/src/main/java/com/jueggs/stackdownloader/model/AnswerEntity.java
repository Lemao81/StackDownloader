package com.jueggs.stackdownloader.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(nameInDb = "ANSWER")
public class AnswerEntity {
    @Id(autoincrement = true)
    private long id;
    @Property
    private long answerId;
    @Property
    private long questionId;
    @Property
    @ToOne(joinProperty = "id")
    private OwnerEntity owner;
    @Property
    private long creationDate;
    @Property
    private String scoreLabel;
    @Property
    private String bodyFromHtml;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 194427544)
    private transient AnswerEntityDao myDao;
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
    @Generated(hash = 1622668575)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAnswerEntityDao() : null;
    }

}
