package edu.nextstep.camp.data.model;

import java.lang.System;

@androidx.room.Entity()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Ledu/nextstep/camp/data/model/CalculatorRecord;", "", "id", "", "expression", "Ledu/nextstep/camp/domain/calculator/Expression;", "result", "(ILedu/nextstep/camp/domain/calculator/Expression;I)V", "getExpression", "()Ledu/nextstep/camp/domain/calculator/Expression;", "getId", "()I", "getResult", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "data_debug"})
public final class CalculatorRecord {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final edu.nextstep.camp.domain.calculator.Expression expression = null;
    private final int result = 0;
    
    @org.jetbrains.annotations.NotNull()
    public final edu.nextstep.camp.data.model.CalculatorRecord copy(int id, @org.jetbrains.annotations.NotNull()
    edu.nextstep.camp.domain.calculator.Expression expression, int result) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public CalculatorRecord(int id, @org.jetbrains.annotations.NotNull()
    edu.nextstep.camp.domain.calculator.Expression expression, int result) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final edu.nextstep.camp.domain.calculator.Expression component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final edu.nextstep.camp.domain.calculator.Expression getExpression() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getResult() {
        return 0;
    }
}