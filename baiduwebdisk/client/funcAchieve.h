#ifndef _FUNCACHIEVE_H_
#define _FUNCACHIEVE_H_
#define _GNU_SOURCE
#define _XOPEN_SOURCE
#include "head.h"
typedef struct{
	int dataLen;
	int operateLen;
	char buf[1000];
}train_t;
typedef struct{
	int flag;
	int dataLen[2];
	int filesize;
	int nLink;
	char privilege[15];
	char name[128];
}fileInfor;
int recvCycle(int ,void*,int);
int sendOperate(int socketFd,char *operate,char *data);
void  md5Sum(char *md5sum,char *filename);
int sendFileInfor(int socketFd,char *filename);
int sendUserInfor(int socketFd,char *myPath);
#endif
