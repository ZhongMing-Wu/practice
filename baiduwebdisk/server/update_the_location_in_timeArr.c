#include "factory.h"
int updateTheLocationInTimeArr(arrNode_t  *updateNode,int location,timeArr_t *timeArr,int nowTime){
	if(updateNode->pre==NULL){
		timeArr[location].next=updateNode->next;
		if(timeArr[location].next==NULL){
			timeArr[location].tail=NULL;
		}else{
			timeArr[location].next->pre=NULL;
		}
	}
	else{
		updateNode->pre->next=updateNode->next;
		if(updateNode->next==NULL){
			timeArr[location].tail=updateNode->pre;
		}
		else{
			updateNode->next->pre=updateNode->pre;
		}
	}
	if(timeArr[nowTime].next==NULL){
		timeArr[nowTime].next=updateNode;
		timeArr[nowTime].tail=updateNode;
		updateNode->pre=NULL;
		updateNode->next=NULL;
	}else{
		timeArr[nowTime].tail->next=updateNode;
		updateNode->pre=timeArr[nowTime].tail;
		updateNode->next=NULL;
	}
	return 0;
}
