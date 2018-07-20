# 1.概述
android系统中的数据库SQLite使用起来并不方便，去年google推出了架构组件，其中room就是一款orm框架。Room就是用来处理数据库的框架

# 2.添加Room依赖库
1. 添加google的maven库，在project的gradle文件：
```
allprojects {
    repositories {
        jcenter()
        google() // 添加谷歌maven库
    }
}
```

2. 添加架构组件依赖库，在module的gradle文件
```
dependencies {
    // Room (use 1.1.0-alpha2 for latest alpha)
    implementation "android.arch.persistence.room:runtime:1.0.0"
    annotationProcessor "android.arch.persistence.room:compiler:1.0.0"

    // Test helpers for Room
    testImplementation "android.arch.persistence.room:testing:1.0.0"
}
```
> 1.以上为gradle插件3.0
  2.如果是kotlin项目，确保用kapt代替annotationProcessor，同时也要添加kotlin-kapt插件

```
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'
apply plugin: 'kotlin-android-extensions'
```


3. 为room添加rxjava支持库
```
dependencies {
    // RxJava support for Room (use 1.1.0-alpha1 for latest alpha)
    implementation "android.arch.persistence.room:rxjava2:1.0.0"
}
```

4. Room @Dao查询中添加对Guava的Optional和ListenableFuture类型的支持。
```
dependencies {
    // Guava support for Room
    implementation "android.arch.persistence.room:guava:1.1.0-alpha2"
}
```

5. 和LiveData一起使用
```
// ReactiveStreams support for LiveData
 implementation "android.arch.lifecycle:reactivestreams:1.0.0"
```

# 3.先来个例子
## 3.1定义学生表
```
/**
 * Created by LiZhiyu on 2018/7/12.
 */
@Entity(tableName = "tb_student",//定义表名
        indices = [(Index(value = arrayOf("name", "sex"), unique = true))],//定义索引
        foreignKeys = [(ForeignKey(entity = ClassEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("class_id")))])//定义外键
//定义索引
//定义外键
class StudentEntity {
    @PrimaryKey //定义主键
    var id: Long = 0
    @ColumnInfo(name = "name")//定义数据表中的字段名
    var name: String? = null
    @ColumnInfo(name = "sex")
    var sex: Int = 0
    @Ignore//指示Room需要忽略的字段或方法
    var ignoreText: String? = null
    @ColumnInfo(name = "class_id")
    var class_id: String? = null
}
```

## 3.2定义Dao
```
/**
 * Created by LiZhiyu on 2018/7/19.
 */
@Dao
interface StudentDao {

    @Query("SELECT * FROM StudentEntity")
    fun getAll(): List<StudentEntity>

    @Query("SELECT * FROM StudentEntity WHERE id IN (:ids)")
    fun getAllById(ids: LongArray): List<StudentEntity>

    @Insert
    fun insert(studentEntity: StudentEntity)

    @Delete
    fun delete(studentEntity: StudentEntity)
}
```

## 3.3定义数据库
```
/**
 * Created by LiZhiyu on 2018/7/20.
 */
@Database(entities = [StudentEntity::class], version = 1)
abstract class RoomDemoDB : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}
```

## 3.4生成数据库实例
```
 //创建数据库实例
        val database = Room.databaseBuilder(
                applicationContext,
                RoomDemoDB::class.java,
                "database_name"
        )
        database.addCallback(object : RoomDatabase.Callback() {
            //第一次创建数据库时调用，但是在创建所有表之后调用的
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.e("lzy", "DB onCreate")
            }

            //当数据库被打开时调用
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.e("lzy", "DB onOpen")
            }
        })
                .allowMainThreadQueries()//允许主线程查询数据
```


# 4.Room三大组件
- Database: 用这个组件创建一个数据库。注解定义了一系列entities，并且类中提供一系列Dao的抽象方法，
也是下层主要连接的访问点。注解的类应该是一个继承 RoomDatabase的抽象类。在运行时，你能通过调用Room.databaseBuilder()
或者 Room.inMemoryDatabaseBuilder()获得一个实例
- Entity： 用这个组件创建表，Database类中的entities数组通过引用这些entity类创建数据库表。
每个entity中的字段都会被持久化到数据库中，除非用@Ignore注解
- DAO： 这个组件代表了一个用来操作表增删改查的dao。Dao 是Room中的主要组件，负责定义访问数据库的方法。
被注解@Database的类必须包含一个没有参数的且返回注解为@Dao的类的抽象方法。在编译时，Room创建一个这个类的实现。

> Entity类能够有一个空的构造函数（如果dao类能够访问每个持久化的字段）或者一个参数带有匹配entity中的字段的类型和名称的构造函数

## 4.1. 参数说明
### 4.1.1 Entity注解可选参数
Entity的字段必须为public或提供setter或者getter。
```
public @interface Entity {
    //定义表名
    String tableName() default "";
    //定义索引
    Index[] indices() default {};
    //设为true则父类的索引会自动被当前类继承
    boolean inheritSuperIndices() default false;
    //定义主键
    String[] primaryKeys() default {};
    //定义外键
    ForeignKey[] foreignKeys() default {};
}
```
- Primary Key主键：每个entity必须定义至少一个字段作为主键。即使这里只有一个字段，仍然需要使用@PrimaryKey注解这个字段。
并且，如果想Room动态给entity分配自增主键，可以设置@PrimaryKey的autoGenerate属性为true。如果entity有个组合的主键，
你可以使用@Entity注解的primaryKeys属性

- Indices and uniqueness(索引和唯一性)
```
public @interface Index {
  //定义需要添加索引的字段
  String[] value();
  //定义索引的名称
  String name() default "";
  //true-设置唯一键，标识value数组中的索引字段必须是唯一的，不可重复
  boolean unique() default false;
}
```

### 4.1.2 Index索引注解可选参数
```
public @interface Index {
  //定义需要添加索引的字段
  String[] value();
  //定义索引的名称
  String name() default "";
  //true-设置唯一键，标识value数组中的索引字段必须是唯一的，不可重复
  boolean unique() default false;
}
```

### 4.1.3 ForeignKey外键注解可选参数
```
public @interface ForeignKey {
  //引用外键的表的实体
  Class entity();
  //要引用的外键列
  String[] parentColumns();
  //要关联的列
  String[] childColumns();
  //当父类实体(关联的外键表)从数据库中删除时执行的操作
  @Action int onDelete() default NO_ACTION;
  //当父类实体(关联的外键表)更新时执行的操作
  @Action int onUpdate() default NO_ACTION;
  //在事务完成之前，是否应该推迟外键约束
  boolean deferred() default false;
  //给onDelete，onUpdate定义的操作
  int NO_ACTION = 1;
  int RESTRICT = 2;
  int SET_NULL = 3;
  int SET_DEFAULT = 4;
  int CASCADE = 5;
  @IntDef({NO_ACTION, RESTRICT, SET_NULL, SET_DEFAULT, CASCADE})
  @interface Action {
    }
}
```

## 4.2 Dao相关
### 4.2.1 Insert

### 4.2.2 Update

### 4.2.3 Delete

### 4.2.4 Query

