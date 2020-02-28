#include "factory.h"

int registerAndLog(pNode_t user,int flag){
	train_t train;
	int dataLen;
	char dataOperate[256]={0};
	char *name=user->name;
	char salt[512]={0};
	char passwd[512]={0};
	record writeToLog;
	while(1){
		if(flag==-1){  //注册 
			char nameFromDatabase[100]={0};
			bzero(dataOperate,sizeof(dataOperate));
			strcpy(dataOperate,"select username from user where username='");
			sprintf(dataOperate,"%s%s%s",dataOperate,name,"'");
			query_Data(dataOperate,nameFromDatabase);
			if(strcmp(nameFromDatabase,"can't find this username")!=0){
				bzero(&train,sizeof(train));
				strcpy(train.buf,"this name is existed");
				train.dataLen=strlen(train.buf);
				send(user->newFd,&train,8+train.dataLen,0);
				break;
			}
			bzero(salt,sizeof(salt));
			getSalt(salt);           
			bzero(&train,sizeof(train));
			train.dataLen=strlen(salt);
			memcpy(train.buf,salt,train.dataLen);
			//发送盐值
			send(user->newFd,&train,8+train.dataLen,0);
			//接收密文
			recvCycle(user->newFd,&dataLen,4);
			recvCycle(user->newFd,&flag,4);
			bzero(passwd,sizeof(passwd));
			recvCycle(user->newFd,passwd,dataLen);
			bzero(dataOperate,sizeof(dataOperate));
			strcpy(dataOperate,"insert into user values('");
			sprintf(dataOperate,"%s%s%s%s%s%s%s",dataOperate,name,"','",passwd,"','",salt,"')");
			insert(dataOperate);
			bzero(&train,sizeof(train));
			strcpy(train.buf,"register success");
			train.dataLen=strlen(train.buf);
			send(user->newFd,&train,8+train.dataLen,0); 
			mkdir(name,0755);
			bzero(&writeToLog,sizeof(writeToLog));
			strcpy(writeToLog.name,name);
			strcpy(writeToLog.operation,"register in server");
			write_to_log(writeToLog);
			break;
		}else if(flag==-2){ //登陆
			bzero(dataOperate,sizeof(dataOperate));
			strcpy(dataOperate,"select salt from user where username='");
			sprintf(dataOperate,"%s%s%s",dataOperate,name,"'");
			bzero(salt,sizeof(salt));
			query_Data(dataOperate,salt);
			bzero(&train,sizeof(train));
			train.dataLen=strlen(salt);
			memcpy(train.buf,salt,train.dataLen);
			//发送盐值
			send(user->newFd,&train,8+train.dataLen,0); 
			if(strcmp(salt,"can't find this username")==0){
				break;
			}
			recvCycle(user->newFd,&dataLen,4);
			recvCycle(user->newFd,&flag,4);
			bzero(passwd,sizeof(passwd));
			recvCycle(user->newFd,passwd,dataLen);
			bzero(dataOperate,sizeof(dataOperate));
			strcpy(dataOperate,"select passwd from user where username='");
			sprintf(dataOperate,"%s%s%s",dataOperate,name,"'");
			char passwdFromDatabase[512]={0};
			query_Data(dataOperate,passwdFromDatabase);
			bzero(&train,sizeof(train));
			if(strcmp(passwd,passwdFromDatabase)==0){
				strcpy(train.buf,"log success");
			}else{
				strcpy(train.buf,"passwd is wrong");
			}
			train.dataLen=strlen(train.buf);
			send(user->newFd,&train,8+train.dataLen,0);
			bzero(&writeToLog,sizeof(writeToLog));
			strcpy(writeToLog.name,name);
			strcpy(writeToLog.operation,"log the server");
			write_to_log(writeToLog);
			if(strcmp(train.buf,"log success")==0){
				memcpy(user->path,user->name,strlen(user->name));
			}	
			break;
		}
	}
	return 0;
}
