# [Consecutive Numbers](https://leetcode.com/problems/consecutive-numbers/)

* SQL Schema

```sql
Create table If Not Exists Logs (id int, num int)
Truncate table Logs
insert into Logs (id, num) values ('1', '1')
insert into Logs (id, num) values ('2', '1')
insert into Logs (id, num) values ('3', '1')
insert into Logs (id, num) values ('4', '2')
insert into Logs (id, num) values ('5', '1')
insert into Logs (id, num) values ('6', '2')
insert into Logs (id, num) values ('7', '2')
```

* Table: Logs

| Column Name | Type    |
|-------------|---------|
| id          | int     |
| num         | varchar |

In SQL, id is the primary key for this table.
`id` is an autoincrement column.

Find all numbers that appear at least three times consecutively.

Return the result table in any order.

The result format is in the following example.

Example 1:

Input:
Logs table:
| id | num |
|----|-----|
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |

Output:
| ConsecutiveNums |
|-----------------|
| 1               |

Explanation: 1 is the only number that appears consecutively for at least three times.

_**Solution:**_

```sql
-- Using self joins
select distinct log1.num as ConsecutiveNums
from logs log1, logs log2, logs log3
where log1.id = log2.id + 1
and log1.num = log2.num
and log1.id = log3.id + 2
and log1.num = log3.num
```

```sql
-- Using self joins
SELECT DISTINCT l1.num AS ConsecutiveNums
FROM Logs l1
JOIN Logs l2 ON l1.id = l2.id - 1
JOIN Logs l3 ON l1.id = l3.id - 2
WHERE l1.num = l2.num AND l2.num = l3.num;
```

```sql
SELECT DISTINCT num AS ConsecutiveNums
FROM (
    SELECT
        LAG(id) OVER (ORDER BY id) AS prev_id, id,
        LEAD(id) OVER (ORDER BY id) AS next_id,
        LAG(num) OVER (ORDER BY id) AS prev_num, num,
        LEAD(num) OVER (ORDER BY id) AS next_num
    FROM logs
) subquery
WHERE prev_num = num
  AND num = next_num
  AND next_id - id = 1
  AND id - prev_id = 1;
```

```sql
-- Using EXISTS and SUBQUERY
SELECT DISTINCT l1.num AS ConsecutiveNums
FROM Logs l1
WHERE EXISTS (
    SELECT 1
    FROM Logs l2
    WHERE l2.id = l1.id + 1 AND l2.num = l1.num
    AND EXISTS (
        SELECT 1
        FROM Logs l3
        WHERE l3.id = l1.id + 2 AND l3.num = l1.num
    )
);
```

```sql
select distinct l.num as ConsecutiveNums from logs l where
l.num = (select l2.num from logs l2  where l2.id = l.id + 1) and
l.num = (select l3.num from logs l3  where l3.id = l.id + 2)
```

```sql
Select
    distinct l1.num as ConsecutiveNums
    from
    logs l1 join logs l2 join logs l3
    on
        l1.id = l2.id-1
        and
        l2.id = l3.id-1
    where
        l1.num = l2.num
        and l2.num = l3.num
```

```sql
select distinct num as ConsecutiveNums
from logs
where (id+1,num) in (select * from logs)
and (id+2,num) in (select * from logs)
```
