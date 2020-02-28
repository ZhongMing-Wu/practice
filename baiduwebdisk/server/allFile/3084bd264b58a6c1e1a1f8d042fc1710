#include "funcAchieve.h"
char myPath[512]={0};
int main(int argc,char *argv[]){
	ARGS_CHECK(argc,4);
	int socketFd=socket(AF_INET,SOCK_STREAM,0);
	ERROR_CHECK(socketFd,-1,"socket");
	struct sockaddr_in server;
	bzero(&server,sizeof(server));
	server.sin_family=AF_INET;
	server.sin_port=htons(atoi(argv[2]));
	inet_pton(AF_INET,argv[1],&server.sin_addr);
	int ret=connect(socketFd,(struct sockaddr*)&server,sizeof(server));
	ERROR_CHECK(ret,-1,"connect");
	train_t train;
	bzero(&train,sizeof(train));
	train.dataLen=strlen(argv[3]);
	train.operateLen=0;
	memcpy(train.buf,argv[3],strlen(argv[3]));
	ret=send(socketFd,&train,8+train.dataLen+train.operateLen,0);
	ERROR_CHECK(ret,-1,"send");
	char operate[20]={0};
	char data[256]={0};  
	//从train.buf中读取数据或者将data中的数据写入buf
	char md5[2048]={0};
	char putsFilename[128]={0};  
	char getsPath[128]={0};
	int dataLen,operateLen;
	fileInfor fileMsg;
	memcpy(myPath,argv[3],strlen(argv[3]));
	while(1){
		bzero(operate,sizeof(operate));
		bzero(data,sizeof(data));
		bzero(&train,sizeof(train));
		scanf("%s",operate);
		if(strcmp(operate,"cd")==0){
			sendOperate(socketFd,operate,data);
			bzero(data,sizeof(data));
			recvCycle(socketFd,&dataLen,4);
			recvCycle(socketFd,&operateLen,4);
			if(operateLen==1){
				recvCycle(socketFd,data,dataLen);
				bzero(myPath,sizeof(myPath));
				memcpy(myPath,data,strlen(data));
				printf("cd success\n");	
				continue;
			}else{
				recvCycle(socketFd,data,dataLen);
			}
			printf("%s\n",data);
		}
		else if(strcmp(operate,"ls")==0){
			system("clear");
			bzero(data,sizeof(data));
			train.dataLen=strlen(data);
			train.operateLen=strlen(operate);
			sprintf(train.buf,"%s%s",operate,data);
			ret=send(socketFd,&train,train.operateLen+train.dataLen+8,0)    ;
			ERROR_CHECK(ret,-1,"send");
			while(1){
				bzero(&fileMsg,sizeof(fileMsg));
				recvCycle(socketFd,&fileMsg.flag,4);
				recvCycle(socketFd,&fileMsg.dataLen[0],4);
				recvCycle(socketFd,&fileMsg.dataLen[1],4);
				recvCycle(socketFd,&fileMsg.filesize,4);
				recvCycle(socketFd,&fileMsg.nLink,4);
				recvCycle(socketFd,&fileMsg.privilege,fileMsg.dataLen[0]);
				recvCycle(socketFd,&fileMsg.name,fileMsg.dataLen[1]);
				if(fileMsg.flag==2){
					break;
				}else{
					printf("%s %d %d %s\n",fileMsg.privilege,fileMsg.nLink,fileMsg.filesize,fileMsg.name);
				}
			}
		}else if(strcmp(operate,"puts")==0){
			
			ret=sendOperate(socketFd,operate,data);
			bzero(putsFilename,sizeof(putsFilename));
			sprintf(putsFilename,"%s/%s","allFile",data);
			printf("putsFileName=%s\n",putsFilename);
			if(ret!=0){
				printf("sendOperate failed\n");
			}else{
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,data,dataLen);
				if(strcmp(data,"this file is existed,request denied")==0){
					printf("%s\n",data);
					continue;
				}
				printf("%s\n",data);
				bzero(md5,sizeof(md5));
				bzero(data,sizeof(data));
				md5Sum(md5,putsFilename);
				train.dataLen=strlen(md5);
				memcpy(train.buf,md5,strlen(md5));
				//发送md5sum
				send(socketFd,&train,8+train.dataLen,0);
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,data,dataLen);
				if(strcmp(data,"this file is existed")==0){
					printf("send success\n");
					continue;
				}
				//发送文件内容
				sendFileInfor(socketFd,putsFilename);
			}
		}else if(strcmp(operate,"gets")==0){
			bzero(getsPath,sizeof(getsPath));
			scanf("%s",data);
			sprintf(getsPath,"%s/%s","allFile",data);
			if(0==access(getsPath,F_OK)){
				printf("this file is existed,gets failed\n");
			}
			else{
				bzero(&train,sizeof(train));
				system("clear");
				train.dataLen=strlen(data);
				train.operateLen=strlen(operate);
				sprintf(train.buf,"%s%s",operate,data);
				int ret=send(socketFd,&train,train.operateLen+train.dataLen+8,0);
				ERROR_CHECK(ret,-1,"send");
				int fd=open(getsPath,O_RDWR|O_CREAT|0600);
				int filesize;
				int fds[2],downSize=0;
				pipe(fds);
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,&filesize,dataLen);
				time_t lastTime,nowTime;
				nowTime=lastTime=time(NULL);
				while(downSize<filesize){
					ret=splice(socketFd,NULL,fds[1],NULL,60000,SPLICE_F_MOVE|SPLICE_F_MORE);
					ERROR_CHECK(ret,-1,"splice");
					splice(fds[0],NULL,fd,NULL,ret,SPLICE_F_MOVE|SPLICE_F_MORE);
					downSize+=ret;
					time(&nowTime);
					if(nowTime-lastTime>=1){
						printf("%5.2lf%%\r",1.0*downSize/filesize*100);
						fflush(stdout);
						lastTime=nowTime;
					}
				}
               close(fd);
			}
		}else if(strcmp(operate,"remove")==0){
			ret=sendOperate(socketFd,operate,data);
			if(ret!=0){
				printf("remove failed\n");
			}
			else{
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,data,dataLen);
				printf("%s\n",data);
			}
		}else if(strcmp(operate,"pwd")==0){
			printf("%s\n",myPath);
		}else if(strcmp(operate,"mkdir")==0){
			ret=sendOperate(socketFd,operate,data);
			if(ret!=0){
				printf("mkdir failed\n");
			}else{
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,data,dataLen);
				printf("%s\n",data);
			}
		}else{
			printf("cna't find this commond\n");
		}
	}
	return 0;
}
