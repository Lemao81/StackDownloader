package com.jueggs.data.db

import android.arch.persistence.room.*
import android.content.Context
import com.jueggs.data.db.dao.*
import com.jueggs.data.db.entity.*

@Database(entities = [(AnswerEntity::class), (OwnerEntity::class), (QuestionEntity::class), (TagEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract var answerDao: AnswerDao
    abstract var questionDao: QuestionDao
    abstract var ownerDao: OwnerDao
    abstract var tagDao: TagDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val NAME: String = ""

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, NAME).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}