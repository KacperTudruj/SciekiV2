package com.example.sciekiv2

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class SewageDisposalData(
    @PrimaryKey private var id: String = UUID.randomUUID().toString(),
    private var createdAt: Date = Date(),
    var adress: String = "",
    var quantity_of_sewage: Float = 0f,
    var type_of_sewage: String = "",
    var community: String = ""
): RealmObject()