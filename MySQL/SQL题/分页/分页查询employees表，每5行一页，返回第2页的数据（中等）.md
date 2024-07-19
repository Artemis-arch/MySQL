# 题目 分页查询employees表，每5行一页，返回第2页的数据

# 解答

```mysql
SELECT *
FROM employees
LIMIT 5,5
```

# 总结

LIMIT 语句结构： LIMIT X,Y 
* Y：返回几条记录
* X：从第几条记录开始返回（第一条记录序号为0，默认为0）
