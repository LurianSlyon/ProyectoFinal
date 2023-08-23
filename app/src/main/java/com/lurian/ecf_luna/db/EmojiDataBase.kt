package com.lurian.ecf_luna.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lurian.ecf_luna.modal.EmojiEntity
import kotlinx.coroutines.runBlocking
@Database(entities = [EmojiEntity::class], version = 1)
abstract class EmojiDataBase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao

    companion object {
        @Volatile
        private var instance: EmojiDataBase? = null

        fun getDatabase(context: Context): EmojiDataBase {
            return instance ?: synchronized(this) {
                val _instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmojiDataBase::class.java,
                    "librodb"
                )

                    .build()

                instance = _instance
                _instance
            }
        }
    }
}
