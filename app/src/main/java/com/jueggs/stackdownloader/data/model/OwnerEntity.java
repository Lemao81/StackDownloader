package com.jueggs.stackdownloader.data.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


@Entity(nameInDb = "OWNER")
public class OwnerEntity {
    @Id(autoincrement = true)
    private long id;
    @Property
    private String displayName;
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

//    @ToMany(referencedJoinProperty = "ownerId")
//    private List<QuestionEntity> questions;
//    @ToMany(referencedJoinProperty = "ownerId")
//    private List<AnswerEntity> answers;
//    end
}