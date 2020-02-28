#include "factory.h"
int sendFileInfor(int newFd,char *filename){
	struct stat buf;
	train_t train;
	int dataLen,operateLen;
	off_t filesize,sendFilesize;
	printf("begin recvCycle filesize\n");
	recvCycle(newFd,&dataLen,4);
	recvCycle(newFd,&operateLen,4);
	recvCycle(newFd,&filesize,dataLen);
	bzero(&train,sizeof(train));
	int fd=open(filename,O_RDWR);
	ERROR_CHECK(fd,-1,"open");
	fstat(fd,&buf);
	sendFilesize=buf.st_size-filesize;
	bzero(&train,sizeof(train));
	train.dataLen=sizeof(sendFilesize);
	memcpy(train.buf,&sendFilesize,train.dataLen);
	int ret=send(newFd,&train,8+train.dataLen,0);
	ERROR_CHECK(ret,-1,"send");
	if(sendFilesize==0){  //说明下载的文件已经在客户端存在，不用下载
		printf("I am in sendFileInfor the sendFilesize=0\n");
		close(fd);
		return 0;
	}
	off_t off=lseek(fd,filesize,SEEK_SET);
	ret=sendfile(newFd,fd,&off,sendFilesize);
	close(fd);
	return 0;
}
