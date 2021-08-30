#include "work_que.h"
void queInit(pQueue_t que,int qCapacity){
	bzero(que,sizeof(queue_t));
	que->queCapacity=qCapacity;
	pthread_mutex_init(&que->mutex,NULL);
	return;
}

void pthreadQueInit(pthreadQue_t *que,int qCapacity){
	bzero(que,sizeof(queue_t));
	que->queCapacity=qCapacity;
	pthread_mutex_init(&que->mutex,NULL);
	return;
}
void queInsert(pQueue_t que,pNode_t newNode){
	if(que->head==NULL){
		que->head=que->tail=newNode;
		que->queSize++;
		return;
	}
	que->tail->next=newNode;
	newNode->pre=que->tail;
	que->tail=newNode;
	que->queSize++;
}

int queGet(pthreadQue_t *que,pthreadQueueNode_t **getNode){
	if(que->head==NULL){
		return -1;
	}
	*getNode=que->head;
	que->head=que->head->next;
	if(que->head==NULL){
		que->tail=NULL;
	}
	que->queSize--;
	return 0;
}

void pthreadQueInsert(pthreadQue_t *que,pthreadQueueNode_t *newNode){
	if(que->head==NULL){
		que->head=que->tail=newNode;
		que->queSize++;
		return;
	}
	que->tail->next=newNode;
	que->tail=newNode;
	que->queSize++;
}

