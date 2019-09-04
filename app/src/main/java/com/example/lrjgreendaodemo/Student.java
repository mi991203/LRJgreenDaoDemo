package com.example.lrjgreendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id
    private long sno;
    @NotNull
    private String sname;
    @NotNull
    private String ssex;
    @NotNull
    private int sage;
    @Generated(hash = 799324884)
    public Student(long sno, @NotNull String sname, @NotNull String ssex,
            int sage) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sage = sage;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public long getSno() {
        return this.sno;
    }
    public void setSno(long sno) {
        this.sno = sno;
    }
    public String getSname() {
        return this.sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSsex() {
        return this.ssex;
    }
    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
    public int getSage() {
        return this.sage;
    }
    public void setSage(int sage) {
        this.sage = sage;
    }
}
