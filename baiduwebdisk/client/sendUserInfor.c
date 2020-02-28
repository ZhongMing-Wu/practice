#include "funcAchieve.h"
int sendUserInfor(int socketFd,char *myPath){
label:
	printf("输入1 Or 2\n");
	printf("1:登陆\n"
			"2:注册\n"
			"3:退出\n");
	int choice,ret,dataLen,operateLen;
	char name[100]={0};
	char *passwd;
	char salt[512]={0};
	char codePasswd[512]={0};
	char data[128]={0};
	train_t user;
	scanf("%d",&choice);
	if(choice==2){
		while(1){
			printf("username:");
			bzero(name,sizeof(name));
			scanf("%s",name);
			if(strlen(name)>20){
				system("clear");
				printf("\nthis username is too long,please input again\n");
				continue;
			}
			passwd=getpass("passwd:");
			if(strlen(passwd)>20){
				system("clear");
				printf("\nthis passwd is too long,please input username and passwd again\n");
				continue;
			}
			bzero(&user,sizeof(user));
			user.dataLen=strlen(name);
			user.operateLen=-1;  //-1表示注册
			memcpy(user.buf,name,strlen(name));
			//发送用户名
			ret=send(socketFd,&user,8+user.dataLen,0);
			ERROR_CHECK(ret,-1,"send");
			//接收盐值(可能接收到真正的盐值，可能接收到提示用户存在的信息)
			recvCycle(socketFd,&dataLen,4);
			recvCycle(socketFd,&operateLen,4);
			bzero(salt,sizeof(salt));
			recvCycle(socketFd,salt,dataLen);
			if(strcmp(salt,"this name is existed")==0){
				//此时，说明服务器数据库中该用户名已存在
				printf("%s\n",salt);
				goto label;
			}
			bzero(codePasswd,sizeof(codePasswd));
			strcpy(codePasswd,crypt(passwd,salt));
			bzero(&user,sizeof(user));
			user.dataLen=strlen(codePasswd);
			user.operateLen=-1;
			memcpy(user.buf,codePasswd,strlen(codePasswd));
			//发送密文
			send(socketFd,&user,8+user.dataLen,0);
			recvCycle(socketFd,&dataLen,4);
			recvCycle(socketFd,&operateLen,4);
			bzero(data,sizeof(data));
			recvCycle(socketFd,data,dataLen);
			if(strcmp(data,"register success")==0){
				printf("%s\n",data);
				return 1;
			}else{
				printf("register failed\n");
			}
		}
	}else if(choice==1){
		printf("username:");
		bzero(name,sizeof(name));
		scanf("%s",name);
		passwd=getpass("passwd:");
		bzero(&user,sizeof(user));
		user.dataLen=strlen(name);
		user.operateLen=-2;      //-2表示登陆
		memcpy(user.buf,name,strlen(name));
		//发送用户名
		ret=send(socketFd,&user,8+user.dataLen,0);
		ERROR_CHECK(ret,-1,"send");
		//接收盐值
		recvCycle(socketFd,&dataLen,4);
		recvCycle(socketFd,&operateLen,4);
		bzero(salt,sizeof(salt));
		recvCycle(socketFd,salt,dataLen);
		if(strcmp(salt,"can't find this username")==0){
			printf("%s\n",salt);
			return 4;
		}
		bzero(codePasswd,sizeof(codePasswd));
		strcpy(codePasswd,crypt(passwd,salt));
		bzero(&user,sizeof(user));
		user.dataLen=strlen(codePasswd);
		user.operateLen=-2;
		memcpy(user.buf,codePasswd,strlen(codePasswd));
		//发送密文
		send(socketFd,&user,8+user.dataLen,0);
		recvCycle(socketFd,&dataLen,4);
		recvCycle(socketFd,&operateLen,4);
		bzero(data,sizeof(data));
		recvCycle(socketFd,data,dataLen);
		if(strcmp(data,"log success")==0){
			printf("%s\n",data);
			strcpy(myPath,name);
			return 2;
		}
		else{
			printf("%s\n",data);
			return 3;
		}
	}else if(choice==3){
		bzero(&user,sizeof(user));
		user.dataLen=0;
		user.operateLen=-3;  //-3表示退出
		//发送退出消息
		ret=send(socketFd,&user,8,0);
		ERROR_CHECK(ret,-1,"send");
		char recvBuf[128]={0};
		recvCycle(socketFd,&dataLen,4);
		recvCycle(socketFd,&operateLen,4);
		recvCycle(socketFd,recvBuf,dataLen);
		if(strcmp(recvBuf,"exit success")==0){
			printf("exit success\n");
		}else{
			printf("exit failed\n");
		}
		return 5;
	}
	return 0;
}
