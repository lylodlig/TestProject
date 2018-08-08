# 1.使用步骤
### 1.创建接收服务器返回数据的类
```
class Reception {
}
```

### 2.创建用于描述网络请求的接口
```
interface ApiService {

    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    fun getCall(): Call<Reception>
}
```

### 3.创建Retrofit实例
```
Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
```

### 4.创建网络请求接口实例
```
val request = retrofit.create(ApiService::class.java)
        request.getCall()
```

### 5.发送网络请求（异步/同步）
```
//异步执行
        call.enqueue(object : Callback<Reception> {
            override fun onFailure(call: Call<Reception>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Reception>?, response: Response<Reception>?) {

            }
        })
        //同步执行
        val response = call.execute()
```

# 2.注解
## 2.1 网络请求方法
@GET、@POST、@PUT、@DELETE、@HEAD很明显对应HTTP的请求方式

- @HTTP
    替换@GET、@POST、@PUT、@DELETE、@HEAD注解的作用 及 更多功能拓展
```
/**
     * method：网络请求的方法（区分大小写）
     * path：网络请求地址路径
     * hasBody：是否有请求体
     */
    @HTTP(method = "GET", path = "blog/{id}", hasBody = false)
    Call<ResponseBody> getCall(@Path("id") int id);
    // {id} 表示是一个变量
    // method 的值 retrofit 不会做处理，所以要自行保证准确
```

## 2.2 标记
- @FormUrlEncoded
    表示发送form-encoded的数据,每个键值对需要用@Filed来注解键名，随后的对象需要提供值。


- @Multipart
    表示发送form-encoded的数据（适用于 有文件 上传的场景）,每个键值对需要用@Part来注解键名，随后的对象需要提供值。

## 2.3 网络请求参数
- @Header & @Headers
    添加请求头 &添加不固定的请求头
```
// @Header
@GET("user")
Call<User> getUser(@Header("Authorization") String authorization)

// @Headers
@Headers("Authorization: authorization")
@GET("user")
Call<User> getUser()

// 以上的效果是一致的。
// 区别在于使用场景和使用方式
// 1. 使用场景：@Header用于添加不固定的请求头，@Headers用于添加固定的请求头
// 2. 使用方式：@Header作用于方法的参数；@Headers作用于方法
```

- @Body
    以 Post方式 传递 自定义数据类型 给服务器,如果提交的是一个Map，那么作用相当于 @Field,不过Map要经过 FormBody.Builder 类处理成为符合 Okhttp 格式的表单，如：
```
FormBody.Builder builder = new FormBody.Builder();
builder.add("key","value");
```

- @Field & @FieldMap
    发送 Post请求 时提交请求的表单字段,与 @FormUrlEncoded 注解配合使用
```
public interface GetRequest_Interface {
        /**
         *表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
         * <code>Field("username")</code> 表示将后面的 <code>String name</code> 中name的取值作为 username 的值
         */
        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);

/**
         * Map的key作为表单的键
         */
        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncoded2(@FieldMap Map<String, Object> map);

}

// 具体使用
         // @Field
        Call<ResponseBody> call1 = service.testFormUrlEncoded1("Carson", 24);

        // @FieldMap
        // 实现的效果与上面相同，但要传入Map
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Carson");
        map.put("age", 24);
        Call<ResponseBody> call2 = service.testFormUrlEncoded2(map);
```

- @Part & @PartMap
    发送 Post请求 时提交请求的表单字段，与@Field的区别：功能相同，但携带的参数类型更加丰富，包括数据流，所以适用于 有文件上传 的场景

```
public interface GetRequest_Interface {

          /**
         * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
         * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
         */
        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);

        /**
         * PartMap 注解支持一个Map作为参数，支持 {@link RequestBody } 类型，
         * 如果有其它的类型，会被{@link retrofit2.Converter}转换，如后面会介绍的 使用{@link com.google.gson.Gson} 的 {@link retrofit2.converter.gson.GsonRequestBodyConverter}
         * 所以{@link MultipartBody.Part} 就不适用了,所以文件只能用<b> @Part MultipartBody.Part </b>
         */
        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload2(@PartMap Map<String, RequestBody> args, @Part MultipartBody.Part file);

        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload3(@PartMap Map<String, RequestBody> args);
}

// 具体使用
 MediaType textType = MediaType.parse("text/plain");
        RequestBody name = RequestBody.create(textType, "Carson");
        RequestBody age = RequestBody.create(textType, "24");
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), "这里是模拟文件的内容");

        // @Part
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
        Call<ResponseBody> call3 = service.testFileUpload1(name, age, filePart);
        ResponseBodyPrinter.printResponseBody(call3);

        // @PartMap
        // 实现和上面同样的效果
        Map<String, RequestBody> fileUpload2Args = new HashMap<>();
        fileUpload2Args.put("name", name);
        fileUpload2Args.put("age", age);
        //这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头中)，但上面的 filePart 有
        //fileUpload2Args.put("file", file);
        Call<ResponseBody> call4 = service.testFileUpload2(fileUpload2Args, filePart); //单独处理文件
        ResponseBodyPrinter.printResponseBody(call4);
}
```

- @Query和@QueryMap
    用于 @GET 方法的查询参数（Query = Url 中 ‘?’ 后面的 key-value）

- @Path
    URL地址的缺省值
 ```
 public interface GetRequest_Interface {

         @GET("users/{user}/repos")
         Call<ResponseBody>  getBlog（@Path("user") String user ）;
         // 访问的API是：https://api.github.com/users/{user}/repos
         // 在发起请求时， {user} 会被替换为方法的第一个参数 user（被@Path注解作用）
     }
 ```

 - @Url
     直接传入一个请求的 URL变量 用于URL设置
```
public interface GetRequest_Interface {

        @GET
        Call<ResponseBody> testUrlAndQuery(@Url String url, @Query("showAll") boolean showAll);
       // 当有URL注解时，@GET传入的URL就可以省略
       // 当GET、POST...HTTP等方法中没有设置Url时，则必须使用 {@link Url}提供

}
```

# 3.源码
## 3.1 大致流程
- 通过解析 网络请求接口的注解 配置 网络请求参数
- 通过 动态代理 生成 网络请求对象
- 通过 网络请求适配器 将 网络请求对象 进行平台适配
- 通过 网络请求执行器 发送网络请求
- 通过 数据转换器 解析服务器返回的数据
- 通过 回调执行器 切换线程（子线程 ->>主线程）
- 用户在主线程处理返回结果

## 3.2 创建Retrofit实例
```
val retrofit = Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
```

### 3.2.1 步骤1
下面是Retrofit的构造器  
```
Retrofit(okhttp3.Call.Factory callFactory, HttpUrl baseUrl,
      List<Converter.Factory> converterFactories, List<CallAdapter.Factory> callAdapterFactories,
      @Nullable Executor callbackExecutor, boolean validateEagerly) {
    this.callFactory = callFactory;
    this.baseUrl = baseUrl;
    this.converterFactories = converterFactories; // Copy+unmodifiable at call site.
    this.callAdapterFactories = callAdapterFactories; // Copy+unmodifiable at call site.
    this.callbackExecutor = callbackExecutor;
    this.validateEagerly = validateEagerly;
  }
```
分别配置了如下设置：  
- callFactory：网络请求的工厂  

- baseUrl：网络请求的url地址

- converterFactories：数据转换器工厂的集合

- adapterFactories：网络请求适配器工厂的集合

- callbackExecutor：回调方法执行器

### 3.2.2 CallAdapter
网络请求执行器（Call）的适配器  
> Call在Retrofit里默认是OkHttpCall  
  在Retrofit中提供了四种CallAdapterFactory： ExecutorCallAdapterFactory（默认）、GuavaCallAdapterFactory、Java8CallAdapterFactory、RxJavaCallAdapterFactory

### 3.2.3 构建Retrofit
可以看出Retrofit实例的创建是采用Builder模式来实现，Builder内部类封装了Retrofit所需要的参数，最后通过build()方法传入创建Retrofit、

### 3.2.4 总结
Retrofit实例创建是通过Builder模式实现的，主要是配置了baseUrl，网络请求工厂CallFactory（默认是OkHttpCall），网络请求适配器AdapterFactory（用于把Call转化成适合不同平台的执行形式，比如我们常用的
RxJava），converter用于转化数据为我们想要的格式，callbackExecutor回调方法执行器，用于切换线程

## 3.3 创建请求接口实例
```
val request = retrofit.create(ApiService::class.java)  
val call = request.getCall()
```

```
public <T> T create(final Class<T> service) {
    Utils.validateServiceInterface(service);
    if (validateEagerly) {
      eagerlyValidateMethods(service);
    }
    //创建网络请求接口的代理对象，通过动态代理创建实例返回
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
        new InvocationHandler() {
          private final Platform platform = Platform.get();

          @Override public Object invoke(Object proxy, Method method, @Nullable Object[] args)
              throws Throwable {
            // If the method is a method from Object then defer to normal invocation.
            if (method.getDeclaringClass() == Object.class) {
              return method.invoke(this, args);
            }
            if (platform.isDefaultMethod(method)) {
              return platform.invokeDefaultMethod(method, service, proxy, args);
            }
            ServiceMethod<Object, Object> serviceMethod =
                (ServiceMethod<Object, Object>) loadServiceMethod(method);
            OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);
            return serviceMethod.adapt(okHttpCall);
          }
        });
  }
```
