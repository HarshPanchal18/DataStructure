# [Fix Names in a table](https://leetcode.com/problems/fix-names-in-a-table/)

* SQL Schema

```sql
Create table If Not Exists Users (user_id int, name varchar(40))
Truncate table Users
insert into Users (user_id, name) values ('1', 'aLice')
insert into Users (user_id, name) values ('2', 'bOB')
```

Table: Users
| Column Name    | Type    |
|----------------|---------|
| user_id        | int     |
| name           | varchar |

`user_id` is the primary key (column with unique values) for this table.
This table contains the ID and the name of the user. The name consists of only lowercase and uppercase characters.

Write a solution to fix the names so that only the first character is uppercase and the rest are lowercase.

Return the result table ordered by `user_id`.
The result format is in the following example.

Example 1:

Input:
Users table:
| user_id | name  |
|---------|-------|
| 1       | aLice |
| 2       | bOB   |

Output:
| user_id | name  |
|---------|-------|
| 1       | Alice |
| 2       | Bob   |

_**Solution:**_

```sql
select user_id, CONCAT(UPPER(SUBSTR(name,1,1)), LOWER(SUBSTR(name,2))) as name from Users order by user_id asc
```
