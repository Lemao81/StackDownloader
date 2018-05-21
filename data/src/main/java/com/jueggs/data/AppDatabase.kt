package com.jueggs.data

import android.arch.persistence.room.*
import android.content.Context
import com.jueggs.andutils.helper.DateConverter
import com.jueggs.data.dao.*
import com.jueggs.data.entity.*

@Database(entities = [(AnswerEntity::class), (OwnerEntity::class), (QuestionEntity::class), (TagEntity::class), (QuestionTagJoinEntity::class)], version = 1)
@TypeConverters(value = [(DateConverter::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerDao(): AnswerDao
    abstract fun questionDao(): QuestionDao
    abstract fun ownerDao(): OwnerDao
    abstract fun tagDao(): TagDao
    abstract fun questionTagJoinDao(): QuestionTagJoinDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val NAME: String = "stackdownloader.db"

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