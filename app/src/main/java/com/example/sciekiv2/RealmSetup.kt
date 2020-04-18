package com.example.sciekiv2

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class RealmSetup : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .name("sewageDisposal.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(configuration)

//        Realm.init(this)
//        val config = RealmConfiguration.Builder()
//            .name("sawageDB.realm").build()
//        val realm = Realm.getInstance(config)
//        realm.beginTransaction()
//        val sawageDB = realm.createObject(SewageDisposalData::class.java, 1)
//        sawageDB.type_of_sewage = "xd"
//
//        realm.commitTransaction()
    }
}
