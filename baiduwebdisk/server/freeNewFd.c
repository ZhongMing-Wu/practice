#include "factory.h"
int freeNewFd(pQueue_t que,timeArr_t *timeArr,int nowTime,int epfd){
	arrNode_t *tempNode=timeArr[nowTime].next;
	arrNode_t *preNode=NULL;
	if(tempNode==NULL){
		return 1;
	}
	while(tempNode!=NULL){
		if(tempNode->mark==0){
			close(tempNode->newFd);
			deleteFd(epfd,tempNode->newFd);
			if(timeArr[nowTime].next==tempNode){  //删除的是第一个节点
				timeArr[nowTime].next=tempNode->next;
				if(timeArr[nowTime].next!=NULL){
					timeArr[nowTime].next->pre=NULL;
				}else{
					timeArr[nowTime].tail=NULL;
				}
			}else{//删除的是中间节点
				preNode->next=tempNode->next;
				if(tempNode->next!=NULL){
					tempNode->next->pre=preNode;
				}
			}
			beginExit(que,tempNode->toNewFdsNode);
			que->queSize-=1;
			printf("newFds's queSize=%d\n",que->queSize);
			free(tempNode);
			if(preNode==NULL){
				tempNode=timeArr[nowTime].next;
			}else{
				tempNode=preNode->next;
			}
		}
		else{
			preNode=tempNode;
			tempNode=tempNode->next;
		}
	}
	return 0;
}
