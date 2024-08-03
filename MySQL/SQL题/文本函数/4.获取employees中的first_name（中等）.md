# 题目 获取employees中的first_name

请你将employees中的first_name，并按照first_name最后两个字母升序进行输出。

# 解答

```mysql
select first_name
from employees
order by substr(first_name, -2)
```

# 总结
按位截取字符串的使用：substr、substring，用法相同。substr(first_name, 2),正数表示，从字符串[2,],开始截取，到结束，字符串第一位index为1，所以[2,]包括了第二位。  
substr(first_name, 2,3) 第三个参数表示总共截取出的字符数。substr(first_name, -2)，表示从倒数第二位开始往后截取，包括了倒数第二位
