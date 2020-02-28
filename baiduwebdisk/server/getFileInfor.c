#include "factory.h"
void tackle(char *p,unsigned int privi){
	char mark[3]={'r','w','x'};
	int k=2;
	for(int i=8;i>=0;i--){
		if((privi&1)==1){
			p[i]=mark[k];
		}else{
			p[i]='-';
		}
		privi=privi>>1;
		k--;
		if(k<0){
			k=2;
		}
	}
}
int getFileInfor(pNode_t user,fileInfor *fileMsg){
	printf("I am in getFileInfor\n");
	char power[10]={0};
	DIR *dir=opendir(user->path);
	struct dirent *p;
	int ret;
	char temp_path[1024]={0};
	char is_directory;
	rewinddir(dir);
	while((p=readdir(dir))){
		if(!strcmp(p->d_name,".")||!strcmp(p->d_name,"..")){
			continue;
		}
		bzero(temp_path,sizeof(temp_path));
		bzero(fileMsg,sizeof(fileInfor));
		sprintf(temp_path,"%s/%s",user->path,p->d_name);
		if(p->d_type==4){
			is_directory='d';
		}else{
			is_directory='-';
            int fd=open(temp_path,O_RDWR);
			char getPath[512]={0};
			bzero(temp_path,sizeof(temp_path));
            read(fd,getPath,sizeof(getPath));
			sprintf(temp_path,"%s/%s","allFile",getPath);
		}
		printf("temp_path=%s\n",temp_path);
		struct stat buf;
		ret=stat(temp_path,&buf);
		ERROR_CHECK(ret,-1,"stat");
		tackle(power,buf.st_mode);
		sprintf(fileMsg->privilege_name,"%c%s%s",is_directory,power,p->d_name);
		fileMsg->filesize=(int)buf.st_size;
		fileMsg->nLink=(int)buf.st_nlink;
		fileMsg->dataLen[0]=strlen(power)+1;
		fileMsg->dataLen[1]=strlen(p->d_name);
		fileMsg->flag=1;
		ret=send(user->newFd,fileMsg,20+fileMsg->dataLen[1]+fileMsg->dataLen[0],0);
		ERROR_CHECK(ret,-1,"send");
	}
	bzero(fileMsg,sizeof(fileInfor));
	fileMsg->flag=2;
	ret=send(user->newFd,fileMsg,20+fileMsg->dataLen[1]+fileMsg->dataLen[0],0);
	ERROR_CHECK(ret,-1,"send");
	ret=closedir(dir);
	ERROR_CHECK(ret,-1,"closedir");
	return 0;
}
