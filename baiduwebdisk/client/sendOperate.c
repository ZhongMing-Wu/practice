#include "funcAchieve.h"
int sendOperate(int socketFd,char *operate,char *data){
	train_t train;
	scanf("%s",data);
	train.dataLen=strlen(data);
	train.operateLen=strlen(operate);
	sprintf(train.buf,"%s%s",operate,data);
	int ret=send(socketFd,&train,train.operateLen+train.dataLen+8,0);
	ERROR_CHECK(ret,-1,"send");
	return 0;
}
