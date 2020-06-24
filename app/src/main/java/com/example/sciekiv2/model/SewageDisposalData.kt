package com.example.sciekiv2.model

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.time.LocalDateTime
import java.util.*

@RealmClass
open class SewageDisposalData(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    @Ignore
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var adress: String = "",
    var quantity_of_sewage: Float = 0f,
    var type_of_sewage: String = "",
    var community: String = ""
): RealmObject()