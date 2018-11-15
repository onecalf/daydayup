package com.onecalf.hard.buck;

import com.onecalf.hard.buck.annotion.DbFiled;
import com.onecalf.hard.buck.annotion.DbTable;

@DbTable("tb_person3")
public class Person {
    @DbFiled("tb_name")
    public String name;

    @DbFiled("tb_password")
    public String password;

    @DbFiled("tb_age")
    public int age;

    @DbFiled("tb_ranking")
    public Integer ranking;

}
