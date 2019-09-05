package com.example.lrjgreendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Course {
    @Id
    private Long cno;
    @NotNull
    private String cname;
    @NotNull
    private int ctime;
    @NotNull
    private String teacher;
    @Generated(hash = 459301576)
    public Course(Long cno, @NotNull String cname, int ctime,
            @NotNull String teacher) {
        this.cno = cno;
        this.cname = cname;
        this.ctime = ctime;
        this.teacher = teacher;
    }
    @Generated(hash = 1355838961)
    public Course() {
    }
    public Long getCno() {
        return this.cno;
    }
    public void setCno(Long cno) {
        this.cno = cno;
    }
    public String getCname() {
        return this.cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public int getCtime() {
        return this.ctime;
    }
    public void setCtime(int ctime) {
        this.ctime = ctime;
    }
    public String getTeacher() {
        return this.teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
