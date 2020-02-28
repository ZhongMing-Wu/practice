#include "factory.h"
int beginExit(pQueue_t que,pNode_t exitNode){
	if(exitNode->pre!=NULL){
		exitNode->pre->next=exitNode->next;
		if(exitNode->next!=NULL){
			exitNode->next->pre=exitNode->pre;
		}
		else{
			que->tail=exitNode->pre;
		}
		free(exitNode);
	}else{
		que->head=exitNode->next;
		if(que->head!=NULL){
			que->head->pre=NULL;
		}else{
			que->tail=NULL;
		}
		free(exitNode);
	}
	return 0;
}
