package com.lzy.testproject.framework.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

/**
 * Created by LiZhiyu on 2018/7/16.
 */
@Entity(tableName = "tb_student",//定义表名
        indices = @Index(value = {"name", "sex"}, unique = true),//定义索引
        foreignKeys = {@ForeignKey(entity = ClassEntity.class,
                parentColumns = "id",
                childColumns = "class_id")})//定义外键
public class ClasEntity {
}
