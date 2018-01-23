package com.jueggs.stackdownloader.model;

import org.greenrobot.greendao.annotation.*;

import java.util.List;

import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "OWNER")
public class OwnerEntity {
    @Id(autoincrement = true)
    private long id;
    @Property
    private String displayName;
    @Property
    @ToMany(referencedJoinProperty = "id")
    private List<QuestionEntity> questions;
    @Property
    @ToMany(referencedJoinProperty = "id")
    private List<AnswerEntity> answers;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1083277909)
    private transient OwnerEntityDao myDao;
    @Generated(hash = 530814326)
    public OwnerEntity(long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
    @Generated(hash = 630426608)
    public OwnerEntity() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1234280732)
    public List<QuestionEntity> getQuestions() {
        if (questions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuestionEntityDao targetDao = daoSession.getQuestionEntityDao();
            List<QuestionEntity> questionsNew = targetDao
                    ._queryOwnerEntity_Questions(id);
            synchronized (this) {
                if (questions == null) {
                    questions = questionsNew;
                }
            }
        }
        return questions;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1619718141)
    public synchronized void resetQuestions() {
        questions = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 40150484)
    public List<AnswerEntity> getAnswers() {
        if (answers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnswerEntityDao targetDao = daoSession.getAnswerEntityDao();
            List<AnswerEntity> answersNew = targetDao._queryOwnerEntity_Answers(id);
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
    @Generated(hash = 2122961127)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOwnerEntityDao() : null;
    }
}