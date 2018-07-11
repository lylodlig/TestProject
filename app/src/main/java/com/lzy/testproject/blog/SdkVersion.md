compileSdkVersion、minSdkVersion、targetSdkVersion

# compileSdkVersion
告诉 Gradle 用哪个版本的 Android SDK 编译你的应用，使用任何新添加的 API 就需要使用对应 Level 的 Android SDK。
修改 compileSdkVersion 不会改变运行时的行为，当修改了 compileSdkVersion 时可能会出现新的编译警告、编译错误，
强烈推荐总是使用最新的 SDK 进行编译，避免弃用的 API。
compileSdkVersion 不会被包含到 APK 中：它纯粹只是在编译的时候使用。

# minSdkVersion
是应用可以运行的最低 API 要求，也是 Google Play 商店用来判断用户设备是否可以安装某个应用的标志之一。
在开发时 minSdkVersion 也起到一个重要角色，lint 默认会在项目中运行，它在我们使用了高于 minSdkVersion 的 API
 时会警告我们，避免调用低版本不存在的 API 而导致在低版本设备上运行时的问题。如果只在较高版本的系统上才使用某些
 API，通常使用运行时检查系统版本的方式解决。此外要记住，我们所使用的库（如 Support Library 或 Google Play services）
 可能有他们自己的 minSdkVersion，我们应用设置的 minSdkVersion  要必需大于等于这些库的 minSdkVersion。在少数情况下你可能
 仍然想用一个比你应用的 minSdkVersion 还高的库，这时可以使用 tools:overrideLibrary 标记配合代码调用处主动版本判断操作来实现。

# targetSdkVersion
是 Android 提供向前兼容的主要依据，在应用的 targetSdkVersion  没有更新之前系统不会应用最新的行为变化，
这允许你在适应新的行为变化之前就可以使用新的 API （因为你已经更新了 compileSdkVersion）

minSdkVersion 和 targetSdkVersion 与 compileSdkVersion 的另一个不同之处是它们会被包含进最终
APK 的 AndroidManifest.xml 文件中，他们的大小关系应该是 minSdkVersion <= targetSdkVersion <= compileSdkVersion，
而推荐的大小关系是 minSdkVersion(lowest possible) <= targetSdkVersion == compileSdkVersion(latest SDK)。