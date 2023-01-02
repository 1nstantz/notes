--
-- Created by IntelliJ IDEA.
-- User: admin
-- Date: 2023/1/2
-- Time: 17:38
-- To change this template use File | Settings | File Templates.
--

require "luasql.mysql"

--创建环境对象
env = luasql.mysql()

--连接数据库
conn = env:connect("mysql","root","123456","59.110.221.83",3306)

--设置数据库的编码格式
conn:execute"SET NAMES UTF8"

--执行数据库操作
cur = conn:execute("select * from user")

row = cur:fetch({},"a")

while row do

    print(row.User, row.Host)

    row = cur:fetch(row,"a")
end


conn:close()  --关闭数据库连接
env:close()

