for i in range(1, 10):
    for j in range(1, i+1):
        print("%d*%d=%d\t" % (j, i, i*j),end="")
    print("")

#斐波那契数列  0，1，1，2，3，5，8,...
num=int(input("需要几项？"))
n1=0
n2=1
count=2
if num<=0:
    print("请输入一个整数。")
elif num==1:
    print("斐波那契数列:")
    print(n1)
elif num==2:
    print("斐波那契数列:")
    print(n1,",",n2)
else:
    print("斐波那契数列:")
    print(n1,",",n2,end=" , ")
    while count<num:
        sum=n1+n2
        print(sum,end=" , ")
        n1=n2
        n2=sum
        count+=1
print()
#阿姆斯特朗数
#如果一个n位正整数等于其各位数字的n次方之和, 则称该数为阿姆斯特朗数。 例如1^3 + 5^3 + 3^3 = 153。
num = int(input("请输入一个数字: "))
sum = 0
n = len(str(num))
temp = num
while temp > 0:
    digit = temp % 10
    print(digit)
    sum += digit ** n
    print(sum)
    temp //= 10
    print(temp,end="-----------")
    print("")
if num == sum:
    print(num, "是阿姆斯特朗数")
else:
    print(num, "不是阿姆斯特朗数")