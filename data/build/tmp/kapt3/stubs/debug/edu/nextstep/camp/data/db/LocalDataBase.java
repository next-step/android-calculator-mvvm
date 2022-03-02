package edu.nextstep.camp.data.db;

import java.lang.System;

@androidx.room.TypeConverters(value = {edu.nextstep.camp.data.db.Convertors.class})
@androidx.room.Database(entities = {edu.nextstep.camp.data.model.CalculatorRecord.class}, version = 1)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Ledu/nextstep/camp/data/db/LocalDataBase;", "Landroidx/room/RoomDatabase;", "()V", "calculatorRecordDAO", "Ledu/nextstep/camp/data/db/CalculatorRecordDAO;", "Companion", "data_debug"})
public abstract class LocalDataBase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final edu.nextstep.camp.data.db.LocalDataBase.Companion Companion = null;
    private static edu.nextstep.camp.data.db.LocalDataBase INSTANCE;
    
    public LocalDataBase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract edu.nextstep.camp.data.db.CalculatorRecordDAO calculatorRecordDAO();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Ledu/nextstep/camp/data/db/LocalDataBase$Companion;", "", "()V", "INSTANCE", "Ledu/nextstep/camp/data/db/LocalDataBase;", "getInstance", "context", "Landroid/content/Context;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final edu.nextstep.camp.data.db.LocalDataBase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}