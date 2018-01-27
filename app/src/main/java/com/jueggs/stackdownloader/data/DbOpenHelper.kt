package com.jueggs.stackdownloader.data

import android.content.Context
import com.jueggs.stackdownloader.data.model.DaoMaster
import org.greenrobot.greendao.database.Database

class DbOpenHelper(context: Context, name:String) : DaoMaster.OpenHelper(context, name) {
    override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        when (oldVersion) {

        }
    }
}