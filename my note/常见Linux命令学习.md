##### 重定向相关命令

![1615190411748](E:\研究生\markdownPicture\1615190411748.png)

![1615190472323](E:\研究生\markdownPicture\1615190472323.png)

> 重定向错误和标准输出到指定位置

```shell
# 重定向错误信息
ls -l ~ 2> text.txt
# 同时重定向错误信息和输出信息
ls -l ~ &> text.txt
ls -l ~ > text.txt 2>&1 # 对于这种方式，顺序不能错误
```

> 重定向标准输入到指定位置

```shell
cat < text.txt
```

> 管道线的使用

```shell
ls -l /usr/bin | less
# 可以用作过滤器
ls -l /usr/bin /bin | sort | less
```

> 常用函数说明

```shell
# 排序
sort

# 报道或忽略重复行
uniq	# 忽略
uniq -d # 报道

# 显示文件所包含的行 
wc text.txt

# 打印匹配行，可以通过正则表达式实现很复杂的查找
ls /bin/usr | sort | uniq | grep zip

# 打印文件开头部分/结尾部分
head -n 5 text.txt
tail -n 5 text.txt
ls /usr/bin | tail -n 5
tail -f # 表示实时的浏览文件
```

##### echo 的使用举例

```shell
echo $((2 + 2))
4

echo Front-{A,B,C}-Back
Front-A-Back Front-B-Back Front-C-Back

echo {Z..A}
Z Y X W V U T S R Q P O N M L K J I H G F E D C B A

# 对于双引号使用的说明
ls -l two words.txt # 看作两个文本
ls -l "two words.txt"
echo "$USER $((2  +2)) $(cal)" # 在双引号中，参数展开，算术表达式展开和命令替换依旧有效

# 对于带有双引号的命令，不会忽略空格和换行符
echo $(cal)
March 2021 Su Mo Tu We Th Fr Sa 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
echo "$(cal)"
March 2021     
Su Mo Tu We Th Fr Sa
    1  2  3  4  5  6
 7  8  9 10 11 12 13
14 15 16 17 18 19 20
21 22 23 24 25 26 27
28 29 30 31

# 对于使用了单引号的文本，echo 不会展开
```

##### 权限相关命令

```shell
# 更改文件模式 4：读 2：写 1：可执行
chmod 600 text.txt # 使用三个八进制数分别代表用户的权限，组的权限和其他人的权限

# 查看新建文件的默认权限
umask

# 更改当前身份
su 用户(不说明，就是更改为 root)

# 不更改当前身份，可以暂时以其他身份执行命令
sudo # 以 root 权限执行命令，但是不登录 root

# 修改某个文件的所有者和所属用户组
sudo chown 新的所有者:新的所属用户组 text.txt # chown 只有超级用户才能使用，后面跟新的所有者名称，如果: 后面没有明确指示用户组，表示将所属用户组也改为新的所有者所在的用户组

sudo chown :music /usr/local/share/Music # 只修改所属用户组

# 修改密码
passwd
```

##### 进程相关的命令

```shell
# ps 不会显示很多进程信息，只是列出与当前终端会话相关的进程。
ps
# 加上 "x" 选项（注意没有开头的 "-" 字符），告诉 ps 命令，展示所有进程，不管它们由什么 终端（如果有的话）控制。
ps x 
# ps aux/-ef 可以查看进程的详细信息
ps aux/-ef
# top 动态显示程序的信息
top

# 根据进程名称查看 PID
ps -ef | grep 进程名称
# 根据 PID 查看端口号
netstat -nltp | grep PID
# 根据端口号查看 PID
lsof -i:端口号
```

##### shell 的登录类型

> 一个是登录 shell 会话，另一个是非登录 shell 会话。
>
> 登录 shell 会话会提示用户输入用户名和密码；例如，我们启动一个虚拟控制台会话。当我们在 GUI 模式下 运行终端会话时，非登录 shell 会话会出现。

##### 登录 shell 时，会读取的配置文件

|      文件       |                             内容                             |
| :-------------: | :----------------------------------------------------------: |
|  /etc/profile   |                应用于所有用户的全局配置脚本。                |
| ~/.bash_profile | 用户私人的启动文件。可以用来扩展或重写全局配置脚本中的设置。 |
|  ~/.bash_login  | 如果文件 ~/.bash_profile 没有找到，bash 会尝试读取这个脚本。 |
|   ~/.profile    | 如果文件 ~/.bash_profile 或文件 ~/.bash_login 都没有找到，bash 会试图读取这个文件。 这是基于 Debian 发行版的默认设置，比方说 Ubuntu。 |

##### 非登录 shell  时，会读取的配置文件

|       文件       |                             内容                             |
| :--------------: | :----------------------------------------------------------: |
| /etc/bash.bashrc |                应用于所有用户的全局配置文件。                |
|    ~/.bashrc     | 用户私有的启动文件。可以用来扩展或重写全局配置脚本中的设置。 |

##### xargs 的使用

```shell
# linux中很多命令不接受标准输入作为参数，这时就需要通过 xargs 来解决，xargs 命令的作用，是将标准输入转为命令行参数。
# 错误的使用
echo "hello world" | echo
# 正确的用法
echo "hello world" | xargs echo
```

##### 查看CPU的情况

```shell
cat /proc/cpuinfo
```

##### 查看内存的使用情况

```shell
top // 查看每个进程的详细信息，包括内存使用情况

free    用KB为单位展示数据
free -m    用MB为单位展示数据
free -h     用GB为单位展示数据

cat /proc/meminfo

vmstate - s
```

