package com.example.sciekiv2.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class CommunityData(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    private var createdAt: Date = Date(),
    var communityName: String = ""
): RealmObject()