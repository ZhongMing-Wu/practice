#ifndef _WORK_QUE_H
#define _WORK_QUE_H
#include "head.h"
struct arrNode;
typedef struct node{
	int newFd;
	struct node *next;
	struct node *pre;
	char path[256];
	int level;
	char name[256];
	char operate[20];
	char data[128];
	struct arrNode *toArrNode;
}node_t,*pNode_t;
typedef struct queue{
	struct node *head,*tail;
	int queCapacity;
	int queSize;
	pthread_mutex_t mutex;
}queue_t,*pQueue_t;
typedef struct arrNode{
	int newFd;
	struct arrNode *pre;
	struct arrNode *next;
	int mark;
	pNode_t toNewFdsNode;
}arrNode_t;
typedef struct timeRecordArr{
	arrNode_t *next;
	arrNode_t *tail;
}timeArr_t;
typedef struct pthreadQueueNode{
	char path[256];
	char name[256];
	int newFd;
	char operate[20];
	char data[128];
	struct pthreadQueueNode *next;
	arrNode_t *toArrNode;
    timeArr_t *timeArr;
	int *time;
	int *locationArr;
	int *mark;
}pthreadQueueNode_t;
typedef struct pthreadQue{
	pthreadQueueNode_t *head,*tail;
	int queCapacity;
	int queSize;
	pthread_mutex_t mutex;
}pthreadQue_t;
void queInit(pQueue_t que,int qCapacity);
void queInsert(pQueue_t que,pNode_t newNode);
int queGet(pthreadQue_t *que,pthreadQueueNode_t **getNode);
void pthreadQueInsert(pthreadQue_t *que,pthreadQueueNode_t *newNode);
void pthreadQueInit(pthreadQue_t *que,int qCapacity);
#endif


