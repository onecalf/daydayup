package com.onecalf.hard.buck;

import com.onecalf.hard.buck.annotion.DbFiled;
import com.onecalf.hard.buck.annotion.DbTable;

@DbTable("tb_person4")
public class Person {
    @DbFiled("tb_name")
    public String name;

    @DbFiled("tb_password")
    public String password;


}
