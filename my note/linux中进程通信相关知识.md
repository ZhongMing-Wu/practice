##### 管道

> 无名管道，再关闭管道的时候，应该先关闭写，再关闭读。先关闭写，读管道只会返回0，但是不会报错，但是先关闭读，会导致写端返回一个 SIGPIPE 信号，如果不处理该信号，会导致进程退出。
>
> 特点：半双工通信的方式，不能全双工通信。缓冲区太小。

```c
int main() {
    int fds[2];
    pipe(fds);
    char szBuf[10] = {0};
    if(fork() == 0) {
        close(fds[1]);
    	if(read(fds[0], sizeof(szBuf)) > 0) {
            xxx;
        }
    } else {
        close(fds[0]);
        write(fds[1], "hello", 6);
        wait(NULL);
    }
}
```

> 命名管道（FIFO）

```c
// 创建管道
int mkfifo(const char *pathname, mode_t mode);
// 删除管道
int unlink(const char *pathname);
// 创建后，可以通过 open 打开管道 先执行 mkfifo(MyFifo.pip) 创建
int fdFifo = open("MyFifo.pip",O_WRONLY); // 写的方式打开
int fdFifo = open("MyFifo.pip",O_RDONLY); // 读的方式打开
// 调用 read 和 write 进行读写
```

##### 共享内存

> 特点：
>
> 1. 不需要经过内核，传输速度较快。

```c
// 用于创建一个关键字，用于关联一个共享内存
key_t ftok(const char *pathname, int proj_id);
// 用于获取共享内存,返回一个共享内存标识号
int shmget((key_t key, int size, int shmflg);
// 获取共享内存的首地址,shmid 是共享内存段的标识
void *shmat(int shmid, const void *shmaddr, int shmflg);
// 使某个进程不能使用某一个共享内存
int shmdt(const void *shmaddr);
// 删除某个共享内存
int shmctl(int shmid, int cmd, struct shmid_ds *buf);
```

##### 消息队列

> 特点：
>
> 1. 单条消息的长度是有限的。
> 2. 所有队列能发送的消息大小也是有限的。
> 3. 需要使用到系统内核，复制有开销。

```c
// 创建一个消息队列，key 用于唯一标识一个消息队列。执行成功返回一个唯一的消息队列标识符 msqid
int msgget(key_t key, int msgflg);
// 接收和发送消息
int msgsnd(int msqid, const void *msgp, size_t msgsz, int msgflg);
ssize_t msgrcv(int msqid, void *msgp, size_t msgsz, long msgtyp, int msgflg);
// 用来删除消息队列
int msgctl(int msqid, int cmd, struct msqid_ds *buf);
```

##### 信号量

> 特点：不能进行大数量量的通信，一般用于多进程或多线程之间的同步。

```c
// 创建一个信号量，创建成功返回一个信号量标识符
int semget(key_t key,int nsems,int flag);
// 用于改变信号量的状态
int semop(int semid,struct sembuf *sops,size_t num_sops);
```

