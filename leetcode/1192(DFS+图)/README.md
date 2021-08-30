### to 1192 of leetcode website

[Critical Connections in a Network](https://leetcode-cn.com/problems/critical-connections-in-a-network/)

**小结**
处理图中的环状图问题时，可以通过一个递增id来标记每个节点的Id，如果下一个节点的id已经被标记，并且小于当前的id，说明出现环，否则下一个节点的id 为 id + 1