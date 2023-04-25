package com.sourav.notifcationspy.di

import android.app.Application
import androidx.room.Room
import com.sourav.notifcationspy.data.NotificationDao
import com.sourav.notifcationspy.data.NotificationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app : Application) : NotificationDatabase {
        return Room.databaseBuilder(
            app,
            NotificationDatabase::class.java,
            NotificationDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db : NotificationDatabase,
    ): NotificationDao {
        return db.dao
    }


//    @Provides
//    @Singleton
//    fun provideNoteRepository(db : NoteDatabase) : NoteRepository {
//        return NoteRepositoryImpl(db.noteDao)
//    }

//    @Provides
//    @Singleton
//    fun provideNoteUseCases(repository: NoteRepository) : NoteUseCases {
//        return NoteUseCases(
//            GetNotesUseCase(repository),
//            DeleteNoteUseCase(repository),
//            AddNoteUseCase(repository),
//            GetSingleNoteUseCase(repository)
//        )
//    }


}