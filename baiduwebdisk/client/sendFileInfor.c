#include "funcAchieve.h"
int sendFileInfor(int socketFd,char *filename){
	struct stat buf;
	train_t train;
	bzero(&train,sizeof(train));
	int fd=open(filename,O_RDWR);
	fstat(fd,&buf);
	train.dataLen=sizeof(buf.st_size);
	memcpy(train.buf,&buf.st_size,train.dataLen);
	int ret=send(socketFd,&train,8+train.dataLen,0);
	ERROR_CHECK(ret,-1,"send");
	printf("begin send file\n");
	ret=sendfile(socketFd,fd,NULL,buf.st_size);
	printf("send success\nsendsize=%d\n",ret);
	close(fd);
	return 0;
}
