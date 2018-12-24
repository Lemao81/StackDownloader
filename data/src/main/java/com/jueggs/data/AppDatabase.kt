package com.jueggs.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jueggs.andutils.converter.DateTimeUnixLongConverter
import com.jueggs.andutils.converter.UnixLongStringDateTimeConverter
import com.jueggs.data.dao.AnswerDao
import com.jueggs.data.dao.OwnerDao
import com.jueggs.data.dao.QuestionDao
import com.jueggs.data.dao.QuestionTagJoinDao
import com.jueggs.data.dao.TagDao
import com.jueggs.data.entity.AnswerEntity
import com.jueggs.data.entity.OwnerEntity
import com.jueggs.data.entity.QuestionEntity
import com.jueggs.data.entity.QuestionTagJoinEntity
import com.jueggs.data.entity.TagEntity

@Database(entities = [(AnswerEntity::class), (OwnerEntity::class), (QuestionEntity::class), (TagEntity::class), (QuestionTagJoinEntity::class)], version = 1)
@TypeConverters(value = [(DateTimeUnixLongConverter::class), (UnixLongStringDateTimeConverter::class)])
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

        fun getInMemoryInstance(context: Context) = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}