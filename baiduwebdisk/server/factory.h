#ifndef _FACTORY_H_
#define _FACTORY_H_
#define _GNU_SOURCE
#include "work_que.h"

typedef struct factory{
	pthreadQue_t  *que;
	pthread_t *threadId;
	int threadNum;
	pthread_cond_t cond;
	short startFlag;
}factory_t,*pFactory_t;
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
	char privilege_name[128]; 
}fileInfor;
typedef struct{
	char name[25];
	char operation[256];
}record;
void factoryInit(pFactory_t fac,pthreadQue_t *que,int num);
void factoryStart(pFactory_t fac);
int tcpInit(int *sfd,char *ip,char *port);
void epollAdd(int,int);
int recvCycle(int sfd,void *buf,int recvLen);
int removeDir(const char *dir);
int getFileInfor(pNode_t user,fileInfor *fileInfor);
void cdDir(pNode_t user);
void lsDirent(pNode_t user);
int putsFile(pNode_t user);
int getsFile(pthreadQueueNode_t *user);
int removeFile(pthreadQueueNode_t *user);
int createDirent(pNode_t user);
int sendMsg(int newFd,char *buf);
int deleteData(char *operate);
int update(char *operate);
int query(char *operate);
int insert(char *operate);
void int_to_char(int linkNum,char *charLinkNum);
int sendFileInfor(int newFd,char *filename);
void getSalt(char *salt);
int query_Data(char *operate,char *data);
void write_to_log(record writeToLog);
int registerAndLog(pNode_t user,int flag);
int exitChildThread(pNode_t nowNewFd);
int beginExit(pQueue_t que,pNode_t exitNode);
int deleteFd(int epfd,int newFd);
int updateTheLocationInTimeArr(arrNode_t *updateNode,int location,timeArr_t *timeArr,int nowTime);
int freeNewFd(pQueue_t que,timeArr_t *timeArr,int nowTime,int epfd);
int freeParticularNewFd(pQueue_t que,timeArr_t *timeArr,int location,int epfd,pNode_t findNode);
#endif


