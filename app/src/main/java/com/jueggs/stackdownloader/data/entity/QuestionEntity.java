package com.jueggs.stackdownloader.data.entity;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

@Entity(nameInDb = "QUESTION")
public class QuestionEntity {
    @Id
    private Long id;

    @Property
    private String tagsString;

    @Property
    private boolean isAnswered;

    @Property
    private int viewCount;

    @Property
    private int answerCount;

    @ToOne(joinProperty = "ownerId")
    private OwnerEntity owner;
    private Long ownerId;

    @ToMany(referencedJoinProperty = "questionId")
    private List<AnswerEntity> answers;

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
    @Generated(hash = 511499490)
    private transient QuestionEntityDao myDao;

    @Generated(hash = 266465945)
    public QuestionEntity(Long id, String tagsString, boolean isAnswered,
            int viewCount, int answerCount, Long ownerId, int score,
            long creationDate, String title, String body) {
        this.id = id;
        this.tagsString = tagsString;
        this.isAnswered = isAnswered;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.ownerId = ownerId;
        this.score = score;
        this.creationDate = creationDate;
        this.title = title;
        this.body = body;
    }

    @Generated(hash = 98121125)
    public QuestionEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagsString() {
        return this.tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }

    public boolean getIsAnswered() {
        return this.isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getAnswerCount() {
        return this.answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1594820974)
    public List<AnswerEntity> getAnswers() {
        if (answers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnswerEntityDao targetDao = daoSession.getAnswerEntityDao();
            List<AnswerEntity> answersNew = targetDao
                    ._queryQuestionEntity_Answers(id);
            synchronized (this) {
                if (answers == null) {
                    answers = answersNew;
                }
            }
        }
        return answers;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 663994474)
    public synchronized void resetAnswers() {
        answers = null;
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