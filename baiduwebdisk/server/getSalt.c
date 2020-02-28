#include "factory.h"
void getSalt(char *salt){
	int flag,i;
	char *s;
	srand((unsigned)time(NULL));
	if((s=(char*)calloc(13,sizeof(char)))==NULL){
		printf("calloc failed\n");
	}
	for(i=0;i<12;i++){
		flag=rand()%3;
		switch(flag){
			case 0:s[i]='A'+rand()%26;break;
			case 1:s[i]='a'+rand()%26;break;
			case 2:s[i]='0'+rand()%10;break;
			default:s[i]='x';break;
		}
	}
	s[12]=0;
    sprintf(salt,"%s%s%s","$6$",s,"$");
	free(s);
	s=NULL;
}
