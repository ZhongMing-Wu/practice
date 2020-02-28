#include "factory.h"
int freeParticularNewFd(pQueue_t que,timeArr_t *timeArr,int location,int epfd,pNode_t findNode){
	close(findNode->newFd);
	deleteFd(epfd,findNode->newFd);
	if(timeArr[location].next==findNode->toArrNode){//如果删除的是头部
		timeArr[location].next=findNode->toArrNode->next;
		if(timeArr[location].next!=NULL){
			timeArr[location].next->pre=NULL;
		}
	}else{
		findNode->toArrNode->pre->next=findNode->toArrNode->next;
		if(findNode->toArrNode->next!=NULL){
			findNode->toArrNode->next->pre=findNode->toArrNode->pre;
		}
	}
	free(findNode->toArrNode);
	beginExit(que,findNode);
	que->queSize-=1;
	return 0;
}
