#include "factory.h"
int pipeFd[2];
void sigFunc(int signum){
	printf("%d is coming\n",signum);
	write(pipeFd[1],&signum,1);
}
int main(int argc,char *argv[]){
	ARGS_CHECK(argc,5);
	factory_t threadInfor;
	queue_t newFds;
	pthreadQue_t queInfor;
    pthreadQueInit(&queInfor,atoi(argv[4]));
	record writeToLog;
	int socketFd,dataLen,operateLen;
	pipe(pipeFd);
	while(fork()){
		signal(SIGINT,sigFunc);
		int status;
		close(pipeFd[0]);
		wait(&status);
		if(WIFEXITED(status)){
			printf("exit success\n");
			exit(0);
		}
	}
	close(pipeFd[1]);
	int epfd=epoll_create(1);
	factoryInit(&threadInfor,&queInfor,atoi(argv[3]));
	factoryStart(&threadInfor);
	tcpInit(&socketFd,argv[1],argv[2]);
	epollAdd(epfd,socketFd);
	epollAdd(epfd,pipeFd[0]);
	struct epoll_event evs[12];
	queInit(&newFds,15);
	pNode_t findNode=NULL,newNode=NULL;//用于查找与evs[i].data.fd相等的newFd
	int newFd,ready,i,j,recordTime=0,fdLocationInCircleArr[100],ret;
	arrNode_t *nodeInTimeArr;
	timeArr_t timeArr[30];
	bzero(timeArr,sizeof(timeArr));
	time_t pastTime,nowTime;
	pastTime=time(NULL);
	while(1){
		bzero(evs,sizeof(evs));
		ready=epoll_wait(epfd,evs,12,1000);
		time(&nowTime);
		if(nowTime-pastTime>=1){
			recordTime=(recordTime+1)%30;
			pastTime=nowTime;
		}
		for(i=0;i<ready;i++){
			if(evs[i].events==EPOLLIN&&evs[i].data.fd==socketFd){
				printf("new fd coming...\n");
				newFd=accept(socketFd,NULL,NULL);
				fdLocationInCircleArr[newFd]=recordTime;
				newNode=(pNode_t)calloc(1,sizeof(node_t));
				newNode->newFd=newFd;
				newNode->level=1;
				nodeInTimeArr=(arrNode_t*)calloc(1,sizeof(arrNode_t));
				nodeInTimeArr->newFd=newFd;
				nodeInTimeArr->toNewFdsNode=newNode;
				nodeInTimeArr->mark=0;
				newNode->toArrNode=nodeInTimeArr;
				if(timeArr[recordTime].next==NULL){
					timeArr[recordTime].next=nodeInTimeArr;
					timeArr[recordTime].tail=nodeInTimeArr;
				}else{
					timeArr[recordTime].tail->next=nodeInTimeArr;
					nodeInTimeArr->pre=timeArr[recordTime].tail;
					timeArr[recordTime].tail=nodeInTimeArr;
				}				
				pthread_mutex_lock(&newFds.mutex);
				queInsert(&newFds,newNode);
				pthread_mutex_unlock(&newFds.mutex);
				epollAdd(epfd,newFd);
				printf("epolladd success\n");
			}
			if(evs[i].events==EPOLLIN&&evs[i].data.fd==pipeFd[0]){
				for(j=0;j<threadInfor.threadNum;j++){
					pthread_cancel(threadInfor.threadId[j]);
				}
				for(j=0;j<threadInfor.threadNum;j++){
					pthread_join(threadInfor.threadId[j],NULL);
				}
				free(threadInfor.threadId);
				threadInfor.threadId=NULL;
				printf("begin exit\n");
				exit(0);
			}
			findNode=newFds.head;
			for(j=0;j<newFds.queSize;j++){
				if(evs[i].events==EPOLLIN&&evs[i].data.fd==findNode->newFd){
					//接收消息并作出相应的操作
					ret=recvCycle(findNode->newFd,&dataLen,4);
					if(ret==1){
						freeParticularNewFd(&newFds,timeArr,fdLocationInCircleArr[findNode->newFd],epfd,findNode);
						printf("one client closed\n");
						break;
					}
					recvCycle(findNode->newFd,&operateLen,4);
					if(fdLocationInCircleArr[findNode->newFd]!=recordTime){
						updateTheLocationInTimeArr(findNode->toArrNode,fdLocationInCircleArr[findNode->newFd],timeArr,recordTime);
						fdLocationInCircleArr[findNode->newFd]=recordTime;
					}
					if(operateLen>=0){
						bzero(findNode->operate,sizeof(findNode->operate));
						recvCycle(findNode->newFd,findNode->operate,operateLen);
						bzero(findNode->data,sizeof(findNode->data));
						printf("datalen=%d\n",dataLen);
						recvCycle(findNode->newFd,findNode->data,dataLen);
						if(strcmp(findNode->operate,"cd")==0){
							bzero(&writeToLog,sizeof(writeToLog));
							strcpy(writeToLog.name,findNode->name);
							sprintf(writeToLog.operation,"%s %s",findNode->operate,findNode->data);
							write_to_log(writeToLog);
							cdDir(findNode);
						}
						else if(strcmp(findNode->operate,"ls")==0){
							bzero(&writeToLog,sizeof(writeToLog));
							strcpy(writeToLog.name,findNode->name);
							sprintf(writeToLog.operation,"%s %s",findNode->operate,findNode->data);
							write_to_log(writeToLog);
							lsDirent(findNode);
						}
						else if(strcmp(findNode->operate,"mkdir")==0){
							bzero(&writeToLog,sizeof(writeToLog));
							strcpy(writeToLog.name,findNode->name);
							sprintf(writeToLog.operation,"%s %s",findNode->operate,findNode->data);
							write_to_log(writeToLog);
							createDirent(findNode);
						}else if(strcmp(findNode->operate,"exit")==0){
							exitChildThread(findNode);
						}else if(strcmp(findNode->operate,"puts")==0){
					       bzero(&writeToLog,sizeof(writeToLog));
					       strcpy(writeToLog.name,findNode->name);
					       sprintf(writeToLog.operation,"%s%s","puts ",findNode->data);
					       write_to_log(writeToLog);
					       putsFile(findNode);
						}
						else {
							findNode->toArrNode->mark=1;
							pthreadQueueNode_t *pthreadNode;
							pthreadNode=(pthreadQueueNode_t *)calloc(1,sizeof(pthreadQueueNode_t));
							pthreadNode->mark=&(findNode->toArrNode->mark);
							pthreadNode->newFd=findNode->newFd;
							memcpy(pthreadNode->path,findNode->path,strlen(findNode->path));
							memcpy(pthreadNode->data,findNode->data,strlen(findNode->data));
							memcpy(pthreadNode->operate,findNode->operate,strlen(findNode->operate));
							memcpy(pthreadNode->name,findNode->name,strlen(findNode->name));
							pthreadNode->locationArr=fdLocationInCircleArr;
							pthreadNode->timeArr=timeArr;
							pthreadNode->time=&recordTime;
							pthreadNode->toArrNode=findNode->toArrNode;
							pthread_mutex_lock(&queInfor.mutex);
							pthreadQueInsert(&queInfor,pthreadNode);
							pthread_mutex_unlock(&queInfor.mutex);
							pthread_cond_signal(&threadInfor.cond);
						}
					}
					else{
						if(operateLen==-1){//说明是注册信息
							bzero(findNode->name,sizeof(findNode->name));
							recvCycle(findNode->newFd,findNode->name,dataLen);
							registerAndLog(findNode,operateLen);
						}
						else if(operateLen==-2){//说明是登录信息 
							bzero(findNode->name,sizeof(findNode->name));
							recvCycle(findNode->newFd,findNode->name,dataLen);
							registerAndLog(findNode,operateLen);
						}else if(operateLen==-3){
							exitChildThread(findNode);
						}
					}
					break;
				}
				else{
					findNode=findNode->next;
				}
			}
		}
		ret=freeNewFd(&newFds,timeArr,(recordTime+1)%30,epfd);
		printf("now time is %d\n",recordTime);
	}//while循环
}
