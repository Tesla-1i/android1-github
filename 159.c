#include<stdio.h>

int gcd(int inum1,int inum2)//函数定义无分号
{
        int itemp=inum2;
        while(itemp!=0)
        {
                itemp=inum1%inum2;
                inum1=inum2;
                inum2=itemp;
        }
        return inum1;
}
int lcm(int inum1,int inum2)
{
        int igcdinside=gcd(inum1,inum2);
        return inum1*inum2/igcdinside;
}
int main()
{
        int inum1,inum2,igcd,ilcm;
        printf("input inum1&inum2(inum1>inum2):");
        scanf("%d %d",&inum1,&inum2);
        igcd = gcd(inum1,inum2);//调用函数
        ilcm = lcm(inum1,inum2);
        printf("the gcd of %d and %d is:%d\n",inum1,inum2,igcd);
        printf("the lcm of %d and %d is:%d\n",inum1,inum2,ilcm);
        return 0;
}
