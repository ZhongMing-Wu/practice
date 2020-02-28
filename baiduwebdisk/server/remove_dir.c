#include "factory.h"
int removeDir(const char *dir){
	char cur_dir[]=".";
	char up_dir[]="..";
	char dir_name[512];
	char operate[512]={0};
	char temp_path[512]={0};
	char md5[2048]={0};
	int linkNum;
	char charLinkNum[20]={0};
	DIR *dirp;
	struct dirent *dp;
	struct stat dir_stat;
	if(0>stat(dir,&dir_stat)){
		perror("get directory stat error");
		return -1;
	}
	if(S_ISREG(dir_stat.st_mode)){
		int fd=open(dir,O_RDWR);
		bzero(md5,sizeof(md5));
		read(fd,md5,sizeof(md5));
		printf("md5=%s\n",md5);
		close(fd); 
		bzero(operate,sizeof(operate));
		strcpy(operate,"select linkNum from fileinfor where filename='");
		sprintf(operate,"%s%s%s",operate,md5,"'");
		linkNum=query(operate);
		if(linkNum==1){
			bzero(operate,sizeof(operate));
			strcpy(operate,"delete from fileinfor where filename='");
			sprintf(operate,"%s%s%s",operate,md5,"'");
			deleteData(operate);
			bzero(temp_path,sizeof(temp_path));
			sprintf(temp_path,"%s/%s","allFile",md5);
			remove(temp_path);
		}else{
			bzero(charLinkNum,sizeof(charLinkNum));
			int_to_char(linkNum-1,charLinkNum);
			bzero(operate,sizeof(operate));
			strcpy(operate,"update fileinfor set linkNum=");
			sprintf(operate,"%s%s%s%s%s",operate,charLinkNum," where filename='",md5,"'");
			update(operate);
		}
		remove(dir);
	}
	else if(S_ISDIR(dir_stat.st_mode)){
		dirp=opendir(dir);
		while((dp=readdir(dirp))!=NULL){
			if(0==strcmp(cur_dir,dp->d_name)||0==strcmp(up_dir,dp->d_name)){
				continue;
			}
			sprintf(dir_name,"%s/%s",dir,dp->d_name);
			removeDir(dir_name);
		}
		closedir(dirp);
		remove(dir);
	}else{
		perror("unknow file type!");
	}
	return 0;
}
