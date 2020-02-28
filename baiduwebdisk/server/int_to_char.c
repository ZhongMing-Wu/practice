#include "factory.h"
void int_to_char(int linkNum,char *charLinkNum){
	char temp[20]={0};
	int val,i=0,k=0;
	while(linkNum!=0){
		val=linkNum%10;
		temp[i++]=val+'0';
		linkNum=linkNum/10;
	}
	for(i=strlen(temp)-1;i>=0;i--){
		charLinkNum[k++]=temp[i];
	}
}
