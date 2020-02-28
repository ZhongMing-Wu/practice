#include "factory.h"
int exitChildThread(pNode_t nowNode){
	train_t train;
	bzero(&train,sizeof(train));
	memcpy(train.buf,"exit success",12);
	train.dataLen=strlen(train.buf);
	int ret=send(nowNode->newFd,&train,8+train.dataLen,0);
	ERROR_CHECK(ret,-1,"send");
	return 0;
}
