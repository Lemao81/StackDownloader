package com.jueggs.data

import android.arch.persistence.room.*
import android.content.Context
import com.jueggs.data.dao.*
import com.jueggs.data.entity.*

@Database(entities = [(AnswerEntity::class), (OwnerEntity::class), (QuestionEntity::class), (TagEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerDao(): AnswerDao
    abstract fun questionDao(): QuestionDao
    abstract fun ownerDao(): OwnerDao
    abstract fun tagDao(): TagDao

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