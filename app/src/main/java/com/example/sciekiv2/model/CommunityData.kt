package com.example.sciekiv2.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class CommunityData(
    @PrimaryKey private var id: String = UUID.randomUUID().toString(),
    public var communityName: String = ""
): RealmObject()