package edu.nextstep.camp.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0016J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Ledu/nextstep/camp/data/LocalCalculatorRecordRepository;", "Ledu/nextstep/camp/domain/calculator/repository/CalculatorRecordRepository;", "calculatorRecordDAO", "Ledu/nextstep/camp/data/db/CalculatorRecordDAO;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Ledu/nextstep/camp/data/db/CalculatorRecordDAO;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getAllRecord", "Lkotlinx/coroutines/flow/Flow;", "", "Ledu/nextstep/camp/domain/calculator/CalculatorMemory$Record;", "saveRecord", "", "record", "(Ledu/nextstep/camp/domain/calculator/CalculatorMemory$Record;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_debug"})
public final class LocalCalculatorRecordRepository implements edu.nextstep.camp.domain.calculator.repository.CalculatorRecordRepository {
    private final edu.nextstep.camp.data.db.CalculatorRecordDAO calculatorRecordDAO = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    public static final edu.nextstep.camp.data.LocalCalculatorRecordRepository.Companion Companion = null;
    @kotlin.jvm.Volatile()
    private static volatile edu.nextstep.camp.data.LocalCalculatorRecordRepository instance;
    
    private LocalCalculatorRecordRepository(edu.nextstep.camp.data.db.CalculatorRecordDAO calculatorRecordDAO, kotlinx.coroutines.CoroutineDispatcher ioDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<edu.nextstep.camp.domain.calculator.CalculatorMemory.Record>> getAllRecord() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveRecord(@org.jetbrains.annotations.NotNull()
    edu.nextstep.camp.domain.calculator.CalculatorMemory.Record record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Ledu/nextstep/camp/data/LocalCalculatorRecordRepository$Companion;", "", "()V", "instance", "Ledu/nextstep/camp/data/LocalCalculatorRecordRepository;", "getInstance", "calculatorRecordDAO", "Ledu/nextstep/camp/data/db/CalculatorRecordDAO;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final edu.nextstep.camp.data.LocalCalculatorRecordRepository getInstance(@org.jetbrains.annotations.NotNull()
        edu.nextstep.camp.data.db.CalculatorRecordDAO calculatorRecordDAO) {
            return null;
        }
    }
}