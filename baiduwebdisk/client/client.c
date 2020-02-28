#include "funcAchieve.h"
int main(int argc,char *argv[]){
	ARGS_CHECK(argc,3);
	int socketFd=socket(AF_INET,SOCK_STREAM,0);
	ERROR_CHECK(socketFd,-1,"socket");
	struct sockaddr_in server;
	char myPath[512]={0};
	int dataLen,operateLen;
	bzero(&server,sizeof(server));
	server.sin_family=AF_INET;
	server.sin_port=htons(atoi(argv[2]));
	inet_pton(AF_INET,argv[1],&server.sin_addr);
	int ret=connect(socketFd,(struct sockaddr*)&server,sizeof(server));
	ERROR_CHECK(ret,-1,"connect");
	while(1){
		ret=sendUserInfor(socketFd,myPath);
		if(ret==2){
			break;
		}
		else if(ret==5){
			close(socketFd);
			return 0;
		}
		else{
			continue;
		}
	}
	train_t train;
	char operate[20]={0};
	char data[256]={0};  
	//从train.buf中读取数据或者将data中的数据写入buf
	char md5[2048]={0};
	char putsFilename[128]={0};  
	char getsPath[128]={0};
	fileInfor fileMsg;
	while(1){
		bzero(operate,sizeof(operate));
		bzero(data,sizeof(data));
		bzero(&train,sizeof(train));
		printf("输入命令\n");
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
				bzero(data,sizeof(data));
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
				printf("md5=%s\n",md5);
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
			struct stat buf;
			bzero(getsPath,sizeof(getsPath));
			scanf("%s",data);
			sprintf(getsPath,"%s/%s","allFile",data);
			if(0==access(getsPath,F_OK)){
				bzero(&train,sizeof(train));
				train.dataLen=strlen(data);
				train.operateLen=strlen(operate);
				sprintf(train.buf,"%s%s",operate,data);
				int ret=send(socketFd,&train,train.operateLen+train.dataLen+8,0);
				ERROR_CHECK(ret,-1,"send");
				int fd=open(getsPath,O_RDWR|O_CREAT,0644);
				bzero(&buf,sizeof(buf));
				fstat(fd,&buf);
				//发送目前文件大小
				bzero(&train,sizeof(train));
				train.dataLen=sizeof(buf.st_size);
				memcpy(train.buf,&buf.st_size,train.dataLen);
				ret=send(socketFd,&train,8+train.dataLen,0);
				ERROR_CHECK(ret,-1,"send");
				lseek(fd,buf.st_size,SEEK_SET);
				off_t filesize;
				//接收文件长度
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,&filesize,dataLen);
				if(filesize==0){
					printf("this file is existed,request denied\n");
					close(fd);
					continue;
				}
				int fds[2];
				off_t downSize=0,recvsize=0;
				pipe(fds);
				time_t lastTime,nowTime;
				nowTime=lastTime=time(NULL);
				while(downSize<filesize){
					recvsize=splice(socketFd,NULL,fds[1],NULL,60000,SPLICE_F_MOVE|SPLICE_F_MORE);
					splice(fds[0],NULL,fd,NULL,recvsize,SPLICE_F_MOVE|SPLICE_F_MORE);
					downSize+=recvsize;
					time(&nowTime);
					if(nowTime-lastTime>=1){
						printf("%5.2lf%%\r",1.0*downSize/filesize*100);
						fflush(stdout);
						lastTime=nowTime;
					}
				}
				fflush(stdout);
				printf("100%%  \n");
				close(fd);
			}
			else{
				bzero(&train,sizeof(train));
				train.dataLen=strlen(data);
				train.operateLen=strlen(operate);
				sprintf(train.buf,"%s%s",operate,data);
				int ret=send(socketFd,&train,train.operateLen+train.dataLen+8,0);
				ERROR_CHECK(ret,-1,"send");
				int fd=open(getsPath,O_RDWR|O_CREAT,0644);
				bzero(&buf,sizeof(buf));
				fstat(fd,&buf);
				//发送目前文件大小
				//printf("nowsize=%ld\n",buf.st_size);
				bzero(&train,sizeof(train));
				train.dataLen=sizeof(buf.st_size);
				memcpy(train.buf,&buf.st_size,train.dataLen);
				//printf("begin send filesize\n");
				ret=send(socketFd,&train,8+train.dataLen,0);
				ERROR_CHECK(ret,-1,"send");
			   //printf("send success\nret=%d\n",ret);
				off_t filesize;
				int fds[2];
				off_t downSize=0,recvsize=0;
				pipe(fds);
				//接收文件长度
				recvCycle(socketFd,&dataLen,4);
				recvCycle(socketFd,&operateLen,4);
				recvCycle(socketFd,&filesize,dataLen);
				time_t lastTime,nowTime;
				nowTime=lastTime=time(NULL);
				while(downSize<filesize){
					recvsize=splice(socketFd,NULL,fds[1],NULL,60000,SPLICE_F_MOVE|SPLICE_F_MORE);
					splice(fds[0],NULL,fd,NULL,recvsize,SPLICE_F_MOVE|SPLICE_F_MORE);
					downSize+=recvsize;
					time(&nowTime);
					if(nowTime-lastTime>=1){
						printf("%5.2lf%%\r",1.0*downSize/filesize*100);
						fflush(stdout);
						lastTime=nowTime;
					}
				}
				fflush(stdout);
				printf("100%%  \n");
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
		}else if(strcmp(operate,"exit")==0){
			char recvBuf[128]={0};
			bzero(&train,sizeof(train));
			memcpy(train.buf,operate,strlen(operate));
			train.operateLen=strlen(operate);
			send(socketFd,&train,train.operateLen+8,0);
			bzero(&train,sizeof(train));
			recvCycle(socketFd,&dataLen,4);
			recvCycle(socketFd,&operateLen,4);
			recvCycle(socketFd,recvBuf,dataLen);
			if(strcmp(recvBuf,"exit success")==0){ //假设一定能收到数据
				printf("exit success\n");
				break;
			}else{
				printf("exit failed\n");
			}
		}else{
			printf("can't find this commond\n");
		}
	}
	close(socketFd);
	return 0;
}
