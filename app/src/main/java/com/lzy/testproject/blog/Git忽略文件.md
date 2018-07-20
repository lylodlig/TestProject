# 未提交到git远程仓库的文件
直接.gitignore文件中配置就可以了，如下所示：
```
# 此为注释 – 将被 Git 忽略
*.a # 忽略所有 .a 结尾的文件
!lib.a              # 但 lib.a 除外
/TODO               # 仅仅忽略项目根目录下的 TODO 文件,不包括 subdir/TODO
build/              # 忽略 build/ 目录下的所有文件
doc/*.txt           # 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
```

# 已提交到git远程仓库的文件
有时在.gitignore中添加忽略文件没有用，这是因为这些文件已经提交到远程仓库，所有无效。此时需要清除对此文件的追踪
1. git rm –cached xxx 或者git rm -r –cached 文件夹名称取消追踪
2. 在.gitignored中添加需要过滤的文件
3. commit, push提交.gitignore

# 仅在自己本地忽略
如果这个文件只是想在自己本地给忽略，比如大家数据库配置都是localhost而自己配成虚拟机ip，
此时只是自己本地忽略，而不需要大家一起忽略。

拿logback.xml文件为例
```
git update-index --assume-unchanged xxx/xx/x/logback.xml
```
指定忽略文件后git status 这个文件就不会再显示了。如果以后需要提交了就取消忽略
```
git update-index --no-assume-unchanged xxx/xx/xlogback.xml
```
