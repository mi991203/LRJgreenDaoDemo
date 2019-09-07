package com.example.lrjgreendaodemo;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Score {
    @NotNull
    private long sno;
    @NotNull
    private long cno;
    @NotNull
    private int score;
@Generated(hash = 1007909659)
public Score(long sno, long cno, int score) {
    this.sno = sno;
    this.cno = cno;
    this.score = score;
}
@Generated(hash = 226049941)
public Score() {
}
public long getSno() {
    return this.sno;
}
public void setSno(long sno) {
    this.sno = sno;
}
public long getCno() {
    return this.cno;
}
public void setCno(long cno) {
    this.cno = cno;
}
public int getScore() {
    return this.score;
}
public void setScore(int score) {
    this.score = score;
}


}
