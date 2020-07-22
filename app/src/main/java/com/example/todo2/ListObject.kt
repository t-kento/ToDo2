package com.example.realm

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ListObject : RealmObject() {
    @PrimaryKey
    var id: Long = System.currentTimeMillis()
    var edit_text = ""
    var hasCompleted = false

//    var completedAt: Date? = null

    companion object {
        fun findAll(): List<ListObject> =
            Realm.getDefaultInstance().use { realm ->
                realm.where(ListObject::class.java)
                    .findAll()
                    .let {
                        realm.copyFromRealm(it)
                    }
            }
        // 未完了のタスク
        fun findToDoAll(): List<ListObject> =
            findAll().filterNot { it.hasCompleted }

        // 完了したタスク
        fun findCompletedAll(): List<ListObject> =
            findAll().filter { it.hasCompleted }

        fun delete(data: ListObject) {
            Realm.getDefaultInstance().use {
                it.where(ListObject::class.java)
                    .equalTo(ListObject::id.name, data.id)
                    .findFirst()?.also { listObject ->
                        it.executeTransaction {
                            listObject.deleteFromRealm()
                        }
                    }
            }
        }
    }
}
