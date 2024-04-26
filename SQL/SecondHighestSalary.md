# [Second Highest Salary](https://leetcode.com/problems/second-highest-salary/)

* SQL Schema

```sql
Create table If Not Exists Employee (id int, salary int)
Truncate table Employee
insert into Employee (id, salary) values ('1', '100')
insert into Employee (id, salary) values ('2', '200')
insert into Employee (id, salary) values ('3', '300')
```

* Table: Employee

| Column Name | Type |
|-------------|------|
| id          | int  |
| salary      | int  |

`id` is the primary key (column with unique values) for this table.
Each row of this table contains information about the salary of an employee.

Write a solution to find the second highest salary from the Employee table. If there is no second highest salary, return null (return None in Pandas).

The result format is in the following example.
Example 1:
Input:
Employee table:
| id | salary |
|----|--------|
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |

Output:
| SecondHighestSalary |
|---------------------|
| 200                 |

Example 2:
Input:
Employee table:
| id | salary |
|----|--------|
| 1  | 100    |

Output:
| SecondHighestSalary |
|---------------------|
| null                |

_**Solution:**_

```sql
# Write your MySQL query statement below
select (select distinct salary from Employee order by salary desc limit 1 offset 1) as SecondHighestSalary
```
