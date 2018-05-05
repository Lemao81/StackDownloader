package com.jueggs.stackdownloader.data.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "ANSWER")
public class AnswerEntity {
    @Id
    private Long id;

    @Property
    private boolean isAccepted;

    @ToOne(joinProperty = "ownerId")
    private OwnerEntity owner;
    private Long ownerId;

    @ToOne(joinProperty = "questionId")
    private QuestionEntity question;
    private Long questionId;

    @Property
    private int score;

    @Property
    private long creationDate;

    @Property
    private String title;

    @Property
    private String body;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 194427544)
    private transient AnswerEntityDao myDao;

    @Generated(hash = 876174604)
    public AnswerEntity(Long id, boolean isAccepted, Long ownerId, Long questionId,
            int score, long creationDate, String title, String body) {
        this.id = id;
        this.isAccepted = isAccepted;
        this.ownerId = ownerId;
        this.questionId = questionId;
        this.score = score;
        this.creationDate = creationDate;
        this.title = title;
        this.body = body;
    }

    @Generated(hash = 1117918334)
    public AnswerEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsAccepted() {
        return this.isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Generated(hash = 1847295403)
    private transient Long owner__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1106568884)
    public OwnerEntity getOwner() {
        Long __key = this.ownerId;
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
    @Generated(hash = 1426352852)
    public void setOwner(OwnerEntity owner) {
        synchronized (this) {
            this.owner = owner;
            ownerId = owner == null ? null : owner.getId();
            owner__resolvedKey = ownerId;
        }
    }

    @Generated(hash = 527827701)
    private transient Long question__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1841167107)
    public QuestionEntity getQuestion() {
        Long __key = this.questionId;
        if (question__resolvedKey == null || !question__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuestionEntityDao targetDao = daoSession.getQuestionEntityDao();
            QuestionEntity questionNew = targetDao.load(__key);
            synchronized (this) {
                question = questionNew;
                question__resolvedKey = __key;
            }
        }
        return question;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1740734525)
    public void setQuestion(QuestionEntity question) {
        synchronized (this) {
            this.question = question;
            questionId = question == null ? null : question.getId();
            question__resolvedKey = questionId;
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
