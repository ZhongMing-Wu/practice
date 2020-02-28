#include "factory.h"
void cdDir(pNode_t user){
	int i;
	train_t train;
	if(strcmp(user->data,"..")==0&&user->level>1){
		i=strlen(user->path)-1;
		while(user->path[i]!='/'){
			user->path[i]=0;
			i--;
		}
		user->path[i]=0;
		user->level-=1;
		train.dataLen=strlen(user->path);
		train.operateLen=1;
		memcpy(train.buf,user->path,strlen(user->path));
		send(user->newFd,&train,train.dataLen+8,0);
		return;
	}else if(strcmp(user->data,".")==0){
		train.dataLen=strlen(user->path);
		train.operateLen=1;
		memcpy(train.buf,user->path,strlen(user->path));
		send(user->newFd,&train,train.dataLen+8,0);
		return;
	}
	else if(strcmp(user->data,"..")==0&&user->level==1){
		char temp[]="request denied,now is root";
		train.dataLen=strlen(temp);
		train.operateLen=0;
		memcpy(train.buf,temp,strlen(temp));
		send(user->newFd,&train,train.dataLen+8,0);
		return;
	}else{
		char temp_path[512]={0};
		struct stat dir_stat;
		sprintf(temp_path,"%s/%s",user->path,user->data);
		if(0!=access(temp_path,F_OK)){
			char temp[]="can't find this file";
			train.dataLen=strlen(temp);
			train.operateLen=0;
			memcpy(train.buf,temp,strlen(temp));
			send(user->newFd,&train,train.dataLen+8,0);
			return;
		}
		stat(temp_path,&dir_stat);
		if(S_ISDIR(dir_stat.st_mode)){
			sprintf(user->path,"%s/%s",user->path,user->data);
			user->level+=1;
			train.dataLen=strlen(user->path);
			train.operateLen=1;
			memcpy(train.buf,user->path,strlen(user->path));
			send(user->newFd,&train,train.dataLen+8,0);
			return;
		}else if(S_ISREG(dir_stat.st_mode)){
			char temp[]="request denied,this file is not a dirtory";
			train.dataLen=strlen(temp);
			train.operateLen=0;
			memcpy(train.buf,temp,strlen(temp));
			send(user->newFd,&train,train.dataLen+8,0);
			return;
		}
	}
}
void lsDirent(pNode_t user){
	fileInfor *fileMsg=(fileInfor*) calloc(1,sizeof(fileInfor));
	int ret=getFileInfor(user,fileMsg);
	if(ret!=0){
		perror("getFileInfor error\n");
	}
	free(fileMsg);
	fileMsg=NULL;
}

int removeFile(pthreadQueueNode_t *user){
	char temp_path[512]={0};
	sprintf(temp_path,"%s/%s",user->path,user->data);
	if(0==access(temp_path,F_OK)){
		removeDir(temp_path);
		char temp_buf[128]="remove success";
		sendMsg(user->newFd,temp_buf);
	}else{
		char temp_buf[128]="can't find this file,remove failed";
		sendMsg(user->newFd,temp_buf);
	}
	return 0;
}

int createDirent(pNode_t user){
	char temp_path[512]={0};
	int ret;
	sprintf(temp_path,"%s/%s",user->path,user->data);
	if(0==access(temp_path,F_OK)){
		char temp_buf[128]="this dirent is existed,create failed";
		sendMsg(user->newFd,temp_buf);
	}else{
		ret=mkdir(temp_path,0755);
		ERROR_CHECK(ret,-1,"mkdir");
		char temp_buf[128]="create success";
		sendMsg(user->newFd,temp_buf);
	}
	return 0;
}

int putsFile(pNode_t user){
	char temp_path[512]={0};
	char temp_buf[256]={0};
	char md5[1024]={0};
	char operate[256]={0};
	int dataLen,operateLen;
	off_t filesize;
	int fd;//,fds[2];
	//off_t downsize=0,recvSize=0;
	int linkNum;  //用于存储某一个文件的引用数
	char charLinkNum[20]={0};
	sprintf(temp_path,"%s/%s",user->path,user->data);
	if(0==access(temp_path,F_OK)){
		bzero(temp_buf,sizeof(temp_buf));
		strcpy(temp_buf,"this file is existed,request denied");
		sendMsg(user->newFd,temp_buf);
	}
	else{
		fd=open(temp_path,O_RDWR|O_CREAT,0644);
		bzero(temp_buf,sizeof(temp_buf));
		strcpy(temp_buf,"create success");
		sendMsg(user->newFd,temp_buf);
		//接收客户端发送的md5sum
		bzero(md5,sizeof(md5));
		recvCycle(user->newFd,&dataLen,4);
		recvCycle(user->newFd,&operateLen,4);
		recvCycle(user->newFd,md5,dataLen);
		write(fd,md5,strlen(md5));   //将文件的md5sum写入文件中
		close(fd);
		bzero(temp_path,sizeof(temp_path));
		sprintf(temp_path,"%s/%s","allFile",md5);
		if(0==access(temp_path,F_OK)){//如果上传的文件服务器端已存在
			bzero(temp_buf,sizeof(temp_buf));
			strcpy(temp_buf,"this file is existed");
			sendMsg(user->newFd,temp_buf);
			bzero(operate,sizeof(operate));
			strcpy(operate,"select linkNum from fileinfor where filename='");
			sprintf(operate,"%s%s%s",operate,md5,"'");
			linkNum=query(operate)+1;
			int_to_char(linkNum,charLinkNum);
			bzero(operate,sizeof(operate));
			strcpy(operate,"update fileinfor set linkNum=");
			sprintf(operate,"%s%s%s%s%s",operate,charLinkNum," where filename='",md5,"'");
			update(operate);
			return 0;	   
		}
		fd=open(temp_path,O_RDWR|O_CREAT,0644);
		bzero(temp_buf,sizeof(temp_buf));
		strcpy(temp_buf,"allowed to send file");
		sendMsg(user->newFd,temp_buf);
		//接收文件长度
		recvCycle(user->newFd,&dataLen,4);
		recvCycle(user->newFd,&operateLen,4);
		recvCycle(user->newFd,&filesize,dataLen);
		printf("filesize=%ld\n",filesize);
		//pipe(fds);
		ftruncate(fd,filesize);
		char *pMap=(char *)mmap(NULL,filesize,PROT_WRITE|PROT_READ,MAP_SHARED,fd,0);
		recvCycle(user->newFd,pMap,filesize);
		munmap(pMap,filesize);
		/*while(downsize<filesize){
			  recvSize=splice(user->newFd,NULL,fds[1],NULL,4096,SPLICE_F_MOVE|SPLICE_F_MORE);
			  splice(fds[0],NULL,fd,NULL,recvSize,SPLICE_F_MOVE|SPLICE_F_MORE);
			  downsize+=recvSize;
		}*/
		close(fd);
		bzero(operate,sizeof(operate));
		strcpy(operate,"insert into fileinfor values('");
		sprintf(operate,"%s%s%s%s%s",operate,md5,"'",",","1)");
		insert(operate);
	}
	return 0;
}

int getsFile(pthreadQueueNode_t *user){
	char temp_path[512]={0};
	char md5[512]={0};
	sprintf(temp_path,"%s/%s",user->path,user->data);
	int fd=open(temp_path,O_RDONLY);
	read(fd,md5,sizeof(md5));
	close(fd);
	bzero(temp_path,sizeof(temp_path));
	sprintf(temp_path,"%s/%s","allFile",md5);
	sendFileInfor(user->newFd,temp_path);
	return 0;
}
