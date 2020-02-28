#include "factory.h"
void write_to_log(record writeToLog){
	char temp_write[512]={0};
	char insertRecord[512]="insert into log values('";
    time_t t;
	struct tm *p;
	time(&t);
	p=localtime(&t);
	sprintf(temp_write,"%d-%d-%d %d:%d:%d",1900+p->tm_year,1+p->tm_mon,1+p->tm_mday,p->tm_hour,p->tm_min,p->tm_sec);
    sprintf(insertRecord,"%s%s%s%s%s%s%s",insertRecord,writeToLog.name,"','",writeToLog.operation,"','",temp_write,"')");
	insert(insertRecord);
}
