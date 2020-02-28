#include "factory.h"
void exitFunc(void *p){
	pFactory_t fac=(pFactory_t)p;
	pthread_mutex_unlock(&fac->que->mutex);
}
void *pthreadFunc(void *p){
	pFactory_t fac=(pFactory_t)p;
	int flag;
	pthreadQueueNode_t *pGet=NULL;
	record writeToLog;
	while(1){
		pthread_mutex_lock(&fac->que->mutex);
		pthread_cleanup_push(exitFunc,fac);
		if(fac->que->queSize==0){
			pthread_cond_wait(&fac->cond,&fac->que->mutex);
		}
		flag=queGet(fac->que,&pGet);
		pthread_cleanup_pop(1);
		if(flag==0){
			/*if(strcmp(pGet->operate,"puts")==0){
				bzero(&writeToLog,sizeof(writeToLog));
				strcpy(writeToLog.name,pGet->name);
				sprintf(writeToLog.operation,"%s%s","puts ",pGet->data);
				write_to_log(writeToLog);
				putsFile(pGet);
			}else*/
			if(strcmp(pGet->operate,"gets")==0){
				printf("I am in gets\n");
				bzero(&writeToLog,sizeof(writeToLog));
				strcpy(writeToLog.name,pGet->name);
				sprintf(writeToLog.operation,"%s%s","gets ",pGet->data);
				write_to_log(writeToLog);
				getsFile(pGet);	
			}else if(strcmp(pGet->operate,"remove")==0){
				bzero(&writeToLog,sizeof(writeToLog));
				strcpy(writeToLog.name,pGet->name);
				sprintf(writeToLog.operation,"%s%s","remove ",pGet->data);
				write_to_log(writeToLog);
				removeFile(pGet);	
			}
			*(pGet->mark)=0;
			updateTheLocationInTimeArr(pGet->toArrNode,*(pGet->locationArr+pGet->newFd),pGet->timeArr,*pGet->time);
			free(pGet);
			printf("this pthread is end\n");
			pGet=NULL;
		}
	}
}

void factoryInit(pFactory_t fac,pthreadQue_t * que,int num){
	fac->startFlag=0;
	fac->threadNum=num;
	fac->threadId=(pthread_t*)calloc(num,sizeof(pthread_t));
	fac->que=que;
	pthread_cond_init(&fac->cond,NULL);
}
void factoryStart(pFactory_t fac){
	if(fac->startFlag==0){
		fac->startFlag=1;
		for(int i=0;i<fac->threadNum;i++){
			pthread_create(fac->threadId+i,NULL,pthreadFunc,fac);
		}
	}
}
