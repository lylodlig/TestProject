# 1. 简介
反射是Java语言中一种动态的访问能力。
- 作用：可以动态的获取类的信息，调用类的方法

- 优点：灵活性高。因为反射属于动态编译，即只有到运行时才动态创建 &获取对象实例。

- 缺点：执行效率低

# 2. 使用步骤
在使用Java反射机制时，主要步骤包括：
1. 获取 目标类型的Class对象
2. 通过 Class 对象分别获取Constructor类对象、Method类对象 & Field 类对象
3. 通过 Constructor类对象、Method类对象 & Field类对象分别获取类的构造函数、方法&属性的具体信息，并进行后续操作

**步骤1：获取 目标类型的Class对象**
```
<-- 方式1：Object.getClass() -->
    // Object类中的getClass()返回一个Class类型的实例
    Boolean carson = true;
    Class<?> classType = carson.getClass();
    System.out.println(classType);
    // 输出结果：class java.lang.Boolean

<-- 方式2：static method Class.forName   -->
    Class<?> classType = Class.forName("java.lang.Boolean");
    // 使用时应提供异常处理器
    System.out.println(classType);
    // 输出结果：class java.lang.Boolean
```

**步骤2：通过 Class 对象分别获取Constructor类对象、Method类对象 & Field 类对象**
```
// 即以下方法都属于`Class` 类的方法。

<-- 1. 获取类的构造函数（传入构造函数的参数类型）->>
  // a. 获取指定的构造函数 （公共 / 继承）
  Constructor<T> getConstructor(Class<?>... parameterTypes)
  // b. 获取所有的构造函数（公共 / 继承）
  Constructor<?>[] getConstructors();
  // c. 获取指定的构造函数 （ 不包括继承）
  Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
  // d. 获取所有的构造函数（ 不包括继承）
  Constructor<?>[] getDeclaredConstructors();
// 最终都是获得一个Constructor类对象

// 特别注意：
  // 1. 不带 "Declared"的方法支持取出包括继承、公有（Public） & 不包括有（Private）的构造函数
  // 2. 带 "Declared"的方法是支持取出包括公共（Public）、保护（Protected）、默认（包）访问和私有（Private）的构造方法，但不包括继承的构造函数
  // 下面同理

<--  2. 获取类的属性（传入属性名） -->
  // a. 获取指定的属性（公共 / 继承）
   Field getField(String name) ;
  // b. 获取所有的属性（公共 / 继承）
   Field[] getFields() ;
  // c. 获取指定的所有属性 （不包括继承）
   Field getDeclaredField(String name) ；
  // d. 获取所有的所有属性 （不包括继承）
   Field[] getDeclaredFields() ；
// 最终都是获得一个Field类对象

<-- 3. 获取类的方法（传入方法名 & 参数类型）-->
  // a. 获取指定的方法（公共 / 继承）
    Method getMethod(String name, Class<?>... parameterTypes) ；
  // b. 获取所有的方法（公共 / 继承）
   Method[] getMethods() ；
  // c. 获取指定的方法 （ 不包括继承）
   Method getDeclaredMethod(String name, Class<?>... parameterTypes) ；
  // d. 获取所有的方法（ 不包括继承）
   Method[] getDeclaredMethods() ；
// 最终都是获得一个Method类对象

<-- 4. Class类的其他常用方法 -->
getSuperclass();
// 返回父类

String getName();
// 作用：返回完整的类名（含包名，如java.lang.String ）

Object newInstance();
// 作用：快速地创建一个类的实例
// 具体过程：调用默认构造器（若该类无默认构造器，则抛出异常
// 注：若需要为构造器提供参数需使用java.lang.reflect.Constructor中的newInstance（）
```

**步骤3：通过 Constructor类对象、Method类对象 & Field类对象分别获取类的构造函数、方法 & 属性的具体信息 & 进行操作**
```
// 即以下方法都分别属于`Constructor`类、`Method`类 & `Field`类的方法。

<-- 1. 通过Constructor 类对象获取类构造函数信息 -->
  String getName()；// 获取构造器名
  Class getDeclaringClass()；// 获取一个用于描述类中定义的构造器的Class对象
  int getModifiers()；// 返回整型数值，用不同的位开关描述访问修饰符的使用状况
  Class[] getExceptionTypes()；// 获取描述方法抛出的异常类型的Class对象数组
  Class[] getParameterTypes()；// 获取一个用于描述参数类型的Class对象数组

<-- 2. 通过Field类对象获取类属性信息 -->
  String getName()；// 返回属性的名称
  Class getDeclaringClass()； // 获取属性类型的Class类型对象
  Class getType()；// 获取属性类型的Class类型对象
  int getModifiers()； // 返回整型数值，用不同的位开关描述访问修饰符的使用状况
  Object get(Object obj) ；// 返回指定对象上 此属性的值
  void set(Object obj, Object value) // 设置 指定对象上此属性的值为value

<-- 3. 通过Method 类对象获取类方法信息 -->
  String getName()；// 获取方法名
  Class getDeclaringClass()；// 获取方法的Class对象
  int getModifiers()；// 返回整型数值，用不同的位开关描述访问修饰符的使用状况
  Class[] getExceptionTypes()；// 获取用于描述方法抛出的异常类型的Class对象数组
  Class[] getParameterTypes()；// 获取一个用于描述参数类型的Class对象数组

<--额外：java.lang.reflect.Modifier类 -->
// 作用：获取访问修饰符

static String toString(int modifiers)
// 获取对应modifiers位设置的修饰符的字符串表示

static boolean isXXX(int modifiers)
// 检测方法名中对应的修饰符在modifiers中的值
```

# 3. 实例应用讲解
## 3.1 基础应用讲解
**实例1：利用反射获取类的属性 & 赋值**
```
<-- 测试类定义-->
public class Student {

    public Student() {
        System.out.println("创建了一个Student实例");
    }
    private String name;
}

<-- 利用反射获取属性 & 赋值 -->
        // 1. 获取Student类的Class对象
        Class studentClass = Student.class;

        // 2. 通过Class对象创建Student类的对象
        Object mStudent = studentClass.newInstance();

        // 3. 通过Class对象获取Student类的name属性
        Field f = studentClass.getDeclaredField("name");

        // 4. 设置私有访问权限
        f.setAccessible(true);

        // 5. 对新创建的Student对象设置name值
        f.set(mStudent, "sdfsdf");

        // 6. 获取新创建Student对象的的name属性 & 输出
        System.out.println(f.get(mStudent));
```

**实例2：利用反射调用类的构造函数**
```
public class Student {

    // 无参构造函数
    public Student() {
        System.out.println("调用了无参构造函数");
    }

    // 有参构造函数
    public Student(String str) {
        System.out.println("调用了有参构造函数");
    }

    private String name;
}

<-- 利用反射调用构造函数 -->
        // 1. 获取Student类的Class对象
        Class studentClass studentClass = Student.class;

        // 2.1 通过Class对象获取Constructor类对象，从而调用无参构造方法
        // 注：构造函数的调用实际上是在newInstance()，而不是在getConstructor()中调用
        Object mObj1 = studentClass.getConstructor().newInstance();

        // 2.2 通过Class对象获取Constructor类对象（传入参数类型），从而调用有参构造方法
        Object mObj2 = studentClass.getConstructor(String.class).newInstance("Carson");
```

**实例3：利用反射调用类对象的方法**
```
public class Student {

    public Student() {
        System.out.println("创建了一个Student实例");
    }

    // 无参数方法
    public void setName1 (){
        System.out.println("调用了无参方法：setName1（）");
    }

    // 有参数方法
    public void setName2 (String str){
        System.out.println("调用了有参方法setName2（String str）:" + str);
    }
}

<-- 利用反射调用方法 -->
        // 1. 获取Student类的Class对象
        Class studentClass = Student.class;

        // 2. 通过Class对象创建Student类的对象
        Object  mStudent = studentClass.newInstance();

        // 3.1 通过Class对象获取方法setName1（）的Method对象:需传入方法名
        // 因为该方法 = 无参，所以不需要传入参数
        Method  msetName1 = studentClass.getMethod("setName1");

        // 通过Method对象调用setName1（）：需传入创建的实例
        msetName1.invoke(mStudent);

        // 3.2 通过Class对象获取方法setName2（）的Method对象:需传入方法名 & 参数类型
        Method msetName2 = studentClass.getMethod("setName2",String.class);

       // 通过Method对象调用setName2（）：需传入创建的实例 & 参数值
        msetName2.invoke(mStudent,"Carson_Ho");
```

## 3.2 常见需求场景讲解--工厂模式优化
在简单工厂模式中，每增加一个接口的子类，必须修改工厂类的逻辑，这样可以使用反射机制来优化：通过 传入子类名称 & 动态创建子类实
例，从而使得在增加产品接口子类的情况下，也不需要修改工厂类的逻辑
**步骤1：创建抽象产品类的公共接口**
```
abstract class Product{
    public abstract void show();
}
```

**步骤2. 创建具体产品类（继承抽象产品类），定义生产的具体产品**
```
<-- 具体产品类A：ProductA.java -->
public class  ProductA extends  Product{

    @Override
    public void show() {
        System.out.println("生产出了产品A");
    }
}

<-- 具体产品类B：ProductB.java -->
public class  ProductB extends  Product{

    @Override
    public void show() {
        System.out.println("生产出了产品B");
    }
}
```

**步骤3. 创建工厂类**
```
public class Factory {

    // 定义方法：通过反射动态创建产品类实例
    public static Product getInstance(String ClassName) {

        Product concreteProduct = null;

        try {

            // 1. 根据 传入的产品类名 获取 产品类类型的Class对象
            Class product_Class = Class.forName(ClassName);
            // 2. 通过Class对象动态创建该产品类的实例
            concreteProduct = (Product) product_Class.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. 返回该产品类实例
        return concreteProduct;
    }

}
```

**步骤4：创建属性配置文件**
  Product.properties
```
// 写入抽象产品接口类的子类信息（完整类名）
ProductA = scut.carson_ho.reflection_factory.ProductA
ProductB = scut.carson_ho.reflection_factory.ProductB
```

**步骤5：将属性配置文件 放到src/main/assets文件夹中**

**步骤6：在动态创建产品类对象时，动态读取属性配置文件从而获取子类完整类名**
```
public class TestReflect {
    public static void main(String[] args) throws Exception {

        // 1. 读取属性配置文件
        Properties pro = new Properties() ;
        pro.load(this.getAssets().open("Product.properties"));

        // 2. 获取属性配置文件中的产品类名
        String Classname = pro.getProperty("ProductA");

        // 3. 动态生成产品类实例
        Product concreteProduct = Factory.getInstance(Classname);

        // 4. 调用该产品类对象的方法，从而生产产品
        concreteProduct.show();

}
```

