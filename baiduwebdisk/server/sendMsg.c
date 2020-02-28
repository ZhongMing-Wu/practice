#include "factory.h"
int sendMsg(int newFd,char *buf){
	train_t train;
	int ret;
	train.dataLen=strlen(buf);
	train.operateLen=0;
	memcpy(train.buf,buf,strlen(buf));
	ret=send(newFd,&train,8+train.dataLen,0);
	ERROR_CHECK(ret,-1,"send");
	return 0;
}
